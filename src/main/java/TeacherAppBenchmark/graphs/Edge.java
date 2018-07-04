package TeacherAppBenchmark.graphs;

public class Edge<L,V> {
    private int id;
    private String label;
    private Object dado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Object getDado() {
        return dado;
    }

    public void setDado(Object dado) {
        this.dado = dado;
    }

    public Edge(int id, String label, Object dado) {
        this.id = id;
        this.label = label;
        this.dado = dado;
    }

    @Override
    public boolean equals(Object obj) {
        Edge object = (Edge) obj;
        return this.id == object.getId() && this.label.equals(object.getLabel()) && this.dado == object.dado;
    }

    @Override
    public String toString() {
        return "ID: " + id + "; " + "Label: " + label + "; " + "Value: " + dado;
    }
}
