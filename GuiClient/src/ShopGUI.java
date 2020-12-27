import datenstrukturklassen.linear.List;

public class ShopGUI {

    private StateGUI state;
    private Shopclient client;

    private Anmeldung anmeldung;
    private Registrierung registrierung;
    private Suche suche;
    private Einkaufswagen einkaufswagen;

    public ShopGUI() {
        state = StateGUI.VERBINDUNG;
        anmeldung = new Anmeldung(this);

    }

    public void referenceClient(Shopclient c){
        client = c;
    }

    public void referenceEinkaufswagen(Einkaufswagen e){einkaufswagen = e;}

    public StateGUI getState(){
        return state;
    }

    public void changeState(StateGUI s){
        state = s;
    }


    public void displayRegistrierung() {
        state = StateGUI.REGISTRIERUNG;
        anmeldung.dispose();
        registrierung = new Registrierung(this, client);
    }

    public void displayAnmeldung(){
        state = StateGUI.ANMELDUNG;
        registrierung.dispose();
        anmeldung = new Anmeldung(this);
    }

    public void displaySuche(){
        if(state == StateGUI.ANMELDUNG){
            anmeldung.dispose();
        }else if(state == StateGUI.PRODUKT){
            //produkt.dispose
        }
        state = StateGUI.SUCHE;
        suche = new Suche();
    }

    public void displayErgebnis(List<Produkt> ergebnis){
        suche.displayErgebnis(ergebnis);
    }


    public void showMessageDialog(String out) {
        switch (state) {
            case ANMELDUNG:
                anmeldung.showMessageDialog(out);
                break;
            case REGISTRIERUNG:
                registrierung.showMessageDialog(out);
                break;
            case SUCHE:
                suche.showMessageDialog(out);
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

    public void testSuche(){

        Suche suche = new Suche();
        String [] protokoll = new String[2];
        protokoll[0] = "ERGEBNIS";
        protokoll[1] = "Harry Potter" + "," + "JK" +","+  "3"+"," +"4"+"," +"5"+","+ "6" +","+"7"+","+"8"+","+"9"+","+"10"+","+"11"+","
                +"12"+","+"13";
        protokoll[2] = "Schnotter" + "," + "Leo" +","+  "3"+"," +"4"+"," +"5"+","+ "6" +","+"7"+","+"8"+","+"9"+","+"10"+","+"11"+","
                +"12"+","+"13";
        List<Produkt> ergebnis = new List<>();
        for (int i = 1; i < protokoll.length; i++) {
            String[] p = protokoll[i].split(",");
            ergebnis.append(new Produkt(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8], p[9], p[10], p[11], p[12]));
        }
        suche.displayErgebnis(ergebnis);
    }

    public static void main(String[] args) {
        //ShopGUI s = new ShopGUI();
        //Suche suche = new Suche();

        Suche suche = new Suche();
        String [] protokoll = new String[3];
        protokoll[0] = "ERGEBNIS";
        protokoll[1] = "Harry Potter und der Stein der Weisen" + "," + "JK" +","+  "3"+"," +"4"+"," +"5"+","+ "6" +","+"7"+","+"8"+","+"9"+","+"10"+","+"11"+","
                +"12"+","+"13";
        protokoll[2] = "Schnotter" + "," + "Leo" +","+  "a"+"," +"b"+"," +"c"+","+ "d" +","+"e"+","+"f"+","+"g"+","+"h"+","+"i"+","
                +"j"+","+"k";
        List<Produkt> ergebnis = new List<>();
        for (int i = 1; i < protokoll.length; i++) {
            String[] p = protokoll[i].split(",");
            ergebnis.append(new Produkt(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8], p[9], p[10], p[11], p[12]));
        }
        suche.displayErgebnis(ergebnis);


    }
}
