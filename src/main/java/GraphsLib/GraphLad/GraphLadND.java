package GraphsLib.GraphLad;

import GraphsLib.Edge;
import GraphsLib.Vertex;
import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;

import java.util.LinkedList;

public class GraphLadND extends GraphLad {
    private MyHash<String,VertexLad> vertices = new MyHashListChain<>();
    private MyHash<String,EdgeLad> edges = new MyHashListChain<>();

    @Override
    public int numVertices() {
        return vertices.size();
    }

    //Tem q retornar os vertex
    @Override
    public LinkedList<String> vertices() {
        return vertices.keys();
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    //Tem  retornar o edges
    @Override
    public LinkedList<String> edges() {
        return edges.keys();
    }


    public int degree(VertexLad vertex)
    {
        return vertex.myDegree();
    }

    public boolean areaAdjacent(VertexLad vertex1, VertexLad vertex2)
    {
        for (EdgeLad edge : vertex1.getEdges()) {
            if(edge.isEndPoint(vertex2))
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object getEdge(String vertex1, String vertex2) {
        return null;
    }

    @Override
    public String[] endVertices(String edge) {
        return new String[0];
    }

    @Override
    public String opossite(String vertex, String edge) {
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
        return null;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, Object value) {
        return null;
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
