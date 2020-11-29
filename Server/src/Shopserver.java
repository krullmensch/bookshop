import netzklassen.Server;

public class Shopserver extends Server {

    public Shopserver(int serverPort) {
        super(serverPort);
    }

    @Override
    public void processNewConnection(String clientIP, int clientPort) {
        System.out.println("Verbindung mit IP " + clientIP + " über Port " + clientPort + " aufgebaut!");

        send(clientIP, clientPort, "VERBUNDEN");
    }

    @Override
    public void processMessage(String clientIP, int clientPort, String msg) {
        String[] protokoll = msg.split(":");
        switch (protokoll[0]) {
            case "ANM":
                break;
            case "REG":
                break;
            case "ABM":
                break;
            case "SUCHE":
                if (protokoll[1] == "Autor") {
                    Anfragen.sucheAutor(protokoll[2]);
                }

                if (protokoll[1] == "Titel") {
                    Anfragen.sucheTitel(protokoll[2]);
                }

                if (protokoll[1] == "isbn") {
                    Anfragen.sucheISBN(protokoll[2]);
                }

                if (protokoll[1] == "genre") {
                    Anfragen.sucheGenre(protokoll[2]);
                }
                break;
        }
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

/*
FRAGEN AN CHRISTIAN

1. Databaseconnector Importieren

Fragen an Hannah und Linus

1. Warum wird als Suchergebnis nur die Artikelnummer zurückgegeben?
2. Wird der Einkaufswagen in der Datenbank gespeichert?

*/