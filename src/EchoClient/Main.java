package EchoClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException {
        String server = InetAddress.getLocalHost().getHostAddress();
        EchoClient echoClient = new EchoClient(server, 2345);
        echoClient.establish();
    }
}