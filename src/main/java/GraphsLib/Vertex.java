package GraphsLib;

public class Vertex<L,V> {
    private int id;
    private L label;
    private V value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public L getLabel() {
        return label;
    }

    public void setLabel(L label) {
        this.label = label;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
