package TeacherAppBenchmark.graphs.GraphLad;

import TeacherAppBenchmark.graphs.Edge;
import TeacherAppBenchmark.graphs.GraphNonDirectional;
import TeacherAppBenchmark.graphs.Vertice;

import java.util.LinkedList;

public class TADGrafoLadjND extends GraphLad implements GraphNonDirectional {

    @Override
    public LinkedList<Edge> incidentEdges(Vertice vertice) {
        VerticeLad vertexLad = this.vertices.findElement(vertice.getLabel());
        LinkedList<Edge> incoming = (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesIn();
        LinkedList<Edge> outgoing = (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesOut();

        incoming.addAll(outgoing);

        return incoming;
    }

    @Override
    public LinkedList<Vertice> adjacenteVertices(Vertice vertice) {
        VerticeLad vertexLad = this.vertices.findElement(vertice.getLabel());
        LinkedList<Vertice> lst = new LinkedList<>();

        for ( EdgeLad edgeLad: vertexLad.getEdges()) {
            lst.add(edgeLad.myOpossite(vertexLad));
        }

        return lst;
    }
}
