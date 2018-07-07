package TeacherAppBenchmark.graphs.GraphLad;


import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import TeacherAppBenchmark.graphs.Edge;
import TeacherAppBenchmark.graphs.GraphDirectional;
import TeacherAppBenchmark.graphs.Vertice;
import WorkFilesLib.ArquivoTxt;

import java.util.LinkedList;


public class TADGrafoLadjDD extends TADGrafoLadj implements GraphDirectional {

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
    public LinkedList<Edge> incidentEdges(Vertice vertice) {
        LinkedList<Edge> edges = inIncidentEdges(vertice);
        edges.addAll(outIncidentEdges(vertice));

        return edges;
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
    public LinkedList<Vertice> adjacenteVertices(Vertice vertice) {
        LinkedList<Vertice> edges = inAdjacentVertices(vertice);
        edges.addAll(outAdjacentVertices(vertice));

        return edges;
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

    public static TADGrafoLadjDD load(String nome_arq_TGF){
        TADGrafoLadjDD graph = new TADGrafoLadjDD();

        ArquivoTxt arq = ArquivoTxt.open(nome_arq_TGF, "rt");

        assert arq != null;
        loadCommon(graph,arq);

        arq.close();

        return graph;

    }

    public String toString(){

        MyHash<Integer,Integer> dicIDgrafoID_tgf = new MyHashListChain();
        /* Escrevendo os vertices */
        String strGrafo = "";
        int id = 1;

        String row;

        for(int i = 0; i<  vertices.size(); i++){
            row = id + " " + vertices.findElement(i).getLabel();
            strGrafo+=(row);
            strGrafo+=("\n");

            dicIDgrafoID_tgf.insertItem(i,id);

            id++;
        }
        strGrafo+=("#");
        strGrafo+=("\n");

        /* escrevendo as arestas */
        for (EdgeLad edge: edges.values()) {
            if(!edge.getLabel().substring(0,2).equals("@#")) {
                row = (edge.getOrigin().getId()+1) + " " + (edge.getDestination().getId()+1) + " " + edge.getLabel();
            }else{
                row = (edge.getOrigin().getId()+1) + " " + (edge.getDestination().getId()+1);
            }
            strGrafo+=(row);
            strGrafo+=("\n");

        }
        return strGrafo;
    }

}
