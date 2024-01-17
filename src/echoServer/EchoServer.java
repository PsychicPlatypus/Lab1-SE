package echoServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public EchoServer() {
    }

    public void establish() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(1234);
            Socket clientSocket;
            clientSocket = serverSocket.accept();
            PrintWriter outputPrintWriter;
            BufferedReader in;
            outputPrintWriter = new PrintWriter(
                    clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));

            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                outputPrintWriter.println(inputLine);
                if (inputLine.equals("Bye."))
                    break;
            }
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Could not close");
            System.exit(-1);
        }
    }
}

