package Model_OSI.Two;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "netology.homework"; // Адрес сервера
        int port = 8080; // Порт сервера

        try (Socket socket = new Socket(host, port)) {
            // Потоки для чтения и записи данных
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            // Шаг 1: Чтение запроса имени
            String serverMessage = in.readLine();
            System.out.println(serverMessage);
            String name = scanner.nextLine();
            out.println(name);

            // Шаг 2: Чтение запроса возраста
            serverMessage = in.readLine();
            System.out.println(serverMessage);
            String isChild = scanner.nextLine();
            out.println(isChild);

            // Шаг 3: Чтение финального сообщения
            serverMessage = in.readLine();
            System.out.println(serverMessage);

            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
