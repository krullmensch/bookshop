public class ShopGUI {

    protected StateGUI state;
    protected ShopGUI gui;
    protected Shopclient client;

    private Anmeldung anmeldung;
    private Registrierung registrierung;

    public ShopGUI() {
        //this.gui = gui;

        anmeldung = new Anmeldung();

    }


    public void displayRegistrierung() {
        anmeldung.close();
        registrierung = new Registrierung();
        registrierung.display();
    }


    public void showMessageDialog(String out) {
        switch (state) {
            case ANMELDUNG:
                anmeldung.printOut(out);
                break;
            case REGISTRIERUNG:
                registrierung.printOut(out);
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
        ShopGUI w = new ShopGUI();


    }
}
