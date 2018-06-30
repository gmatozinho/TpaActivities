package GraphsLib.GraphLad;

import GraphsLib.Edge;
import GraphsLib.Graph;
import GraphsLib.Vertex;

import java.util.LinkedList;

public class GraphLad extends Graph {
    @Override
    public int numVertices() {
        return 0;
    }

    @Override
    public LinkedList<Vertex> vertices() {
        return null;
    }

    @Override
    public int numEdges() {
        return 0;
    }

    @Override
    public LinkedList<Edge> edges() {
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
    public Vertex insertVertex(Object value) {
        return null;
    }

    @Override
    public Vertex insertVertex(Object value, String label) {
        return null;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, Object value) {
        return null;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, Object value, String label) {
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

    @Override
    public boolean areaAdjacent(Vertex vertex1, Vertex vertex2) {
        return false;
    }

    @Override
    public int degree(Vertex vertex) {
        return 0;
    }
}
