package GraphsLib.GraphLad;

import GraphsLib.Edge;
import GraphsLib.GraphNonDirectional;
import GraphsLib.Vertex;
import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;

import java.util.LinkedList;

public class GraphLadND extends GraphLad implements GraphNonDirectional {
    private MyHash<String,VertexLad> vertices = new MyHashListChain<>();
    private MyHash<String,EdgeLad> edges = new MyHashListChain<>();

    @Override
    public int numVertices() {
        return vertices.size();
    }

    //Tem q retornar os vertex
    public LinkedList<Vertex> vertices() {
        return (LinkedList<Vertex>)(LinkedList<?>) vertices.values();
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    //Tem  retornar o edges
    public LinkedList<Edge> edges() {
        return (LinkedList<Edge>)(LinkedList<?>) edges.values();
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
    public Vertex insertVertex(Object value) {
        return null;
    }

    @Override
    public Vertex insertVertex(Object value, String label) {
        return null;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, Object value) {
        return null;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, Object value, String label) {
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

    @Override
    public boolean areaAdjacent(Vertex vertex1, Vertex vertex2) {
        return false;
    }

    @Override
    public int degree(Vertex vertex) {
        return 0;
    }

    @Override
    public LinkedList<Edge> incidentEdges(Vertex vertex) {
        return null;
    }

    @Override
    public LinkedList<Vertex> adjacentVertices(Vertex vertex) {
        return null;
    }
}
