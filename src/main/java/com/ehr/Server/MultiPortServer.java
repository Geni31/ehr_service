package com.ehr.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiPortServer {

    public static void main(String[] args) {
        int[] ports = {1234, 1235, 1236, 1237, 1238};

        for (int port : ports) {
            new Thread(new SimpleServer(port)).start();
        }
    }
}

