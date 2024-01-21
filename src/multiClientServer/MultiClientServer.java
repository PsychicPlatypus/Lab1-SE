package multiClientServer;

import java.io.*;
import java.net.*;

public class MultiClientServer extends Thread {
    private Integer port;
    private Integer connectionId = 0;

    public MultiClientServer(Integer port) {
        this.port = port;
    }

    public void run() {
        try {
            System.out.println("Current server running: " + InetAddress.getLocalHost().getHostAddress());
            ServerSocket serverSocket = new ServerSocket(this.port);
            Socket clientSocket;

            InetAddress aInetAddress = serverSocket.getInetAddress();
            Integer portInteger = serverSocket.getLocalPort();

            String output = String.format("%s:%s", aInetAddress.getHostAddress(), Integer.toString(portInteger));
            System.out.println("Server started at: " + output);

            while (true) {
                try {
                    clientSocket = serverSocket.accept();
                    SocketHandler conn = new SocketHandler(connectionId, clientSocket);
                    conn.start();
                    connectionId += 1;
                } catch (IOException e) {
                    System.out.println("Accept failed:2345");
                    serverSocket.close();
                    System.exit(-1);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not listen on port: 2345");
            System.exit(-1);
        }
    }
}