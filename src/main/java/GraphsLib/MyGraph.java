package GraphsLib;

public abstract class MyGraph<L,V> {
    public abstract int numVertices();
    public abstract Object vertices();
    public abstract int numEdges();
    public abstract Object edges();
    public abstract Edge getEdge(Vertex u, Vertex v);
    public abstract Vertex[] endVertices(Edge e);
    public abstract Vertex opossite(String v,String e);
    public abstract int outDegree(Vertex v);
    public abstract int inDegree(Vertex v);
    public abstract Object outgoingEdges(Vertex v);
    public abstract Object incomingEdges(Vertex v);
    public abstract Vertex insertVertex(Object x);
    public abstract Edge insertEdge(Vertex u,Vertex v, Object x);
    public abstract Object removeVertex(Vertex v);
    public abstract V removeEdge(Edge e);

}
