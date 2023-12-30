package bg.sofia.uni.fmi.mjt.order.server;

import bg.sofia.uni.fmi.mjt.order.server.repository.OrderRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRequestHandler implements Runnable {

    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command";
    private static final int COMMAND_INDEX = 0;
    private static final int SECOND_WORD_INDEX = 1;
    private static final int ID_INDEX = 2;
    private static final int SIZE_INDEX = 1;
    private static final int COLOR_INDEX = 2;
    private static final int DESTINATION_INDEX = 3;
    private static final int VALUE_INDEX = 1;
    private final Socket socket;
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
        switch (inputTokens[COMMAND_INDEX].toLowerCase()) {
            case "get" -> handleGetCommand(inputTokens, out);
            case "request" -> handleRequestCommand(inputTokens, out);
            default -> out.println(UNKNOWN_COMMAND_MESSAGE);
        }
    }

    private void handleGetCommand(String[] inputTokens, PrintWriter out) {
        switch (inputTokens[SECOND_WORD_INDEX].toLowerCase()) {
            case "all" -> out.println(orderRepository.getAllOrders());
            case "all-successful" -> out.println(orderRepository.getAllSuccessfulOrders());
            case "my-order" -> out.println(orderRepository.getOrderById(
                    Integer.parseInt(getValueFromToken(inputTokens[ID_INDEX]))
            ));
            default -> out.println(UNKNOWN_COMMAND_MESSAGE);
        }
    }

    private void handleRequestCommand(String[] inputTokens, PrintWriter out) {
        String size = getValueFromToken(inputTokens[SIZE_INDEX]);
        String color = getValueFromToken(inputTokens[COLOR_INDEX]);
        String destination = getValueFromToken(inputTokens[DESTINATION_INDEX]);

        out.println(orderRepository.request(size, color, destination));
    }

    private String getValueFromToken(String token) {
        return token.split("=")[VALUE_INDEX];
    }

}
