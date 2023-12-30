package bg.sofia.uni.fmi.mjt.order.server;

import bg.sofia.uni.fmi.mjt.order.server.repository.MJTOrderRepository;
import bg.sofia.uni.fmi.mjt.order.server.repository.OrderRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TShirtShopServer {

    private static final int SERVER_PORT = 4444;
    private static final int EXECUTORS_AMOUNT = 15;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(EXECUTORS_AMOUNT);
        Thread.currentThread().setName("TShirtShop Server Thread");

        OrderRepository orderRepository = new MJTOrderRepository();

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            Socket clientSocket;
            while (true) {
                clientSocket = serverSocket.accept();
                executor.execute(new ClientRequestHandler(clientSocket, orderRepository));
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred with the server socket!", e);
        } finally {
            executor.shutdown();
        }
    }
}
