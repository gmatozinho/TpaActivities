package GraphsLib;

import java.util.LinkedList;

public abstract class MyGraph {
    public abstract int numVertices();
    public abstract LinkedList<Vertex> vertices();
    public abstract int numEdges();
    public abstract LinkedList<Edge> edges();
    public abstract Edge getEdge(Vertex vertex1, Vertex vertex2);
    public abstract Vertex[] endVertices(Edge edge);
    public abstract Vertex opossite(String vertex,String edge);
    public abstract int outDegree(Vertex vertex);
    public abstract int inDegree(Vertex vertex);
    public abstract Object outgoingEdges(Vertex vertex);
    public abstract Object incomingEdges(Vertex vertex);
    public abstract Vertex insertVertex(Object value);
    public abstract Edge insertEdge(Vertex vertex1,Vertex vertex2, Object value);
    public abstract Object removeVertex(Vertex vertex);
    public abstract Object removeEdge(Edge edge);

}
