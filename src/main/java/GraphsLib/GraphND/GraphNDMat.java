package GraphsLib.GraphND;

import GraphsLib.Edge;
import GraphsLib.Vertex;
import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import WorkFilesLib.ArquivoTxt;

import java.io.Serializable;
import java.util.LinkedList;

class Header implements Serializable {
    public Header(String label, int address) {
        this.label = label;
        this.id = address;
    }

    private String label;
    private int id;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean equals(Object object2) {
        Header object = (Header)object2;
        if(this.id == object.getId() && this.label == object.getLabel()) {
            return true;
        }
        else return false;
    }
}


public class GraphNDMat extends GraphND {

    // Dicionários com chave Header(Label,ID) e conteúdo objeto Vertex/Edge.
    private MyHash<Header,Vertex> dicVertices = new MyHashListChain<>();
    private MyHash<Header,Edge> dicEdges = new MyHashListChain<>();

    private LinkedList<Integer> lstVtxDelete = new LinkedList();

    private int globalVertexID = 0;
    private int globalEdgeID = 0;
    private String matrix[][];

    private static final int defaultsize = 64;

    private int firstIndexMatrix = 0;
    private int lastIndexMatrix = -1;


    //Cria um grafo de tamanho Default com 64 nós.
    public GraphNDMat()
    {
        matrix = new String[defaultsize][defaultsize];
        startMat(defaultsize);
    }

    //Cria um grafo de tamanho Custom com N nós.
    public GraphNDMat(int lenght)
    {
        matrix = new String[lenght][lenght];
        startMat(lenght);
    }

    private void startMat(int size)    {
        for(int i = 0; i<size; i++){
            for (int j= 0; j <size; j++){
                matrix[i][j] = null;
            }
        }
    }

    @Override
    public int numVertices() {
        return dicVertices.size();
    }

    @Override
    public LinkedList<String> vertices() {

        LinkedList<String> vertices = new LinkedList<>();
        for ( Header header: dicVertices.keys()
             ) {
            vertices.add(header.getLabel());
        }
        return vertices;
    }

    @Override
    public int numEdges() {
        return dicEdges.size();
    }

