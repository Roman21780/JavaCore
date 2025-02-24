package Model_OSI.Two;

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

                // Шаг 1: Запрос имени
                out.println("Write your name");
                String name = in.readLine();
                System.out.println("Client name: " + name);

                // Шаг 2: Запрос возраста
                out.println("Are you a child? (yes/no):");
                String isChild = in.readLine();
                System.out.println("Client is child: " + isChild);

                // Шаг 3: Ответ в зависимости от возраста
                if ("yes".equalsIgnoreCase(isChild)) {
                    out.println("Welcome to the kids area, " + name + "! Let's play!");
                } else {
                    out.println("Welcome to the adult zone, " + name + "! Have a good rest, or a good working day!");
                }

                // Закрытие соединения
                clientSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
