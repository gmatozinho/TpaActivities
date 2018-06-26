package GraphsLib.GraphLad;

import GraphsLib.Edge;
import GraphsLib.Vertex;

import java.util.LinkedList;

public class GraphLadND extends GraphLad {
    @Override
    public int numVertices() {
        return 0;
    }

    @Override
    public LinkedList<String> vertices() {
        return null;
    }

    @Override
    public int numEdges() {
        return 0;
    }

    @Override
    public LinkedList<String> edges() {
        return null;
    }

    @Override
    public Object getEdge(String vertex1, String vertex2) {
        return null;
    }

    @Override
    public String[] endVertices(String edge) {
        return new String[0];
    }

    @Override
    public String opossite(String vertex, String edge) {
        return null;
    }

    @Override
    public int outDegree(Vertex vertex) {
        return 0;
    }

    @Override
    public int inDegree(Vertex vertex) {
        return 0;
    }

    @Override
    public Object outgoingEdges(Vertex vertex) {
        return null;
    }

    @Override
    public Object incomingEdges(Vertex vertex) {
        return null;
    }

    @Override
    public Vertex insertVertex(Object value) {
        return null;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, Object value) {
        return null;
    }

    @Override
    public Object removeVertex(Vertex vertex) {
        return null;
    }

    @Override
    public Object removeEdge(Edge edge) {
        return null;
    }
}
