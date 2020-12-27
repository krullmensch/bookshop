import datenstrukturklassen.linear.List;
import javafx.util.Pair;

public class Einkaufswagen {
    List<Pair<Produkt, Integer>> l;
    private int Summe;

    public Einkaufswagen() {}

    public void addItem(Produkt p, int i) {
        l.append(new Pair<>(p, i));
    }

    public void removeItem(Produkt p){

    }

    public void editItem(Produkt p, int i){

    }

    public int getSumme() {
        int summe = 0;
        for (l.toFirst(); l.hasAccess(); l.next()) {
            summe = summe + (Integer.parseInt(l.getContent().getKey().getPreis()) * l.getContent().getValue());
        }
        return summe;
    }
}
