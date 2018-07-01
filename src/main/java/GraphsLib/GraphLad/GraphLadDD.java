package GraphsLib.GraphLad;


import GraphsLib.Edge;
import GraphsLib.GraphDirectional;
import GraphsLib.Vertex;

import java.util.LinkedList;


public class GraphLadDD extends GraphLad implements GraphDirectional {

    @Override
    public int outDegree(Vertex vertex) {
        VertexLad vertexLad = vertices.findElement(vertex.getLabel());
        return vertexLad.myOutDegree();
    }

    @Override
    public int inDegree(Vertex vertex) {
        VertexLad vertexLad = vertices.findElement(vertex.getLabel());
        return vertexLad.myInDegree();
    }

    @Override
    public LinkedList<Edge> inIncidentEdges(Vertex vertex) {
        VertexLad vertexLad = vertices.findElement(vertex.getLabel());
        return (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesIn();
    }

    @Override
    public LinkedList<Edge> outIncidentEdges(Vertex vertex) {
        VertexLad vertexLad = vertices.findElement(vertex.getLabel());
        return (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesOut();
    }

    @Override
    public LinkedList<Vertex> inAdjacentVertices(Vertex vertex) {
        VertexLad vertexLad = vertices.findElement(vertex.getLabel());
        LinkedList<Vertex> lst = new LinkedList<>();

        for ( EdgeLad edgeLad: vertexLad.getEdgesIn()) {
            lst.add(edgeLad.myOpossite(vertexLad));
        }

        return lst;
    }

    @Override
    public LinkedList<Vertex> outAdjacentVertices(Vertex vertex) {
        VertexLad vertexLad = vertices.findElement(vertex.getLabel());
        LinkedList<Vertex> lst = new LinkedList<>();

        for ( EdgeLad edgeLad: vertexLad.getEdgesOut()) {
            lst.add(edgeLad.myOpossite(vertexLad));
        }

        return lst;
    }

    @Override
    public Vertex destination(Edge edge) {
        EdgeLad edgeLad = edges.findElement(edge.getLabel());

        return edgeLad.getDestination();
    }

    @Override
    public Vertex origin(Edge edge) {
        EdgeLad edgeLad = edges.findElement(edge.getLabel());

        return edgeLad.getOrigin();
    }

}
