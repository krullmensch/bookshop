import datenstrukturklassen.linear.List;
import netzklassen.Client;

public class Shopclient extends Client {

    private ShopGUI gui;

    public Shopclient(String ip, int port, ShopGUI gui) {
        super(ip, port);
        this.gui = gui;
    }

    @Override
    public void processMessage(String msg) {
        String[] protokoll = msg.split(":");
        switch (protokoll[0]) {
            case "VERBUNDEN":
                gui.showMessageDialog("Verbindung erfolgreich. Du kannst dich jetzt anmelden!");
                gui.enableAnmeldung();
                break;
            case "ANMOK":
                gui.showMessageDialog("Anmeldung erfolgreich!");
                gui.displaySuche();
                break;
            case "ABMOK":
                gui.showMessageDialog("Du bist nun abgemeldet!");
                gui.resetEinkaufswagen();
                gui.displayAnmeldung();
                break;
            case "ANMERROR":
                gui.showMessageDialog("Benutzername und Passwort stimmen nicht überein!" + "\n" +
                        "Prpbiere es erneut oder erstelle ein neues Konto.");
                break;
            case "REGOK":
                gui.showMessageDialog("Registrierung erfolgreich!");
                gui.displayAnmeldung();
                break;
            case "REGERROR":
                gui.showMessageDialog("Ein*e Benutzer*in mit dem Usernamen " + protokoll[1] + " existiert bereits." + "\n"+
                        "Bitte wählen Sie einen anderen Usernamen.");
                break;
            case "PROFIL":
                gui.displayProfil(protokoll);
                break;
            case "PROFILOK":
                gui.showMessageDialog("Profil erfolgreich bearbitet!");
                gui.displaySuche();
                break;
            case "PROFILERROR":
                gui.showMessageDialog("Profilbearbeitung fehlgeschlagen");
                break;
            case "ERGEBNISSE":
                List<Produkt> ergebnis = new List<>();
                for (int i = 1; i < protokoll.length; i++) {
                    String[] p = protokoll[i].split("/");
                    String [] b = p[10].split(";");
                    ergebnis.append(new Produkt(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8], p[9], b, p[11], p[12], p[13]));
                }
                gui.displayErgebnis(ergebnis);
                break;
            case "SUCHERROR":
                gui.showMessageDialog("Es wurden keine Artikel mit deiner Suchanfrage gefunden!");
                break;
            case "BESTELLOK":
                gui.showMessageDialog("Vielen Dank für deine Bestellung!");
                gui.resetEinkaufswagen();
                gui.displaySuche();
                break;
            case "BESTELLERROR":
                gui.showMessageDialog("Bestellung fehlgeschlagen");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + protokoll[0]);
        }

    }

}
