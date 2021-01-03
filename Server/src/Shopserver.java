import netzklassen.Server;
public class Shopserver extends Server{

DatabaseAccess ac = new DatabaseAccess("bookshop");

    public Shopserver(int serverPort) {
        super(serverPort);
    }


    /* Im folgenden wird eine Verbindungsanfrage des Clients verarbeitet */
    @Override
    public void processNewConnection(String clientIP, int clientPort) {
        System.out.println("Verbindung mit IP " + clientIP + " über Port " + clientPort + " aufgebaut!");
        send(clientIP, clientPort, "VERBUNDEN");
    }


    /* Im Folgenden werden die Nachrichten die vom Client kommen interpretiert und es wird mit ihnen umgegangen.
     * Jeder Fall (Case) wird mit dem ersten Wort welches die Nachricht des Clients beinhaltet interpretiert und ausgeführt
     * Die Anfragen an die Datenbank werden in der Shopserver Klasse geschrieben und per Aufruf bestimmer Methoden an die Klasse DatabaseAccess weitergegeben.
     * Dort findet die tatsächliche SQL Anfrage über den Databaseconnector statt.*/
    @Override
    public void processMessage(String clientIP, int clientPort, String msg) {
        String[] protokoll = msg.split(":");
        switch (protokoll[0]) {


            /* Im ersten Fall geht es um die Anmeldung eines Users und somit um die Freischaltung für den "Zugang" zur Datenbank.
            *  Dafür wird auf der Datenbank ein SELECT Befehl ausgeführt womit dann im Folgenden die Übereinstimmung des Passworts in der Datenbank mit der Eingabe des Users überprüft wird.
            *  Wenn der Username selbst auch schon nicht in der Daten  vorhanden ist, wird ein Anmelde-Error an den Client gesendet */
            case "ANM":
                if (ac.einloggen("SELECT Passwort FROM User WHERE Username=" + protokoll[1]).equals(protokoll[2])) {
                    send(clientIP, clientPort,"ANMOK");
                    System.out.println("User: " + ac.getCell("SELECT Username FROM User WEHRE Username=" + protokoll[1]) + " hat sich angemeldet!");
                } else {
                    send(clientIP, clientPort, "ANMERROR");
                }
                break;


            /* In diesem Fall geht es um die Registration eines neuen Users in der Datenbank.
            *  Wenn der Username, welcher vom User eingegeben wurde bereits existiert, wird ein Registrations-Error an den Client gesendet
            *  Für die Registration wird ein INSERT Befehl auf der Datenbank ausgeführt mit dem der neue User eingetragen wird.
            *  Dabei werden die Daten: Username, PLZ, Straße, Hausnummer, Password, Vorname, Name, E-Mail, Telefonnummer, Geburtstag, Geschlecht
            *  vom Client an den Server und von dort an die Datenbank geschickt */
            case "REG":
                if (ac.userRegistrieren("SELECT Username FROM User WHERE Username=" + protokoll[1]) == null) {
                    ac.userRegistrieren("INSERT INTO User (Username, PLZ, Straße, Hausnummer, Password, Vorname, Name, E-Mail, Telefonnummer, Geburtstag, Geschlecht)" +
                            "VALUES (" + protokoll[1] + ", " + protokoll[10] + ", " + protokoll[7] + ", " + protokoll[8] + ", " + protokoll[2] + ", " + protokoll[3] + ", " + protokoll[4] +
                            ", " + protokoll[11] + ", " + protokoll[12] + ", " + protokoll[5] + ", " + protokoll[6] + ")");
                    send(clientIP, clientPort, "REGOK");
                } else {
                    send(clientIP, clientPort, "REGERROR:" + protokoll[1]);
                }
                break;


            /* In diesem Fall geht es nur darum wenn sich ein User aus dem Client ausloggt und dafür wird eine Abmeldebestätigung an den Client gesendet
            *  und zudem noch eine Nachricht auf der Server Console ausgegeben. */
            case "ABM":
                send(clientIP, clientPort,"ABMOK");
                System.out.println("User: " + ac.getCell("SELECT Username FROM User WEHRE Username=" + protokoll[1]) + " hat sich abgemeldet!");
                processClosingConnection(clientIP, clientPort);
                break;


            /* In diesem Fall geht es um die Suche der Angebotenen Produkte durch den Client und die Verarbeitung durch den Server und die Datenbank.
            *  Gesucht wird, vorgegeben durch den Client, nach den vier "Suchwörtern": Autor, Titel, ISBN oder Genre. Das bedeutet es wird immer ein eindeutiges Element
            *  in der Datenbank gesucht und wiedergegeben wird die Ergebnistabelle mit allen übereinstimmenden Zeilen der Tabelle 'Produkte'. */
            case "SUCHE":

                if (protokoll[1].equals("Autor") && ac.suche("SELECT Autor FROM Produkte WHERE Autor=" + protokoll[2]) != null) {
                    send(clientIP, clientPort,"ERGEBNISSE:" + ac.suche("SELECT * FROM Produkte WHERE Autor=" + protokoll[2]).toString());
                } else {
                    send(clientIP,clientPort, "SUCHERROR");
                }

                if (protokoll[1].equals("Titel") && ac.suche("SELECT Titel FROM Produkte WHERE Autor=" + protokoll[2]) != null) {
                    send(clientIP, clientPort,"ERGEBNISSE:" + ac.suche("SELECT * FROM Produkte WHERE Autor=" + protokoll[2]).toString());
                } else {
                    send(clientIP,clientPort, "SUCHERROR");
                }

                if (protokoll[1].equals("ISBN") && ac.suche("SELECT ISBN FROM Produkte WHERE Autor=" + protokoll[2]) != null) {
                    send(clientIP, clientPort,"ERGEBNISSE:" + ac.suche("SELECT * FROM Produkte WHERE ISBN=" + protokoll[2]).toString());
                } else {
                    send(clientIP,clientPort, "SUCHERROR");
                }

                if (protokoll[1].equals("Genre") && ac.suche("SELECT Genre FROM Produkte WHERE Autor=" + protokoll[2]) != null) {
                    send(clientIP, clientPort,"ERGEBNISSE:" + ac.suche("SELECT * FROM Produkte WHERE Genre=" + protokoll[2]).toString());
                } else {
                    send(clientIP,clientPort, "SUCHERROR");
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
