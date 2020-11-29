public class Anfragen {

    public static String sucheAutor(String autor) {
        String sucheAutor = "SELECT ArtikelNr FROM Produkte WHERE Autor=" + autor;
        return sucheAutor;
    }

    public static String sucheTitel (String titel){
        String sucheTitel = "SELECT ArtikelNr FROM Produkte WHERE Titel=" + titel;
        return sucheTitel;
    }

    public static String sucheISBN (String isbn) {
        String sucheISBN = "SELECT ArtikelNr FROM Produkte WHERE ISBN=" + isbn;
        return sucheISBN;
    }

    public static String sucheGenre (String genre) {
        String sucheGenre = "SELECT ArtikelNr FROM Produkte WHERE=" + genre;
        return sucheGenre;
    }
}
