package TeacherAppBenchmark.graphs.GraphMad;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import TeacherAppBenchmark.graphs.Edge;
import TeacherAppBenchmark.graphs.GraphDirectional;
import TeacherAppBenchmark.graphs.Header;
import TeacherAppBenchmark.graphs.Vertice;
import WorkFilesLib.ArquivoTxt;

import java.util.LinkedList;

public class TADGrafoMadjDD extends TADGrafoMadj implements GraphDirectional {

    public TADGrafoMadjDD() {
    }

    public TADGrafoMadjDD(int length) {
        super(length);
    }

    @Override
    public int degree(Vertice vertice){
        return inDegree(vertice)+ outDegree(vertice);
    }

    public int outDegree(Vertice vertice) {
        int outDegree = 0;

        int row = findVertexPosByLabel(vertice.getLabel());
        for(int i = firstIndexMatrix; i<= lastIndexMatrix; i++)
            if(!lstVtxDelete.contains(i)){
                if(matrix[row][i] != null)
                    outDegree ++;
            }

        return outDegree;
    }

    public int inDegree(Vertice vertice) {

        int inDegree = 0;

        int col = findVertexPosByLabel(vertice.getLabel());
        for(int i = firstIndexMatrix; i<= lastIndexMatrix; i++)
            if(!lstVtxDelete.contains(i)){
                if(matrix[i][col] != null)
                    inDegree ++;
            }

        return inDegree;
    }

    @Override
    public Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value) {
        Header header1 = new Header(vertice1.getLabel(), vertice1.getId());
        Header header2 = new Header(vertice2.getLabel(), vertice2.getId());

        if(vertices.findElement(vertice1.getId()) == null || vertices.findElement(vertice2.getId()) == null)
        {
            return null;
        }

        Integer id = globalEdgeID++;
        String label = id.toString();

        Edge edge = new Edge(id,label,value);

        edges.insertItem(id,edge);

        int row = vertice1.getId();
        int column = vertice2.getId();

        matrix[row][column] = id;
        return  edge;
    }

    @Override
    public Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value, String label) {
        Header header1 = new Header(vertice1.getLabel(), vertice1.getId());
        Header header2 = new Header(vertice2.getLabel(), vertice2.getId());

        if(vertices.findElement(vertice1.getId()) == null || vertices.findElement(vertice2.getId()) == null)
        {
            return null;
        }

        Integer id = globalEdgeID++;

        Edge edge = new Edge(id,label,value);

        edges.insertItem(id,edge);

        int row = vertice1.getId();
        int column = vertice2.getId();

        matrix[row][column] = id;

        return  edge;
    }

    @Override
    public Object removeVertex(Vertice vertice) {
        Object tmp = vertice.getDado();

        int row = vertice.getId();
        int limitCol = vertices.size()+lstVtxDelete.size();
        lstVtxDelete.add(row);

        for(int i = 0; i < limitCol; i++){
            if( !lstVtxDelete.contains(i)) {
                Integer edgeLabel = matrix[row][i];
                if(edgeLabel!=null) {
                    edges.removeElement(edgeLabel);

                    matrix[row][i] = null;
                }
            }
        }

        vertices.removeElement(vertice.getId());

        return tmp;
    }

    @Override
    public Object removeEdge(Edge edge) {
        Object tmp = edge.getDado();
        String edgeLabel = edge.getLabel();
        edges.removeElement(edge.getId());

        String [] endPoints = endVertices(edge.getLabel());

        int row = findVertexPosByLabel(endPoints[0]);
        int column = findVertexPosByLabel(endPoints[1]);

        matrix[row][column] = null;

        return tmp;
    }

    public LinkedList<Edge> inIncidentEdges(Vertice vertice) {
        LinkedList<Edge> lst = new LinkedList<>();
        int col = findVertexPosByLabel(vertice.getLabel());
        incidentEdges(col,lst,false);

        return lst;
    }

    public LinkedList<Edge> outIncidentEdges(Vertice vertice) {
        LinkedList<Edge> lst = new LinkedList<>();
        int row = findVertexPosByLabel(vertice.getLabel());
        incidentEdges(row,lst,true);

        return lst;
    }

    @Override
    public LinkedList<Edge> incidentEdges(Vertice vertice) {
        LinkedList<Edge> edges = inIncidentEdges(vertice);
        edges.addAll(outIncidentEdges(vertice));

        return edges;
    }

    private void incidentEdges(int pos, LinkedList<Edge> lst, boolean row )
    {
        for(int i = firstIndexMatrix; i<= lastIndexMatrix; i++) {
            if (!lstVtxDelete.contains(i)) {
                Integer label;
                Header header;
                if(row) {
                    if (matrix[pos][i] != null) {
                        label = matrix[pos][i];
                        lst.add(edges.findElement(label));
                    }
                }else
                {
                    if (matrix[i][pos] != null) {
                        label = matrix[i][pos];
                        lst.add(edges.findElement(label));
                    }
                }
            }
        }

    }

    public LinkedList<Vertice> inAdjacentVertices(Vertice vertice) {
        return adjacentVertices(vertice,false);
    }

    public  LinkedList<Vertice> outAdjacentVertices(Vertice vertice) {
        return adjacentVertices(vertice,true);
    }

    @Override
    public LinkedList<Vertice> adjacenteVertices(Vertice vertice) {
        LinkedList<Vertice> edges = inAdjacentVertices(vertice);
        edges.addAll(outAdjacentVertices(vertice));

        return edges;
    }

    private LinkedList<Vertice> adjacentVertices(Vertice vertice, boolean row)
    {
        Header headerToFind = new Header(vertice.getLabel(), vertice.getId());

        if(vertices.findElement(vertice.getId()) == null)
            return null;

        LinkedList<Vertice> lst = new LinkedList<>();

        int pos = vertice.getId();

        for(int i = firstIndexMatrix; i<=lastIndexMatrix; i++) {
            if(row) {
                if (!lstVtxDelete.contains(i) && (matrix[pos][i] != null)) {
                    String label = findVertexLabelById(i);
                    lst.add(vertices.findElement(i));
                }
            }else {
                if (!lstVtxDelete.contains(i) && (matrix[i][pos] != null)) {
                    String label = findVertexLabelById(i);
                    lst.add(vertices.findElement(i));
                }
            }
        }

        return lst;
    }

    public Vertice destination(Edge edge) {
        return findOriginOrDestiny(edge,false);
    }

    public Vertice origin(Edge edge) {
        return findOriginOrDestiny(edge,true);
    }

    private Vertice findOriginOrDestiny(Edge edge, boolean origin)
    {
        for(int i=firstIndexMatrix; i<lastIndexMatrix ;i++) {
            for (int j = 0; j < lastIndexMatrix; j++) {
                if(origin) {
                    if (!lstVtxDelete.contains(i) && (matrix[i][j] != null) && (matrix[i][j].equals(edge.getLabel()))) {
                        String label = findVertexLabelById(i);
                        return vertices.findElement(i);
                    }
                }else{
                    if (!lstVtxDelete.contains(j) && (matrix[i][j] != null) && (matrix[i][j].equals(edge.getLabel()))) {
                        String label = findVertexLabelById(j);
                        return vertices.findElement(j);
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
    public static TADGrafoMadjDD load(String nome_arq_TGF){
        TADGrafoMadjDD graph = new TADGrafoMadjDD();

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

                            if(!edges.findElement(matrix[lin][col]).getLabel().substring(0,2).equals("@#")) {
                                row = tgf_lin + " " + tgf_col + " " + edges.findElement(matrix[lin][col]).getLabel();
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
