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
                if (DatabaseAccess.einloggen("SELECT Passwort FROM User WHERE Username=" + protokoll[1]) == protokoll[2]) {
                    send(clientIP, clientPort,"ANMOK");
                } else if(DatabaseAccess.einloggen("SELECT Passwort FROM User WHERE Username=" + protokoll[1]) == "42704"/*TODO: Fehlermeldung weitergeben, wenn es keinen Benutzer mit diesem Namen gibt*/){
                    send(clientIP, clientPort, "ANMERROR");
                } else {
                    send(clientIP, clientPort, "ANMERROR1");
                }
                break;
            case "REG":
                if (DatabaseAccess.userRegistrieren("SELECT Username FROM User WHERE Username=" + protokoll[1]) == null) {
                    DatabaseAccess.userRegistrieren("INSERT INTO User (Username, PLZ, Straße, Hausnummer, Password, Vorname, Name, E-Mail, Telefonnummer, Geburtstag, Geschlecht)" +
                            "VALUES (" + protokoll[1] + ", " + protokoll[10] + ", " + protokoll[7] + ", " + protokoll[8] + ", " + protokoll[2] + ", " + protokoll[3] + ", " + protokoll[4] +
                            ", " + protokoll[11] + ", " + protokoll[12] + ", " + protokoll[5] + ", " + protokoll[6] + ")");
                    send(clientIP, clientPort, "REGOK");
                    break;
                } else {
                    send(clientIP, clientPort, "REGERROR:" + protokoll[1]); /*TODO: Ein*e Benutzer*in mit dem Usernamen " + protokoll[1] + " existiert bereits. Bitte wählen Sie einen anderen Usernamen.*/
                }
                break;
            case "ABM":
                processClosingConnection(clientIP, clientPort);
                send(clientIP, clientPort,"ABMOK");
                break;
            case "SUCHE": /*TODO: SUCHERROR FEHLT NOCH*/
                if (protokoll[1] == "Autor") {
                    send(clientIP, clientPort,"ERGEBNISSE:" + DatabaseAccess.sucheAutor("SELECT * FROM Produkte WHERE Autor=" + protokoll[2]));
                }

                if (protokoll[1] == "Titel") {
                    send(clientIP, clientPort,"ERGEBNISSE:" + DatabaseAccess.sucheTitel("SELECT * FROM Produkte WHERE Titel=" + protokoll[2]));
                }

                if (protokoll[1] == "ISBN") {
                    send(clientIP, clientPort,"ERGEBNISSE:" + DatabaseAccess.sucheISBN("SELECT * FROM Produkte WHERE ISBN=" + protokoll[2]));
                }

                if (protokoll[1] == "Genre") {
                    send(clientIP, clientPort,"ERGEBNISSE:" + DatabaseAccess.sucheGenre("SELECT * FROM Produkte WHERE Genre=" + protokoll[2]));
                }
                break;
            case "BESTELLUNG": /*TODO: Bestellung, Bestellung ansehen, Bewertungen etc...*/
                break;
            case "BEWERTUNG":
                break;
            case "PROFILEEDIT":
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

Fragen an Hannah und Linus

1. Warum wird als Suchergebnis nur die Artikelnummer zurückgegeben?
2. Wird der Einkaufswagen in der Datenbank gespeichert?

*/