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
                gui.enableAnmeldung();
                gui.printOut("Verbindung erfolgreich. Du kannst dich jetzt anmelden!");
                break;
            case "ANMOK":
                gui.showMessageDialog("Anmeldung erfolgreich!");
                gui.displaySuche();
                break;
            case "ABMOK":
                gui.showMessageDialog("Du bist nun abgemeldet!");
                gui.displayAnmeldung();
                break;
            case "ANMERROR":
                gui.showMessageDialog("Anmeldung fehlgeschlagen!");
                break;
            case "ANMERROR1":
                gui.showMessageDialog("Benutzername und Passwort stimmen nicht überein!" + "\n" +
                        "Prpbier es erneut oder erstelle ein neues Konto.");
                break;
            case "REGOK":
                gui.showMessageDialog("Registrierung erfolgreich!");
                gui.displayAnmeldung();
                break;
            case "REGERROR":
                gui.showMessageDialog("Registrierung fehlgeschlagen!");
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
            case "ERGEBNIS":
                List<Produkt> ergebnis = new List<>();
                for (int i = 1; i < protokoll.length; i++) {
                    String[] p = protokoll[i].split("/");
                    String [] b = p[10].split(";");
                    ergebnis.append(new Produkt(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8], p[9], b, p[11], p[12], p[13]));
                }
                gui.displayErgebnis(ergebnis);
                break;
            case "SUCHERROR1":
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
