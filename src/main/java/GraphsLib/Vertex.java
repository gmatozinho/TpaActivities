package GraphsLib;

public class Vertex {
    private int id;
    private String label;
    private Object value;

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Vertex(int id, String label, Object value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }


    @Override
    public String toString() {
        return "ID: " + id + "; " + "Label: " + label + "; " + "Value: " + value;
    }
}
