import datenstrukturklassen.linear.List;

public class Einkaufswagen {
    private List<Paar<Produkt, Integer>> l;

    public Einkaufswagen() {
        l = new List<>();
    }

    public Boolean isEmpty(){
        l.toFirst();
        if(l.hasAccess()) return false;
        else return true;
    }

    private Boolean getItem(Produkt p){
        l.toFirst();
        while (l.hasAccess() && l.getContent().getKey() != p) {
            l.next();
        }
        if (l.hasAccess()) return true;
        else return false;
    }

    public void clearList(){
        l = new List<>();
    }

    public void addItem(Produkt p, int i) {
        if (getItem(p)) { //Wenn es das Produkt bereits im Einkaufswagen gibt, wird die Menge um i erhöht
            l.getContent().setValue(l.getContent().getValue() + i);
        } else {
            l.append(new Paar<>(p, i));
        }
    }

    public void removeItem(Paar<Produkt, Integer> p){
            l.toFirst();
            while (l.hasAccess() && l.getContent().getKey() != p.getKey()) {
                l.next();
            }
            if (l.hasAccess()) {
                l.remove();
            }

    }

    public void editItem(Produkt p, int i){
        l.toFirst();
        while (l.hasAccess() && l.getContent().getKey() != p) {
            l.next();
        }
        if (l.hasAccess()) {
            l.getContent().setValue(i);
            if(l.getContent().getValue() <= 0){ //Sollte die Menge auf 0 oder kleiner gesetzt worden sein, wird der Artikel entfernt
                l.remove();
            }
        }
    }

    public int getSumme() { //Errechnet die Summe aus allen Artikel im Einkaufswagen
        int summe = 0;
        for (l.toFirst(); l.hasAccess(); l.next()) {
            summe = summe + (Integer.parseInt(l.getContent().getKey().getPreis()) * l.getContent().getValue());
        }
        return summe;
    }

    public List<Paar<Produkt, Integer>> getL(){
        return l;
    }
}
