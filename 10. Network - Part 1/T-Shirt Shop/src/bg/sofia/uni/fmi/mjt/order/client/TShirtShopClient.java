package bg.sofia.uni.fmi.mjt.order.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TShirtShopClient {

    private static final int SERVER_PORT = 4444;

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", SERVER_PORT);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            Thread.currentThread().setName("Client Thread " + socket.getLocalPort());

            while (true) {
                String message = scanner.nextLine();

                if (message.equals("disconnect")) {
                    System.out.println("Disconnected from the server");
                    break;
                }

                out.println(message);
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error occurred in network communication!", e);
        }
    }
}
