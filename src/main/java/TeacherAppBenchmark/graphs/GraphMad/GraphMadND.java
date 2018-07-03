package TeacherAppBenchmark.graphs.GraphMad;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import TeacherAppBenchmark.graphs.Edge;
import TeacherAppBenchmark.graphs.GraphNonDirectional;
import TeacherAppBenchmark.graphs.Header;
import TeacherAppBenchmark.graphs.Vertice;
import WorkFilesLib.ArquivoTxt;

import java.util.LinkedList;


public class GraphMadND extends GraphMad implements GraphNonDirectional {

    public GraphMadND() {
    }

    public GraphMadND(int length) {
        super(length);
    }

    @Override
    public int degree(Vertice v){
        int degree = 0;

        int row = v.getId();

        for(int i = firstIndexMatrix; i<= lastIndexMatrix; i++)
            if(!lstVtxDelete.contains(i) && (matrix[row][i]!= null))
                degree++;

        return degree;
    }

    @Override
    public Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value) {
        Header header1 = new Header(vertice1.getLabel(), vertice1.getId());
        Header header2 = new Header(vertice2.getLabel(), vertice2.getId());

        if(dicVertices.findElement(header1) == null || dicVertices.findElement(header2) == null)
        {
            return null;
        }

        Integer id = globalEdgeID++;
        String label = id.toString();

        Edge edge = new Edge(id,label,value);

        dicEdges.insertItem(new Header(label,id),edge);

        int row = vertice1.getId();
        int column = vertice2.getId();

        matrix[row][column] = edge.getLabel();
        matrix[column][row] = edge.getLabel();

        return  edge;
    }

    @Override
    public Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value, String label) {
        Header header1 = new Header(vertice1.getLabel(), vertice1.getId());
        Header header2 = new Header(vertice2.getLabel(), vertice2.getId());

        if(dicVertices.findElement(header1) == null || dicVertices.findElement(header2) == null)
        {
            return null;
        }

        Integer id = globalEdgeID++;

        Edge edge = new Edge(id,label,value);

        dicEdges.insertItem(new Header(label,id),edge);

        int row = vertice1.getId();
        int column = vertice2.getId();

        matrix[row][column] = edge.getLabel();
        matrix[column][row] = edge.getLabel();

        return  edge;
    }

    @Override
    public Object removeVertex(Vertice vertice) {
        Object tmp = vertice.getValue();

        int row = vertice.getId();
        int limitCol = dicVertices.size();
        lstVtxDelete.add(row);

        for(int i = 0; i < limitCol; i++){
            if( !lstVtxDelete.contains(i)) {
                String edgeLabel = matrix[row][i];
                if(edgeLabel!=null) {
                    int pos = findEdgePosByLabel(edgeLabel);
                    dicEdges.removeElement(new Header(edgeLabel, pos));

                    matrix[row][i] = null;
                    matrix[i][row] = null;
                }
            }
        }

        return tmp;
    }

    @Override
    public Object removeEdge(Edge edge) {
        Object tmp = edge.getValue();
        String edgeLabel = edge.getLabel();
        dicEdges.removeElement(new Header(edgeLabel,edge.getId()));

        String [] endPoints = endVertices(edge.getLabel());

        int row = findVertexPosByLabel(endPoints[0]);
        int column = findVertexPosByLabel(endPoints[1]);

        matrix[row][column] = null;
        matrix[column][row] = null;

        return tmp;
    }

    public LinkedList<Edge> incidentEdges(Vertice vertice){
        LinkedList<Edge> lst = new LinkedList<>();

        int row = findVertexPosByLabel(vertice.getLabel());
        for(int i = firstIndexMatrix; i<= lastIndexMatrix; i++) {
            if (!lstVtxDelete.contains(i)) {
                if (matrix[row][i] != null) {
                    String label = matrix[row][i];
                    Header header = new Header(label, findEdgePosByLabel(label));
                    lst.add(dicEdges.findElement(header));
                }
            }
        }

        return lst;
    }

    public LinkedList<Vertice> adjacenteVertices(Vertice vertice){
        Header headerToFind = new Header(vertice.getLabel(), vertice.getId());

        if(dicVertices.findElement(headerToFind) == null)
            return null;

        LinkedList<Vertice> lst = new LinkedList();
        int row = vertice.getId();

        for (int i = firstIndexMatrix; i <= lastIndexMatrix; i++) {
            if(!lstVtxDelete.contains(i) && matrix[row][i] != null){
                String label = findVertexLabelById(i);
                lst.add(dicVertices.findElement(new Header(label,i)));
            }
        }

        return lst;
    }

    /*
     *  Exemplo de uso:
     *  TGrafoNDMAd g = TGrafoNDMAd.load("nomeArqTGF.txt");
     * */
    public static GraphMadND load(String nome_arq_TGF){
        GraphMadND graph = new GraphMadND();

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

        for(int i = this.firstIndexMatrix; i<= this.lastIndexMatrix; i++){
            if(!lstVtxDelete.contains(i)){
                row = id + " " + findVertexLabelById(i);
                strGrafo+=(row);
                strGrafo+=("\n");

                dicIDgrafoID_tgf.insertItem(i,id);

                id++;
            }
        }
        strGrafo+=("#");
        strGrafo+=("\n");

        /* escrevendo as arestas */
        for(int lin =firstIndexMatrix; lin<=lastIndexMatrix; lin++){
            if(!lstVtxDelete.contains(lin)){
                for (int col = lin; col <= lastIndexMatrix; col++){
                    if(!lstVtxDelete.contains(col)){
                        if(matrix[lin][col] != null){
                            int tgf_lin = dicIDgrafoID_tgf.findElement(lin);
                            int tgf_col = dicIDgrafoID_tgf.findElement(col);

                            if(!matrix[lin][col].substring(0,2).equals("@#")) {
                                row = tgf_lin + " " + tgf_col + " " + matrix[lin][col];
                            }else{
                                row = tgf_lin + " " + tgf_col;
                            }

                            strGrafo+=(row);
                            strGrafo+=("\n");
                        }
                    }
                }
            }
        }


        return strGrafo;
    }

}
