package GraphsLib.GraphND;

import GraphsLib.Edge;
import GraphsLib.Vertex;
import HashLib.Core.MyHash;

import java.util.LinkedList;

public class GraphNDMat<L,V> extends GraphND {

    private MyHash<String,Edge> matrix;

    @Override
    public int numVertices() {
        return numVertices;
    }

    @Override
    public LinkedList<Vertex> vertices() {
        return null;
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    @Override
    public LinkedList<Edge> edges() {
        return null;
    }

    @Override
    public Edge getEdge(Vertex vertex1, Vertex vertex2) {
        return null;
    }

    @Override
    public Vertex[] endVertices(Edge edge) {
        return new Vertex[0];
    }

    @Override
    public Vertex opossite(String v, String edge) {
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
    public Vertex insertVertex(Object x) {
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
