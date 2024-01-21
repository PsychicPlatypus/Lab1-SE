package EchoClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import TimeSerialization.WriteTime;

public class EchoClient {
    private String server;
    private Integer port;

    public EchoClient(String server, Integer port) {
        this.server = server;
        this.port = port;
    }

    public void establish() {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            echoSocket = new Socket(this.server, this.port);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O");
            System.exit(1);
        }
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;

        try {
            while ((userInput = stdIn.readLine()) != null) {
                if (userInput.equals("Bye.")) {
                    out.println(userInput);
                    break;
                } else if (userInput.equals("/time")) {
                    WriteTime writeTime = new WriteTime();
                    writeTime.write(new String[0]);
                    out.println(userInput);
                    System.out.println("echo: " + in.readLine());
                } else {
                    out.println(userInput);
                    System.out.println("echo: " + in.readLine());
                }
            }
            out.close();
            in.close();
            stdIn.close();
            echoSocket.close();
        } catch (IOException ioe) {
            System.out.println("Failed");
            System.exit(-1);
        }
    }
}