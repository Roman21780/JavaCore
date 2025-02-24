package Model_OSI.One;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        int port = 8080; // Порт, на котором будет работать сервер

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New connection accepted");

                // Потоки для чтения и записи данных
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Чтение строки от клиента
                String clientMessage = in.readLine();
                System.out.println("Received from client: " + clientMessage + " from port " + clientSocket.getPort());

                // Отправка ответа клиенту
                out.println(String.format("Hi %s, your port is %d", clientMessage, clientSocket.getPort()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
