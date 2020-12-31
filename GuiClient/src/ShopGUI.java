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
        anmeldung = new Anmeldung(this);
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
        registrierung = new Registrierung(this, client);
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
        suche = new Suche();
    }

    public void displayBestellung(){
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

    public void resetEinkaufswagen() {
        einkaufswagen = new Einkaufswagen();
    }

    public static void main(String[] args) {
        //ShopGUI s = new ShopGUI();

        //Suche suche = new Suche();
        String [] protokoll = new String[3];
        protokoll[0] = "ERGEBNIS";

        //Ergebnis: P(100:Harry Potter:j.k. Rowlins:2001.2002)

        protokoll[1] = "100/Harry Potter und der Stein der Weisen/J.K. Rowlins/20.01.2002/Cornelson/978-5472947/10/Fantasy/Deutsch/Erster Teil der Harry Potter Buchreihe/*****"
                + "\n" +"Klasse Buch!;***" + "\n"+"War Okay!" +"/12/29/4,5";
        protokoll[2] = "101" + "/" + "Fantastische Tierwesen" + "/" + "J.K. Rowlins" +"/"+  "03.08.2014"+"/" +"Cornelson"+"/" +"978-8432059"+"/"+ "15" +"/"+"Fantasy"+"/"+"Deutsch"+
                "/"+"Spin-off der Harry Potter Buchreihe"+"/"+"**" + "\n" + "Hat mir nicht gefallen"+";" + "*****"+ "\n"+ "Mega super duper"+"/"+"12"+"/"
                +"55"+"/"+"3,2";
        List<Produkt> ergebnis = new List<>();
        for (int i = 1; i < protokoll.length; i++) {
            String[] p = protokoll[i].split("/");
            String [] b = p[10].split(";");
            ergebnis.append(new Produkt(p[0], p[1], p[2], p[3], p[4], p[5], p[6], p[7], p[8], p[9], b, p[11], p[12], p[13]));
        }
        //suche.displayErgebnis(ergebnis);
        Admin admin = new Admin();
        admin.displayErgebnis(ergebnis);

        Einkaufswagen en = new Einkaufswagen();
        ergebnis.toFirst();
        en.addItem(ergebnis.getContent(), 2);
        ergebnis.next();
        en.addItem(ergebnis.getContent(), 1);
        //Bestellung b= new Bestellung(en);

        String pro = "PROFIL:Thom:124:Thomas:Edison:08.01.2002:Männlich:Alter Markt:2:Herford:32049:tj@itiger.de:0152 5682639";
        String []profil = pro.split(":");

        //Profil p = new Profil(profil);




    }


}
