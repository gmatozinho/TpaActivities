package TeacherAppBenchmark.graphs;

import java.io.Serializable;

public class Header implements Serializable {
    public Header(String label, int address) {
        this.label = label;
        this.id = address;
    }

    private String label;
    private int id;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean equals(Object object2) {
        Header object = (Header)object2;
        if(this.id == object.getId() && this.label.equals(object.getLabel())) {
            return true;
        }
        else return false;
    }
}
