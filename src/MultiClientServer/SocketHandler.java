package MultiClientServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import TimeSerialization.ReadTime;

public class SocketHandler extends Thread {
    private Socket clientSocket;
    private Integer connectionId;

    public SocketHandler(Integer connectionId, Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.connectionId = connectionId;
        System.out.println("[ESTABLISHED CONNECTION]: " + clientSocket.getInetAddress().getHostAddress());
        System.out.println("[ASSIGNED ID]: " + Integer.toString(connectionId));
    }

    public void run() {
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    clientSocket.getInputStream()));
        } catch (IOException ioe) {
            System.out.println("Failed in creating streams");
            System.exit(-1);
        }
        String inputLine;
        try {
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
                System.out.println("[" + Integer.toString(connectionId) + "]: " + inputLine);
                if (inputLine.equals("Bye."))
                    break;
                if (inputLine.equals("/time")) {
                    ReadTime readTime = new ReadTime();
                    readTime.read(new String[0]);
                }
            }
        } catch (IOException ioe) {
            System.out.println("Failed in reading, writing");
            System.exit(-1);
        }
        try {
            clientSocket.close();
            out.close();
            in.close();
            System.out.println("[DISCONNECTED CONN WITH ID " + Integer.toString(this.connectionId) + " ]");
        } catch (IOException ioe) {
            System.out.println("Failed in closing down");
            System.exit(-1);
        }
    }
}
