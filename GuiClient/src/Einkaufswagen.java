import datenstrukturklassen.linear.List;

public class Einkaufswagen {
    private List<Paar<Produkt, Integer>> l;
    private int Summe;

    public Einkaufswagen() {
        l = new List<>();
    }

    public Boolean isEmpty(){
        l.toFirst();
        if(l.hasAccess()) return true;
        else return false;
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
        if (getItem(p)) {
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
            if(l.getContent().getValue() <= 0){
                l.remove();
            }
        }

    }

    public int getSumme() {
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
