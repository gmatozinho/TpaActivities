package TeacherAppBenchmark.graphs;

import java.util.LinkedList;

public interface GraphDirectional {
    public int outDegree(Vertice vertice);

    public int inDegree(Vertice vertice);
    LinkedList<Edge> inIncidentEdges(Vertice vertice);
    LinkedList<Edge> outIncidentEdges(Vertice vertice);
    LinkedList<Vertice> inAdjacentVertices(Vertice vertice);
    LinkedList<Vertice> outAdjacentVertices(Vertice vertice);
    Vertice destination(Edge edge);
    Vertice origin(Edge edge);
}
