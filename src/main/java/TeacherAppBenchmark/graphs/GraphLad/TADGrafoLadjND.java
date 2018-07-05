package TeacherAppBenchmark.graphs.GraphLad;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import TeacherAppBenchmark.graphs.Edge;
import TeacherAppBenchmark.graphs.GraphNonDirectional;
import TeacherAppBenchmark.graphs.Vertice;
import WorkFilesLib.ArquivoTxt;

import java.util.LinkedList;

public class TADGrafoLadjND extends TADGrafoLadj implements GraphNonDirectional {

    @Override
    public LinkedList<Edge> incidentEdges(Vertice vertice) {
        VerticeLad vertexLad = this.vertices.findElement(vertice.getId());
        LinkedList<Edge> incoming = (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesIn();
        LinkedList<Edge> outgoing = (LinkedList<Edge>)(LinkedList<?>)vertexLad.getEdgesOut();

        incoming.addAll(outgoing);

        return incoming;
    }

    @Override
    public LinkedList<Vertice> adjacenteVertices(Vertice vertice) {
        VerticeLad vertexLad = this.vertices.findElement(vertice.getId());
        LinkedList<Vertice> lst = new LinkedList<>();

        for ( EdgeLad edgeLad: vertexLad.getEdges()) {
            lst.add(edgeLad.myOpossite(vertexLad));
        }

        return lst;
    }

    public static TADGrafoLadjND carrega(String nome_arq_TGF){
        TADGrafoLadjND graph = new TADGrafoLadjND();

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
