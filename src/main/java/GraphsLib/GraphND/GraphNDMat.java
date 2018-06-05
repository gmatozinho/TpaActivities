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

    @Override
    public int numVertices() {
        return numVertices;
    }

    @Override
    public LinkedList vertices() {
        return dicVertices.keys();
    }

    @Override
    public int numEdges() {
        return numEdges;
    }

    @Override
    public LinkedList edges() {
        return dicVertices.keys();
    }

    @Override
    public Edge getEdge(Vertex vertex1, Vertex vertex2) {
        return null;
    }

    @Override
    public Vertex[] endVertices(Edge edge) {
        return new Vertex[0];
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
