import datenstrukturklassen.linear.List;
import netzklassen.Client;

public class Shopclient extends Client {

    private ShopGUI gui;

    public Shopclient (String ip, int port, ShopGUI gui) {
        super(ip, port);
        this.gui = gui;
    }

    @Override
    public void processMessage(String msg) {
        String[] protokoll = msg.split(":");
        switch (protokoll[0]){
            case "VERBUNDEN":
                gui.changeState(StateGUI.ANMELDUNG);
                gui.enableAnmeldung();
                gui.printOut("Verbindung erfolgreich. Du kannst dich jetzt anmelden!");
                break;
            case "ANMOK":
                gui.changeState(StateGUI.SUCHE);
                gui.showMessageDialog("Anmeldung erfolgreich!");
                gui.displaySuche();
                break;
            case "REGOK":
                gui.displayAnmeldung();
                break;
            case "ERGEBNIS":
                if(protokoll[1].equals("")){
                    gui.showMessageDialog("Keine Bücher mit dieser Suche gefunden!");
                }else {
                    List<Produkt> ergebnis = new List<>();
                    for (int i = 1; i < protokoll.length; i++) {
                        String[] p = protokoll[i].split(",");
                        ergebnis.append(new Produkt(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8], p[9], p[10], p[11], p[12], p[13]));
                    }
                    gui.displayErgebnis(ergebnis);
                }




                break;
            default:
                throw new IllegalStateException("Unexpected value: " + protokoll[0]);
        }

    }

}
