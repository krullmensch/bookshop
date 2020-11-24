import netzklassen.Server;

public class shopserver extends Server {

    public shopserver(int clientPort) {
        super(clientPort);
    }

    @Override
    public void processNewConnection(String clientIP, int clientPort) {
        System.out.println("Kunde mit IP" + clientIP + "über Port" + clientPort + "verbunden!");
    }

    @Override
    public void processMessage(String clientIP, int clientPort, String msg) {
        
    }

    @Override
    public void processClosingConnection(String clientIP, int clientPort) {
        System.out.println("Kunde mit IP" + clientIP + "über Port" + clientPort + "getrennt!");
    }
}