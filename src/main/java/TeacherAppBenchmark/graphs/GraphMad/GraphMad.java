package TeacherAppBenchmark.graphs.GraphMad;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import TeacherAppBenchmark.graphs.Edge;
import TeacherAppBenchmark.graphs.Graph;
import TeacherAppBenchmark.graphs.Vertice;
import WorkFilesLib.ArquivoTxt;

import java.util.LinkedList;

public class GraphMad extends Graph {

    // Dicionários com chave Header(Label,ID) e conteúdo objeto Vertice/Edge.
    protected MyHash<Integer,Vertice> dicVertices = new MyHashListChain<>();
    protected MyHash<Integer,Edge> dicEdges = new MyHashListChain<>();

    protected LinkedList<Integer> lstVtxDelete = new LinkedList();

    protected int globalVertexID = 0;
    protected int globalEdgeID = 0;
    protected String matrix[][];

    protected static final int defaultsize = 64;

    protected int firstIndexMatrix = 0;
    protected int lastIndexMatrix = -1;

    //Cria um grafo de tamanho Default com 64 nós.
    public GraphMad ()
    {
        matrix = new String[defaultsize][defaultsize];
    }

    //Cria um grafo de tamanho Custom com N nós.
    public GraphMad(int length)
    {
        matrix = new String[length][length];
    }

    @Override
    public int numVertices() {
        return dicVertices.size();
    }

    @Override
    public LinkedList<Vertice> vertices() {
        return dicVertices.values();
    }

    @Override
    public int numEdges() {
        return dicEdges.size();
    }

    @Override
    public LinkedList<Edge> edges() {
        return dicEdges.values();
    }

    //Get edge - se a posição do vertex1 for encontrada eu busco o 2 senao retorno nulo o mesmo vale pro vertex 2;
    @Override
    public Object getEdge(String vertex1, String vertex2) {
        int vertex1Pos = findVertexPosByLabel(vertex1);
        int vertex2Pos;
        if(vertex1Pos != -1)
        {
            vertex2Pos = findVertexPosByLabel(vertex2);
            if(vertex2Pos != -1){
                return matrix[vertex1Pos][vertex2Pos];
            }
        }

        return null;
    }

    @Override
    public String[] endVertices(String edge) {
        int tam = numVertices();
        for (int i = 0; i< tam; i++){
            for(int j = 0; j < tam; j++){
                if(matrix[i][j] != null && matrix[i][j].equals(edge)){
                    String labelVertex1 = findVertexLabelById(i);
                    String labelVertex2;
                    if(!labelVertex1.equals(""))
                    {
                        labelVertex2 = findVertexLabelById(j);
                        if(!labelVertex2.equals(""))
                        {
                            return new String[] {labelVertex2,labelVertex1};
                        }
                    }
                }
            }
        }

        return null;
    }

    @Override
    public String opossite(String vertex, String edge) {
        if(findVertexPosByLabel(vertex)==-1) return null;
        if(findEdgePosByLabel(edge)==-1)return null;

        String endPoints[] = endVertices(edge);

        if(endPoints != null) {
            if (vertex.equals(endPoints[0])) {
                return endPoints[1];
            } else
            if(vertex.equals(endPoints[1])) {
                return endPoints[0];
            }
        }

        return null;
    }

    @Override
    public Vertice insertVertex(Object value) {
        if(dicVertices.size()/matrix[0].length >= 0.75f)
            resize();

        Integer id = generateVertexId();
        String label = id.toString();

        if ((id < findFirstRowColUtil()) ||(findFirstRowColUtil() == -1)) {
            firstIndexMatrix = id;
        }
        if((id > findLastRowColUtil())) {
            lastIndexMatrix = id;
        }

        Vertice vertice = new Vertice(id,label, value);
        dicVertices.insertItem(id, vertice);
        return vertice;
    }

