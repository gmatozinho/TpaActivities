package GraphsLib;

import java.util.LinkedList;

public interface GraphNonDirectional {
    LinkedList<Edge> incidentEdges(Vertex vertex);
    LinkedList<Vertex> adjacentVertices(Vertex vertex);

}
