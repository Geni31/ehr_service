package com.ehr.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer implements Runnable {
    private int port;
    public static String IPaddress = "localhost";
    public SimpleServer(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is ready and listening");

            // Accept incoming connections
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("New client connected on port " + port);

                    BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

                    String message = input.readLine();
                    System.out.println("Received on port " + port + ": " + message);

                    output.println("Echo from port " + port + ": " + message);
                } catch (IOException e) {
                    System.err.println("Error handling client on port " + port + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
    public static String getIPaddress(){
        return IPaddress;
    }
}
