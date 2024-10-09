package com.ehr.Services;

import com.ehr.Server.SimpleServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Billing_Service {
    public static void main(String[] args) {
        int port = 1236;

        try (Socket socket = new Socket(SimpleServer.getIPaddress(), port);
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

                output.println(userInput);

                String response = input.readLine();
                System.out.println("Server response: " + response);
            }
        } catch (IOException e) {
            System.err.println("Could not connect to server on port " + port + ": " + e.getMessage());
        }
    }
}