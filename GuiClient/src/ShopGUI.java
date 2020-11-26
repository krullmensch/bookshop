public class ShopGUI {

    private StateGUI state;
    private Shopclient client;

    private Anmeldung anmeldung;
    private Registrierung registrierung;

    public ShopGUI() {
        anmeldung = new Anmeldung(this, client, state);

    }


    public void displayRegistrierung() {
        state = StateGUI.REGISTRIERUNG;
        anmeldung.dispose();
        registrierung = new Registrierung(this, client, state);
    }

    public void displayAnmeldung(){
        state = StateGUI.ANMELDUNG;
        registrierung.dispose();
        anmeldung = new Anmeldung(this, client, state);
    }


    public void showMessageDialog(String out) {
        switch (state) {
            case ANMELDUNG:
                anmeldung.showMessageDialog(out);
                break;
            case REGISTRIERUNG:
                registrierung.showMessageDialog(out);
                break;
        }
    }

    public void printOut(String out) {
        switch (state) {
            case ANMELDUNG:
                anmeldung.printOut(out);
                break;
            case REGISTRIERUNG:
                registrierung.printOut(out);
                break;
        }
    }

    public void enableAnmeldung() {
        anmeldung.enableAnmeldung();

    }

    public static void main(String[] args) {
        ShopGUI s = new ShopGUI();


    }
}
