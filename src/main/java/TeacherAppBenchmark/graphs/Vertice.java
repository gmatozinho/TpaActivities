package TeacherAppBenchmark.graphs;

public class Vertice {
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

    public Vertice(int id, String label, Object value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }


    @Override
    public String toString() {
        return "ID: " + id + "; " + "Label: " + label + "; " + "Value: " + value;
    }
}
