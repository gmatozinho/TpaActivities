package GraphsLib.GraphLad;


import GraphsLib.Edge;
import GraphsLib.GraphDirectional;
import GraphsLib.Vertex;

import java.util.LinkedList;


public class GraphLadDD extends GraphLad implements GraphDirectional {
    LinkedList<VertexLad> vertices = new LinkedList<>();
    LinkedList<EdgeLad> edges = new LinkedList<>();

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

    public int outDegree(Vertex vertex) {
        return 0;
    }

    public int inDegree(Vertex vertex) {
        return 0;
    }

    public Object outgoingEdges(Vertex vertex) {
        return null;
    }

    public Object incomingEdges(Vertex vertex) {
        return null;
    }

    @Override
    public LinkedList<Edge> inIncidentEdges(Vertex vertex) {
        return null;
    }

    @Override
    public LinkedList<Edge> outIncidentEdges(Vertex vertex) {
        return null;
    }

    @Override
    public LinkedList<Vertex> inAdjacentVertices(Vertex vertex) {
        return null;
    }

    @Override
    public LinkedList<Vertex> outAdjacentVertices(Vertex vertex) {
        return null;
    }

    @Override
    public Vertex destination(Edge edge) {
        return null;
    }

    @Override
    public Vertex origin(Edge edge) {
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
