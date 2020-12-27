import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Produkt {

    private String titel;
    private String autor;
    private String erscheinungsdatum;
    private String verlag;
    private String isbn;
    private String preis;
    private String genre;
    private String sprache;
    private String beschreibung;
    private String bewertung;
    private String altersfreigabe;
    private String lagerbestand;
    private String durschnitssbewertung;


    public Produkt(String titel, String autor, String erscheinungsdatum, String verlag, String isbn, String preis, String genre,
                   String sprache, String beschreibung, String bewertung, String altersfreigabe, String lagerbestand, String durschnitssbewertung) {
        this.titel = titel;
        this.autor = autor;
        this.erscheinungsdatum = erscheinungsdatum; //Zum Erstellen von Produkt mit Erscheinungsjahr als String
        this.verlag = verlag;
        this.isbn = isbn;
        this.preis = preis;
        this.genre = genre;
        this.sprache = sprache;
        this.beschreibung = beschreibung;
        this.bewertung = bewertung;
        this.altersfreigabe = altersfreigabe;
        this.lagerbestand = lagerbestand;
        this.durschnitssbewertung = durschnitssbewertung;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getVerlag() {
        return verlag;
    }

    public void setVerlag(String verlag) {
        this.verlag = verlag;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPreis() {
        return preis;
    }

    public void setPreis(String preis) {
        this.preis = preis;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSprache() {
        return sprache;
    }

    public void setSprache(String sprache) {
        this.sprache = sprache;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getBewertung() {
        return bewertung;
    }

    public void setBewertung(String bewertung) {
        this.bewertung = bewertung;
    }

    public String getAltersfreigabe() {
        return altersfreigabe;
    }

    public void setAltersfreigabe(String altersfreigabe) {
        this.altersfreigabe = altersfreigabe;
    }

    public String getLagerbestand() {
        return lagerbestand;
    }

    public void setLagerbestand(String lagerbestand) {
        this.lagerbestand = lagerbestand;
    }

    public String getDurschnitssbewertung() {
        return durschnitssbewertung;
    }

    public void setDurschnitssbewertung(String durschnitssbewertung) {
        this.durschnitssbewertung = durschnitssbewertung;
    }

    public String getErscheinungsdatum() {
        return erscheinungsdatum;
    }

    public void setErscheinungsdatum(String erscheinungsdatum) {
        this.erscheinungsdatum = erscheinungsdatum;
    }
}
