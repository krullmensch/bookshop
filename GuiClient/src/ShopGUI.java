import datenstrukturklassen.linear.List;

public class ShopGUI {

    private StateGUI state;
    private Shopclient client;

    private Anmeldung anmeldung;
    private Registrierung registrierung;
    private Suche suche;
    private Bestellung bestellung;
    private Profil profil;
    private Einkaufswagen einkaufswagen;

    public ShopGUI() {
        state = StateGUI.ANMELDUNG;
        //anmeldung = new Anmeldung(this);
    }


    public void referenceClient(Shopclient c){
        client = c;
    }

    public void referenceEinkaufswagen(Einkaufswagen e){einkaufswagen = e;}

    public StateGUI getState(){
        return state;
    }


    public void displayRegistrierung() {
        state = StateGUI.REGISTRIERUNG;
        anmeldung.dispose();
        registrierung = new Registrierung(client);
    }

    public void displayAnmeldung(){
        if(state == StateGUI.REGISTRIERUNG) {
            registrierung.dispose();
        }else if(state == StateGUI.SUCHE){
            suche.dispose();
        }else if(state == StateGUI.BESTELLUNG){
            bestellung.dispose();
        }
        state = StateGUI.ANMELDUNG;
        anmeldung = new Anmeldung(this);
    }

    public void displaySuche(){
        if(state == StateGUI.ANMELDUNG){
            anmeldung.dispose();
        }else if(state == StateGUI.BESTELLUNG){
            bestellung.dispose();
        }else if(state == StateGUI.PROFIL){
            profil.dispose();
        }
        state = StateGUI.SUCHE;
        suche = new Suche(this, client);
    }

    public void displayBestellung(){
        suche.dispose();

        state = StateGUI.BESTELLUNG;
        bestellung = new Bestellung(einkaufswagen);
    }

    public void displayErgebnis(List<Produkt> ergebnis) {
        suche.displayErgebnis(ergebnis);
    }

    public void displayProfil(String[] p) {
        state = StateGUI.PROFIL;
        profil = new Profil(p);
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
            case BESTELLUNG:
                bestellung.showMessageDialog(out);
                break;
            case PROFIL:
                profil.showMessageDialog(out);
                break;
        }
    }

    public void enableAnmeldung() {
        anmeldung.enableAnmeldung();

    }

    public void resetEinkaufswagen() {
        einkaufswagen = new Einkaufswagen();
    }

    public static void testGUI(String fenster){
        ShopGUI shop = new ShopGUI();
        Shopclient c = new Shopclient("127.0.0.1", 5555,shop);

        String suchergebnis = "ERGEBNIS:100/Namen durcheinader wurschteln - So geht es richtig/Christian Eisentraut/20.08.2018/Kevinkatze Verlag/978-5472947/25/Lehrbuch/Deutsch/" +
                "Wer einfache Namen auch total langweilig findet, für den ist 'Namen durcheinander wurschteln' genau das richtige. Hier erährst du wie mehrere Namen miteinander kombiniert werden" +
                " und auch wie einfache langweilige Namen verbessert werden können./***** Marvus"
                + "\n" +"Hat buchstäblich mein Leben verändert!;**** Hanja" + "\n"+"Ein must-read für Jeden mit einem Namen.;*** Lina" +"\n" +"Funktioniert mit meinem Namen nicht, ansonsten klasse!/6/9/4";
        suchergebnis = suchergebnis + ":101/Die Pick-Up Verschwörung der OS Cafeteria/Christian Eisentraut/05.09.2020/Kevinkatze Verlag/978-79345045/15/Thriller/Deutsch/In dem Verschwörungsthriller " +
                "deckt ein heldenhafter Informatiklehrer das düstere Geheimnis der Schulcafeteria rund um den Schokoriegel Pick-Up auf./***** Mighty Merl"
                + "\n" +"Unfassbar spannendes Buch!;**** Ralle" + "\n"+"Die Auflösung am Ende war der absolute Hammer!" +"/18/38/4,5";
        String[] ergebnisprotokoll = suchergebnis.split(":");
        List<Produkt> ergebnis = new List<>();
        for (int i = 1; i < ergebnisprotokoll.length; i++) {
            String[] p = ergebnisprotokoll[i].split("/");
            String[] b = p[10].split(";");
            ergebnis.append(new Produkt(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8], p[9], b, p[11], p[12], p[13]));
        }

        switch (fenster) {
            case "Anmeldung":
                Anmeldung a = new Anmeldung(shop);
                break;
            case "Registrierung":
                Registrierung r = new Registrierung(c);
                break;
            case "Suche":
                Suche s = new Suche(shop,c);
                s.displayErgebnis(ergebnis);
                break;
            case "Bestellung":
                Einkaufswagen e = new Einkaufswagen();
                ergebnis.toFirst();
                e.addItem(ergebnis.getContent(), 2);
                ergebnis.next();
                e.addItem(ergebnis.getContent(), 1);
                Bestellung b= new Bestellung(e);
                break;
            case "Profil":
                String profilprotokoll = "PROFIL:Thom:124:Thomas:Edison:08.01.2002:Männlich:Alter Markt:2:Herford:32049:tj@itiger.de:0152 5682639";
                String []profil = profilprotokoll.split(":");
                Profil p = new Profil(profil);
                break;
            case "Admin":
                Admin admin = new Admin();
                admin.enableAnmeldung();
                admin.enableSuche();
                admin.displayErgebnis(ergebnis);
                break;

        }
    }


    public static void main(String[] args) {
        ShopGUI.testGUI("Registrierung");
    }


}
