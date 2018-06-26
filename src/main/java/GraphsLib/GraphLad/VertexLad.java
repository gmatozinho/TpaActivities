package GraphsLib.GraphLad;

import GraphsLib.Vertex;

import java.util.LinkedList;

public class VertexLad extends Vertex {

    public VertexLad(int id, String label, Object value) {
        super(id, label, value);
    }

    LinkedList<EdgeLad> in = new LinkedList() {};
    LinkedList<EdgeLad> out = new LinkedList() {};
}
