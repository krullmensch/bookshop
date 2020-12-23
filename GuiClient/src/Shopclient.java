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
                //gui.displaySuchen
                break;
            case "REGOK":
                gui.displayAnmeldung();
                break;
            case "ERGEBNIS":



                break;
            default:
                throw new IllegalStateException("Unexpected value: " + protokoll[0]);
        }

    }

}
