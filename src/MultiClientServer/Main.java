package MultiClientServer;

public class Main {
    public static void main(String[] args) {
        MultiClientServer multiClientServer = new MultiClientServer(2345);
        multiClientServer.start();
    }
}