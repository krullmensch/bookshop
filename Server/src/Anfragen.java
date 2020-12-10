import java.util.Date;

public class Anfragen {

    //ANMELDUNG/ABMELDUNG/REGISTRATION/BEARBEITEN

    public static String userRegistrieren(String username, int plz, String straße, String hausnummer, String password, String vorname, String name, String eMail, int telefonnummer, Date geburtsdatum, String geschlecht){
        String userRegistrieren = "INSERT INTO User (Username, PLZ, Straße, Hausnummer, Password, Vorname, Name, E-Mail, Telefonnummer, Geburtstag, Geschlecht)"+
                "VALUES (" + username + ", " + plz + ", " + straße + ", " + hausnummer + ", " + password + ", " + vorname + ", " + name + ", " + eMail + ", " + telefonnummer + ", " + geburtsdatum + ", " + geschlecht + ")";
        return userRegistrieren;
    }

    public static String einloggen(String username){
        String einloggen = "SELECT Passwort FROM User WHERE Username=" + username;
        return einloggen;
    }

    public static String userBearbeiten(String username, String kategorie, String inhalt){
        String userBearbeiten = "UPDATE User SET" + kategorie + "=" + inhalt + " WHERE Username=" + username;
        return userBearbeiten;
    }

    //SUCHE

    public static String sucheAutor(String autor) {
        String sucheAutor = "SELECT ArtikelNr FROM Produkte WHERE Autor=" + autor;
        return sucheAutor;
    }

    public static String sucheTitel(String titel){
        String sucheTitel = "SELECT ArtikelNr FROM Produkte WHERE Titel=" + titel;
        return sucheTitel;
    }

    public static String sucheISBN(String isbn) {
        String sucheISBN = "SELECT ArtikelNr FROM Produkte WHERE ISBN=" + isbn;
        return sucheISBN;
    }

    public static String sucheGenre(String genre) {
        String sucheGenre = "SELECT ArtikelNr FROM Produkte WHERE=" + genre;
        return sucheGenre;
    }
}