    @Override
    public LinkedList<String> edges() {
        LinkedList<String> edges = new LinkedList<>();
        for ( Header header: dicEdges.keys()
                ) {
            edges.add(header.getLabel());
        }
        return edges;
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
                if(matrix[i][j].equals(edge)){
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

    public int degree(Vertex v){
        int degree = 0;

        int row = v.getId();

        for(int i = firstIndexMatrix; i<= lastIndexMatrix; i++)
            if(!lstVtxDelete.contains(i) && (matrix[row][i]!= null))
                degree++;

        return degree;
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
    public int outDegree(Vertex vertex) {
        return 0;
    }

    @Override
    public int inDegree(Vertex vertex) {
        return 0;
    }

    @Override
    public Object outgoingEdges(Vertex vertex) {
        return null;
    }

    @Override
    public Object incomingEdges(Vertex vertex) {
        return null;
    }

    @Override
    public Vertex insertVertex(Object value) {
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

        Vertex vertex = new Vertex(id,label, value);
        Header header = new Header(label,id);

        dicVertices.insertItem(header,vertex);
        return vertex;
    }

    public Vertex insertVertex(Object value,String label)
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

        Vertex vertex = new Vertex(id,label, value);
        Header header = new Header(label,id);

        dicVertices.insertItem(header,vertex);
        return vertex;
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
        matrix[column][row] = edge.getLabel();

        return  edge;
    }

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
        matrix[column][row] = edge.getLabel();

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
                int pos = findEdgePosByLabel(edgeLabel);
                dicEdges.removeElement(new Header(edgeLabel,pos));

                matrix[row][i] = null;
                matrix[i][row] = null;
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

    public LinkedList<Vertex> adjacentVertices(Vertex v){
        Header headerToFind = new Header(v.getLabel(),v.getId());

        if(dicVertices.findElement(headerToFind) == null)
            return null;

        LinkedList<Vertex> lst = new LinkedList();
        int row = v.getId();

        for (int i = firstIndexMatrix; i <= lastIndexMatrix; i++) {
            if(!lstVtxDelete.contains(i) && matrix[row][i] != null){
                String label = findVertexLabelById(i);
                lst.add(dicVertices.findElement(new Header(label,i)));
            }
        }

        return lst;
    }

    public boolean areaAdjacent(Vertex vertex1, Vertex vertex2)
    {
        int row = vertex1.getId();
        int column = vertex2.getId();

        return matrix[row][column] != null;
    }


    public LinkedList<Edge> incidentEdges(Vertex v){
        LinkedList<Edge> lst = new LinkedList<>();

        int row = findVertexPosByLabel(v.getLabel());
        for(int i = firstIndexMatrix; i<= lastIndexMatrix; i++)
            if(!lstVtxDelete.contains(i)){
                if(matrix[row][i] != null) {
                    String label = matrix[row][i];
                    Header header = new Header(label,findVertexPosByLabel(label));
                    lst.add(dicEdges.findElement(header));
                }
            }

        return lst;
    }

    private int findVertexPosByLabel(String label)
    {
        for ( Header header: dicVertices.keys()) {
            if(header.getLabel().equals(label))
            {
                return header.getId();
            }
        }
        return -1;
    }

    private String findVertexLabelById(int id)
    {
        for ( Header header: dicVertices.keys()) {
            if(header.getId()==id)
            {
                return header.getLabel();
            }
        }
        return "";
    }

    private int findEdgePosByLabel(String label)
    {
        for (Header header: dicEdges.keys()) {
            if(header.getLabel().equals(label))
            {
                return header.getId();
            }
        }
        return -1;
    }

    private int generateVertexId(){
        if(lstVtxDelete.size() > 0){
            int id = lstVtxDelete.get(0);
            lstVtxDelete.remove(0);
            return id;
        }
        else
            return globalVertexID++;
    }

    private int findFirstRowColUtil(){
        int i = firstIndexMatrix +1;
        while(lstVtxDelete.contains(i) && (i<= lastIndexMatrix))
            i=i+1;
        if(!lstVtxDelete.contains(i))
            return i;

        return lastIndexMatrix;
    }

    private int findLastRowColUtil(){
        int i = lastIndexMatrix - 1;
        while(lstVtxDelete.contains(i) && (i>= firstIndexMatrix))
            i = i-1;

        if(!lstVtxDelete.contains(i))
            return i;

        return firstIndexMatrix;
    }

    private void resize(){
        int newSize = (int)(matrix[0].length * 1.5f);
        String newMat[][] = new String[newSize][newSize];

        for (int i = firstIndexMatrix; i <= lastIndexMatrix; i++) {
            for (int j = firstIndexMatrix; j <= lastIndexMatrix; j++) {
                newMat[i][j] = matrix[i][j];
            }
        }

        matrix = newMat;

    }

    /*
     *  Exemplo de uso:
     *  TGrafoNDMAd g = TGrafoNDMAd.carrega("nomeArqTGF.txt");
     * */
    public static GraphNDMat carrega(String nome_arq_TGF){
        GraphNDMat graph = new GraphNDMat();

        ArquivoTxt arq = ArquivoTxt.open(nome_arq_TGF, "rt");

        /* lendo os vertices */
        String row = arq.readline();
        while (!row.trim().equals("#")){
            String[] vector = row.split(" ", 2);
            if(vector.length>1) {
                Vertex vertex = graph.insertVertex(null, vector[1].trim());
            }

            row = arq.readline();
        }

        /* lendo as arestas */
        row = arq.readline();
        while (row!= null){
            String[] edges = row.split(" ", 3);

            int idVertex1 = Integer.parseInt(edges[0].trim()) - 1;
            int idVertex2 = Integer.parseInt(edges[1].trim()) - 1;

            String labelVertex1 = graph.findVertexLabelById(idVertex1);
            String labelVertex2 = graph.findVertexLabelById(idVertex2);

            Vertex vertex1 = graph.dicVertices.findElement(new Header(labelVertex1,idVertex1));
            Vertex vertex2 = graph.dicVertices.findElement(new Header(labelVertex2,idVertex2));

            String label="";

            if(edges.length == 3) {
                label = (edges[2].trim());
            }
            if(label.equals("")) {
                label = ("@#");
            }

            Edge edge = graph.insertEdge(vertex1,vertex2,null,label);

            row = arq.readline();
        }

        arq.close();

        return graph;

    }

    public String salva(String nome_arq_TGF){

        ArquivoTxt arq = ArquivoTxt.open(nome_arq_TGF, "wt");

        arq.write(this.toString());

        arq.close();

        return nome_arq_TGF;
    }

    public String toString(){

        MyHash<Integer,Integer> dicIDgrafoID_tgf = new MyHashListChain();
        /* Escrevendo os vertices */
        String strGrafo = "";
        int id = 1;

        String row="";

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
