package GraphsLib.GraphLad;

import GraphsLib.Edge;
import GraphsLib.GraphNonDirectional;
import GraphsLib.Vertex;

import java.util.LinkedList;

public class GraphLadND extends GraphLad implements GraphNonDirectional {

    @Override
    public LinkedList<Edge> incidentEdges(Vertex vertex) {
        VertexLad vertexLad = vertices.findElement(vertex.getLabel());
        LinkedList<Edge> incoming = (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesIn();
        LinkedList<Edge> outgoing = (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesOut();

        incoming.addAll(outgoing);

        return incoming;
    }

    @Override
    public LinkedList<Vertex> adjacentVertices(Vertex vertex) {
        VertexLad vertexLad = vertices.findElement(vertex.getLabel());
        LinkedList<Vertex> lst = new LinkedList<>();

        for ( EdgeLad edgeLad: vertexLad.getEdges()) {
            lst.add(edgeLad.myOpossite(vertexLad));
        }

        return lst;
    }
}

