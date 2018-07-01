package GraphsLib;

import java.util.LinkedList;

public interface GraphDirectional {
    public int outDegree(Vertex vertex);

    public int inDegree(Vertex vertex);
    LinkedList<Edge> inIncidentEdges(Vertex vertex);
    LinkedList<Edge> outIncidentEdges(Vertex vertex);
    LinkedList<Vertex> inAdjacentVertices(Vertex vertex);
    LinkedList<Vertex> outAdjacentVertices(Vertex vertex);
    Vertex destination(Edge edge);
    Vertex origin(Edge edge);
}
