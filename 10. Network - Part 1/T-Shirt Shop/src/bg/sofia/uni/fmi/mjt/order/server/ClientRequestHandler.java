package bg.sofia.uni.fmi.mjt.order.server;

import bg.sofia.uni.fmi.mjt.order.server.repository.OrderRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRequestHandler implements Runnable {

    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command";
    private Socket socket;
    private final OrderRepository orderRepository;

    public ClientRequestHandler(Socket socket, OrderRepository orderRepository) {
        this.socket = socket;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Client Request Handler " + socket.getRemoteSocketAddress());

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String input;
            while ((input = in.readLine()) != null) {
                handleInput(input, out);
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void handleInput(String input, PrintWriter out) {
        String[] inputTokens = input.split("\\s+");
        switch (inputTokens[0].toLowerCase()) {
            case "get" -> handleGetCommand(inputTokens, out);
            case "request" -> handleRequestCommand(inputTokens, out);
            default -> out.println(UNKNOWN_COMMAND_MESSAGE);
        }
    }

    private void handleGetCommand(String[] inputTokens, PrintWriter out) {
        switch (inputTokens[1].toLowerCase()) {
            case "all" -> out.println(orderRepository.getAllOrders());
            case "all-successful" -> out.println(orderRepository.getAllSuccessfulOrders());
            case "my-order" -> out.println(orderRepository.getOrderById(
                    Integer.parseInt(inputTokens[2].split("=")[1])
            ));
            default -> out.println(UNKNOWN_COMMAND_MESSAGE);
        }
    }

    private void handleRequestCommand(String[] inputTokens, PrintWriter out) {
        String size = getValueFromToken(inputTokens[1]);
        String color = getValueFromToken(inputTokens[2]);
        String destination = getValueFromToken(inputTokens[3]);

        out.println(orderRepository.request(size, color, destination));
    }

    private String getValueFromToken(String token) {
        return token.split("=")[1];
    }

}
