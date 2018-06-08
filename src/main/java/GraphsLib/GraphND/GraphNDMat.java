package GraphsLib.GraphND;

import GraphsLib.Edge;
import GraphsLib.Vertex;
import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;

import java.util.LinkedList;

class Header {
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
}


public class GraphNDMat extends GraphND {

    private static final int TAM_DEFAULT = 64;

    // Dicionários com chave label e conteúdo objeto Vertex/Edge.
    private MyHash<Header,Vertex> dicVertices = new MyHashListChain<>();
    private MyHash<Header,Edge> dicEdges = new MyHashListChain<>();


    private int globalVertexID = 0;
    private int globalEdgeID = 0;
    private String matrix[][];
    private int lenght;

    private static final int defaultsize = 64;

    private int primIndexMatrix = -1;
    private int ultimIndexMatrix = -1;


    //Cria um grafo de tamanho Default com 64 nós.
    public GraphNDMat()
    {
        this.lenght = defaultsize;
        matrix = new String[defaultsize][defaultsize];

        startMat(defaultsize);
    }

    //Cria um grafo de tamanho Custom com N nós.
    public GraphNDMat(int lenght)
    {
        this.lenght = lenght;
        matrix = new String[lenght][lenght];

        startMat(lenght);
    }

    private void startMat(int size)
    {
        for(int i = 0; i<size; i++){
            for (int j= 0; j <size; j++){
                matrix[i][j] = null;
            }
        }
    }


    @Override
    public int numVertices() {
        return numVertices;
    }

    @Override
    public LinkedList vertices() {

        LinkedList<String> vertices = new LinkedList<>();
        for ( Header header: dicVertices.keys()
             ) {
            vertices.add(header.getLabel());
        }
        return vertices;
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    @Override
    public LinkedList edges() {
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

    private String findVertexPosById(int id)
    {
        for ( Header header: dicVertices.keys()) {
            if(header.getId()==id)
            {
                return header.getLabel();
            }
        }
        return "";
    }


    @Override
    public String[] endVertices(String edge) {
        int tam = numVertices();
        for (int i = 0; i< tam; i++){
            for(int j = 0; j < tam; j++){
                if(matrix[i][j].equals(edge)){
                    String labelVertex1 = findVertexPosById(i);
                    String labelVertex2;
                    if(!labelVertex1.equals(""))
                    {
                        labelVertex2 = findVertexPosById(j);
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
    public Vertex opossite(String vertex, String edge) {
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
        Integer id = globalVertexID++;
        String label = id.toString();

        Vertex vertex = new Vertex(id,label, value);

        Header header = new Header(label,id);

        dicVertices.insertItem(header,vertex);
        numVertices ++;
        return vertex;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, Object value) {
        Header header1 = new Header(vertex1.getLabel(),vertex1.getId());
        Header header2 = new Header(vertex2.getLabel(),vertex2.getId());

        if(dicVertices.findElements(header1) == null || dicVertices.findElements(header2) == null)
        {
            return null;
        }

        Integer id = globalEdgeID++;
        String label = id.toString();

        Edge edge = new Edge(id,label,value);

        int linha = vertex1.getId();
        int coluna = vertex2.getId();

        matrix[linha][coluna] = edge.getLabel();
        matrix[coluna][linha] = edge.getLabel();

        numEdges ++;
        return  edge;
    }

    @Override
    public Object removeVertex(Vertex vertex) {
        return null;
    }

    @Override
    public Object removeEdge(Edge edge) {
        return null;
    }
}
