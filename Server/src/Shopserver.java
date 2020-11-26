import netzklassen.Server;
import java.util.HashMap;


public class Shopserver extends Server {

    HashMap<String, Integer> aktVerbindungen = new HashMap<String,Integer>();

    public Shopserver(int serverPort) {
        super(serverPort);
    }

    @Override
    public void processNewConnection(String clientIP, int clientPort) {
        System.out.println("Verbindung mit IP " + clientIP + " über Port " + clientPort + " aufgebaut!");

        aktVerbindungen.put(clientIP, clientPort);
        //send(clientIP, clientPort, "VERBUNDEN");
        System.out.println(aktVerbindungen);
    }

    @Override
    public void processMessage(String clientIP, int clientPort, String msg) {
        
    }

    @Override
    public void processClosingConnection(String clientIP, int clientPort) {
        System.out.println("Verbindung mit IP " + clientIP + " über Port " + clientPort + " getrennt!");
    }

    public static void main(String[] args) {
        new Shopserver(5555);
        System.out.println("Server gestartet auf Port 5555");
        System.out.println("Warten auf Verbindungen");
    }
}

//FRAGEN AN CHRISTIAN
/*

1. Muss die IP und der Port notiert werden damit keine Mehrfachverbindungen über den selben Port funktionieren?

*/