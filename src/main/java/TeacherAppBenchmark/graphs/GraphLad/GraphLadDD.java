package TeacherAppBenchmark.graphs.GraphLad;


import TeacherAppBenchmark.graphs.Edge;
import TeacherAppBenchmark.graphs.GraphDirectional;
import TeacherAppBenchmark.graphs.Vertice;

import java.util.LinkedList;


public class GraphLadDD extends GraphLad implements GraphDirectional {

    @Override
    public int outDegree(Vertice vertice) {
        VerticeLad vertexLad = this.vertices.findElement(vertice.getId());
        return vertexLad.myOutDegree();
    }

    @Override
    public int inDegree(Vertice vertice) {
        VerticeLad vertexLad = this.vertices.findElement(vertice.getId());
        return vertexLad.myInDegree();
    }

    @Override
    public LinkedList<Edge> inIncidentEdges(Vertice vertice) {
        VerticeLad vertexLad = this.vertices.findElement(vertice.getId());
        return (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesIn();
    }

    @Override
    public LinkedList<Edge> outIncidentEdges(Vertice vertice) {
        VerticeLad vertexLad = this.vertices.findElement(vertice.getId());
        return (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesOut();
    }

    @Override
    public LinkedList<Vertice> inAdjacentVertices(Vertice vertice) {
        VerticeLad vertexLad = this.vertices.findElement(vertice.getId());
        LinkedList<Vertice> lst = new LinkedList<>();

        for ( EdgeLad edgeLad: vertexLad.getEdgesIn()) {
            lst.add(edgeLad.myOpossite(vertexLad));
        }

        return lst;
    }

    @Override
    public LinkedList<Vertice> outAdjacentVertices(Vertice vertice) {
        VerticeLad vertexLad = this.vertices.findElement(vertice.getId());
        LinkedList<Vertice> lst = new LinkedList<>();

        for ( EdgeLad edgeLad: vertexLad.getEdgesOut()) {
            lst.add(edgeLad.myOpossite(vertexLad));
        }

        return lst;
    }

    @Override
    public Vertice destination(Edge edge) {
        EdgeLad edgeLad = edges.findElement(edge.getId());

        return edgeLad.getDestination();
    }

    @Override
    public Vertice origin(Edge edge) {
        EdgeLad edgeLad = edges.findElement(edge.getId());

        return edgeLad.getOrigin();
    }

}
