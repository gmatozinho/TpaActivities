package TeacherAppBenchmark.graphs;

import java.util.LinkedList;

public abstract class Graph {
    public abstract int numVertices();
    public abstract LinkedList<Vertice> vertices();
    public abstract int numEdges();
    public abstract LinkedList<Edge> edges();
    public abstract Object getEdge(String vertex1, String vertex2);
    public abstract String[] endVertices(String edge);
    public abstract String opossite(String vertex, String edge);
    public abstract Vertice insertVertex(Object value);
    public abstract Vertice insertVertex(Object value, String label);
    public abstract Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value);
    public abstract Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value, String label);
    public abstract Object removeVertex(Vertice vertice);
    public abstract Object removeEdge(Edge edge);
    public abstract boolean areAdjacent(Vertice vertice1, Vertice vertice2);
    public abstract int degree(Vertice vertice);

}
