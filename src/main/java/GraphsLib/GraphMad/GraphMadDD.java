package GraphsLib.GraphMad;

import GraphsLib.Edge;
import GraphsLib.GraphDirectional;
import GraphsLib.Header;
import GraphsLib.Vertex;
import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import WorkFilesLib.ArquivoTxt;

import java.util.LinkedList;

public class GraphMadDD extends GraphMad implements GraphDirectional {

    public GraphMadDD() {
    }

    public GraphMadDD(int length) {
        super(length);
    }

    @Override
    public int degree(Vertex vertex){
        return inDegree(vertex)+ outDegree(vertex);
    }

    public int outDegree(Vertex vertex) {
        int outDegree = 0;

        int row = findVertexPosByLabel(vertex.getLabel());
        for(int i = firstIndexMatrix; i<= lastIndexMatrix; i++)
            if(!lstVtxDelete.contains(i)){
                if(matrix[row][i] != null)
                    outDegree ++;
            }

        return outDegree;
    }

    public int inDegree(Vertex vertex) {

        int inDegree = 0;

        int col = findVertexPosByLabel(vertex.getLabel());
        for(int i = firstIndexMatrix; i<= lastIndexMatrix; i++)
            if(!lstVtxDelete.contains(i)){
                if(matrix[i][col] != null)
                    inDegree ++;
            }

        return inDegree;
    }

    public Object outgoingEdges(Vertex vertex) {
        return null;
    }

    public Object incomingEdges(Vertex vertex) {
        return null;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, Object value) {
        Header header1 = new Header(vertex1.getLabel(),vertex1.getId());
        Header header2 = new Header(vertex2.getLabel(),vertex2.getId());

        if(dicVertices.findElement(header1) == null || dicVertices.findElement(header2) == null)
        {
            return null;
        }

        Integer id = globalEdgeID++;
        String label = id.toString();

        Edge edge = new Edge(id,label,value);

        dicEdges.insertItem(new Header(label,id),edge);

        int row = vertex1.getId();
        int column = vertex2.getId();

        matrix[row][column] = edge.getLabel();

        return  edge;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, Object value,String label) {
        Header header1 = new Header(vertex1.getLabel(),vertex1.getId());
        Header header2 = new Header(vertex2.getLabel(),vertex2.getId());

        if(dicVertices.findElement(header1) == null || dicVertices.findElement(header2) == null)
        {
            return null;
        }

        Integer id = globalEdgeID++;

        Edge edge = new Edge(id,label,value);

        dicEdges.insertItem(new Header(label,id),edge);

        int row = vertex1.getId();
        int column = vertex2.getId();

        matrix[row][column] = edge.getLabel();

        return  edge;
    }

    @Override
    public Object removeVertex(Vertex vertex) {
        Object tmp = vertex.getValue();

        int row = vertex.getId();
        int limitCol = dicVertices.size();
        lstVtxDelete.add(row);

        for(int i = 0; i < limitCol; i++){
            if( !lstVtxDelete.contains(i)) {
                String edgeLabel = matrix[row][i];
                if(edgeLabel!=null) {
                    int pos = findEdgePosByLabel(edgeLabel);
                    dicEdges.removeElement(new Header(edgeLabel, pos));

                    matrix[row][i] = null;
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

        return tmp;
    }

    public LinkedList<Edge> inIncidentEdges(Vertex vertex) {
        LinkedList<Edge> lst = new LinkedList<>();
        int col = findVertexPosByLabel(vertex.getLabel());
        incidentEdges(col,lst,false);

        return lst;
    }

    public LinkedList<Edge> outIncidentEdges(Vertex vertex) {
        LinkedList<Edge> lst = new LinkedList<>();
        int row = findVertexPosByLabel(vertex.getLabel());
        incidentEdges(row,lst,true);

        return lst;
    }

    private void incidentEdges(int pos, LinkedList<Edge> lst, boolean row )
    {
        for(int i = firstIndexMatrix; i<= lastIndexMatrix; i++) {
            if (!lstVtxDelete.contains(i)) {
                String label;
                Header header;
                if(row) {
                    if (matrix[pos][i] != null) {
                        label = matrix[pos][i];
                        header = new Header(label, findEdgePosByLabel(label));
                        lst.add(dicEdges.findElement(header));
                    }
                }else
                {
                    if (matrix[i][pos] != null) {
                        label = matrix[i][pos];
                        header = new Header(label, findEdgePosByLabel(label));
                        lst.add(dicEdges.findElement(header));
                    }
                }
            }
        }

    }

    public LinkedList<Vertex> inAdjacentVertices(Vertex vertex) {
        return adjacentVertices(vertex,false);
    }

    public  LinkedList<Vertex> outAdjacentVertices(Vertex vertex) {
        return adjacentVertices(vertex,true);
    }

    private LinkedList<Vertex> adjacentVertices(Vertex vertex, boolean row)
    {
        Header headerToFind = new Header(vertex.getLabel(),vertex.getId());

        if(dicVertices.findElement(headerToFind) == null)
            return null;

        LinkedList<Vertex> lst = new LinkedList<>();

        int pos = vertex.getId();

        for(int i = firstIndexMatrix; i<=lastIndexMatrix; i++) {
            if(row) {
                if (!lstVtxDelete.contains(i) && (matrix[pos][i] != null)) {
                    String label = findVertexLabelById(i);
                    lst.add(dicVertices.findElement(new Header(label, i)));
                }
            }else {
                if (!lstVtxDelete.contains(i) && (matrix[i][pos] != null)) {
                    String label = findVertexLabelById(i);
                    lst.add(dicVertices.findElement(new Header(label, i)));
                }
            }
        }

        return lst;
    }

    public Vertex destination(Edge edge) {
        return findOriginOrDestiny(edge,false);
    }

    public Vertex origin(Edge edge) {
        return findOriginOrDestiny(edge,true);
    }

    private Vertex findOriginOrDestiny(Edge edge, boolean origin)
    {
        for(int i=firstIndexMatrix; i<lastIndexMatrix ;i++) {
            for (int j = 0; j < lastIndexMatrix; j++) {
                if(origin) {
                    if (!lstVtxDelete.contains(i) && (matrix[i][j] != null) && (matrix[i][j].equals(edge.getLabel()))) {
                        String label = findVertexLabelById(i);
                        return dicVertices.findElement(new Header(label, i));
                    }
                }else{
                    if (!lstVtxDelete.contains(j) && (matrix[i][j] != null) && (matrix[i][j].equals(edge.getLabel()))) {
                        String label = findVertexLabelById(j);
                        return dicVertices.findElement(new Header(label, j));
                    }
                }
            }
        }

        return null;

    }

     /*
     *  Exemplo de uso:
     *  TGrafoNDMAd g = TGrafoNDMAd.load("nomeArqTGF.txt");
     * */
    public static GraphMadDD load(String nome_arq_TGF){
        GraphMadDD graph = new GraphMadDD();

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
                for (int col = 0; col <= lastIndexMatrix; col++){
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
