public class Shopclient extends Client {

    private ShopGUI gui;
    private StateGUI state;

    public Shopclient (String ip, int port, ShopGUI gui, StateGUI state) {
        super(ip, port);
        this.gui = gui;
        this.state = state;
    }

    @Override
    public void processMessage(String message) {
        String[] protokoll = message.split(":");
        switch (protokoll[0]){
            case "VERBUNDEN":
                state = StateGUI.ANMELDUNG;
                gui.enableAnmeldung();
                gui.printOut("Verbindung erfolgreich. Du kannst dich jetzt anmelden!");
                break;
            case "ANMOK":
                state = StateGUI.SUCHE;
                gui.showMessageDialog("Anmeldung erfolgreich!");
                //gui.displaySuchen
                break;
            case "REGOK":
                state = StateGUI.ANMELDUNG;
                gui.displayRegistrierung();
                break;
            case "ERGEBNIS":



                break;
            default:
                throw new IllegalStateException("Unexpected value: " + protokoll[0]);
        }

    }

}
