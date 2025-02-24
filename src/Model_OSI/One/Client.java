package Model_OSI.One;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String host = "localhost"; // Адрес сервера
        int port = 8080; // Порт сервера

        try (Socket socket = new Socket(host, port)) {
            // Потоки для чтения и записи данных
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Отправка строки серверу
            out.println("Client");

            // Чтение ответа от сервера
            String serverResponse = in.readLine();
            System.out.println("Server response: " + serverResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
