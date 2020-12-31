import datenstrukturklassen.linear.List;
import netzklassen.Client;

import java.awt.event.ActionListener;

public class AdminClient extends Client {

    private Admin admin;

    public AdminClient(String ip, int port, Admin admin){
        super(ip, port);
        this.admin = admin;
    }


    @Override
    public void processMessage(String msg) {
        String[] protokoll = msg.split(":");
        switch (protokoll[0]) {
            case "VERBUNDEN":
                admin.showMessageDialog("Verbindung erfolgreich. Du kannst dich jetzt anmelden!");
                admin.enableAnmeldung();
                break;
            case "ANMOK":
                admin.showMessageDialog("Anmeldung erfolgreich!");
                admin.resetInterface();
                admin.enableSuche();
                break;
            case "ANMERROR":
                admin.showMessageDialog("Anmeldung fehlgeschlagen!");
                break;
            case "ANMERROR1":
                admin.showMessageDialog("Benutzername und Passwort stimmen nicht überein!");
            case "ERGEBNIS":
                List<Produkt> ergebnis = new List<>();
                for (int i = 1; i < protokoll.length; i++) {
                    String[] p = protokoll[i].split("/");
                    String [] b = p[10].split(";");
                    ergebnis.append(new Produkt(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8], p[9], b, p[11], p[12], p[13]));
                }
                admin.displayErgebnis(ergebnis);
                break;
            case "SUCHERROR1":
                admin.showMessageDialog("Es wurden keine Artikel mit deiner Suchanfrage gefunden!");
                break;
            case "EDITOK":
                admin.showMessageDialog("Deine Bearbeitung wurde in der Datenbank gespeichert");
                admin.refreshSuche();
                break;
            case "EDITERROR":
                admin.showMessageDialog("Deine Bearbeitung ist fehlgeschlagen");
                break;

        }

    }
}
