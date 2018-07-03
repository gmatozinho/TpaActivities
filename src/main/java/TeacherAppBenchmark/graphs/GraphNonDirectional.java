package TeacherAppBenchmark.graphs;

import java.util.LinkedList;

public interface GraphNonDirectional {
    LinkedList<Edge> incidentEdges(Vertice vertice);
    LinkedList<Vertice> adjacenteVertices(Vertice vertice);

}