    @Override
    public Vertice insertVertex(Object value, String label)
    {
        if(dicVertices.size()/matrix[0].length >= 0.75f)
            resize();

        Integer id = generateVertexId();

        if ((id < findFirstRowColUtil()) ||(findFirstRowColUtil() == -1)) {
            firstIndexMatrix = id;
        }
        if((id > findLastRowColUtil())) {
            lastIndexMatrix = id;
        }

        Vertice vertice = new Vertice(id,label, value);

        dicVertices.insertItem(id, vertice);
        return vertice;
    }

    @Override
    public Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value) {
        return null;
    }


    @Override
    public Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value, String label) {
        return null;
    }

    @Override
    public Object removeVertex(Vertice vertice) {
        return null;
    }

    @Override
    public Object removeEdge(Edge edge) {
        return null;
    }

    @Override
    public boolean areAdjacent(Vertice vertice1, Vertice vertice2)
    {
        int row = vertice1.getId();
        int column = vertice2.getId();

        return matrix[row][column] != null;
    }

    @Override
    public int degree(Vertice vertice) {
        return 0;
    }

    /*******Funções utilitárias********/

    protected int findVertexPosByLabel(String label)
    {
        for ( Vertice vertice: dicVertices.values()) {
            if(vertice.getLabel().equals(label))
            {
                return vertice.getId();
            }
        }
        return -1;
    }

    protected String findVertexLabelById(int id)
    {
        for ( Vertice vertice: dicVertices.values()) {
            if(vertice.getId()==id)
            {
                return vertice.getLabel();
            }
        }
        return "";
    }

    protected int findEdgePosByLabel(String label)
    {
        for (Edge edge: dicEdges.values()) {
            if(edge.getLabel().equals(label))
            {
                return edge.getId();
            }
        }
        return -1;
    }

    protected int generateVertexId(){
        if(lstVtxDelete.size() > 0){
            int id = lstVtxDelete.get(0);
            lstVtxDelete.remove(0);
            return id;
        }
        else
            return globalVertexID++;
    }

    protected int findFirstRowColUtil(){
        int i = firstIndexMatrix +1;
        while(lstVtxDelete.contains(i) && (i<= lastIndexMatrix))
            i=i+1;
        if(!lstVtxDelete.contains(i))
            return i;

        return lastIndexMatrix;
    }

    protected int findLastRowColUtil(){
        int i = lastIndexMatrix - 1;
        while(lstVtxDelete.contains(i) && (i>= firstIndexMatrix))
            i = i-1;

        if(!lstVtxDelete.contains(i))
            return i;

        return firstIndexMatrix;
    }

    protected void resize(){
        int newSize = (int)(matrix[0].length * 1.5f);
        String newMat[][] = new String[newSize][newSize];

        for (int i = firstIndexMatrix; i <= lastIndexMatrix; i++) {
            for (int j = firstIndexMatrix; j <= lastIndexMatrix; j++) {
                newMat[i][j] = matrix[i][j];
            }
        }

        matrix = newMat;

    }

    protected static void loadCommon(GraphMad graph, ArquivoTxt arq){
        /* lendo os vertices */
        String row = arq.readline();
        while (!row.trim().equals("#")){
            String[] vector = row.split(" ", 2);
            if(vector.length>1) {
                graph.insertVertex(null, vector[1].trim());
            }

            row = arq.readline();
        }

        /* lendo as arestas */
        row = arq.readline();
        while (row!= null){
            String[] edges = row.split(" ", 3);

            int idVertex1 = Integer.parseInt(edges[0].trim()) - 1;
            int idVertex2 = Integer.parseInt(edges[1].trim()) - 1;

            Vertice vertice1 = graph.dicVertices.findElement(idVertex1);
            Vertice vertice2 = graph.dicVertices.findElement(idVertex2);

            String label="";

            if(edges.length == 3) {
                label = (edges[2].trim());
            }
            if(label.equals("")) {
                label = ("@#" + (graph.globalEdgeID+1));
            }

            graph.insertEdge(vertice1, vertice2, null, label);

            row = arq.readline();
        }

    }

    public String save(String nome_arq_TGF){

        ArquivoTxt arq = ArquivoTxt.open(nome_arq_TGF, "wt");

        arq.write(this.toString());

        arq.close();

        return nome_arq_TGF;
    }

}
