package com.ehr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Appointment {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // Change to the server's IP if needed
        int port = 1235; // Port to connect to

        try (Socket socket = new Socket(serverAddress, port);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Connected to server on port " + port);

            String userInput;
            while (true) {
                System.out.print("Enter message to send (or 'exit' to quit): ");
                userInput = scanner.nextLine();
                if ("exit".equalsIgnoreCase(userInput)) {
                    break;
                }

                // Send message to the server
                output.println(userInput);

                // Read response from the server
                String response = input.readLine();
                System.out.println("Server response: " + response);
            }
        } catch (IOException e) {
            System.err.println("Could not connect to server on port " + port + ": " + e.getMessage());
        }
    }
}