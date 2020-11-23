import netzklassen.Client;

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
            case "CONNECTION":
                gui.printOut("Connection sucessful!");
                state.setStateType(StateGUIType.CONNECTION);
                break;
            case "ANMELDEBESTÄTIGUNG":
                state.setStateType(StateGUIType.SEARCH);
                gui.refreshInterface();
                break;
            case "REGESTRIERUNGSBESTÄTIGUNG":
                state.setStateType(StateGUIType.SIGNIN);
                gui.refreshInterface();
                break;
            case "RESULT":



                break;
            default:
                throw new IllegalStateException("Unexpected value: " + protokoll[0]);
        }

    }

}
