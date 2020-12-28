public class Paar <p, i>{
    private p p;
    private i i;

    public Paar (p p, i i){
        this.p = p;
        this.i = i;
    }


    public p getKey() {
        return p;
    }

    public void setKey(p p) {
        this.p = p;
    }

    public i getValue() {
        return i;
    }

    public void setValue(i i) {
        this.i = i;
    }
}
