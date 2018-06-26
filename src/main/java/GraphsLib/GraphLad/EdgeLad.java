package GraphsLib.GraphLad;

import GraphsLib.Edge;

public class EdgeLad extends Edge {

    public EdgeLad(int id, String label, Object value) {
        super(id, label, value);
    }

    VertexLad in;
    VertexLad out;


}
