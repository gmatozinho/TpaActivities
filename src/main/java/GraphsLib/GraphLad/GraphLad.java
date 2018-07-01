package GraphsLib.GraphLad;

import GraphsLib.Edge;
import GraphsLib.Graph;
import GraphsLib.Vertex;
import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;

import java.util.LinkedList;

public class GraphLad extends Graph {
    protected int globalVertexID = 0;
    protected int globalEdgeID = 0;
    protected MyHash<String,VertexLad> vertices = new MyHashListChain<>();
    protected MyHash<String,EdgeLad> edges = new MyHashListChain<>();

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public LinkedList<Vertex> vertices() {
        return (LinkedList<Vertex>)(LinkedList<?>) vertices.values();
    }

    @Override
    public int numEdges() {
        return edges.size();
    }

    @Override
    public LinkedList<Edge> edges() {
        return (LinkedList<Edge>)(LinkedList<?>) edges.values();
    }

    @Override
    public Object getEdge(String vertex1, String vertex2) {
        VertexLad vertexObj1 = vertices.findElement(vertex1);
        VertexLad vertexObj2 = vertices.findElement(vertex2);

        for (EdgeLad edgeLad : vertexObj1.getEdges()) {
            if(edgeLad.isEndPoint(vertexObj2))
            {
                return edgeLad;
            }
        }

        return null;
    }

    @Override
    public String[] endVertices(String edge) {
        EdgeLad edgeLad = edges.findElement(edge);
        return new String[] { edgeLad.getOrigin().getLabel(),edgeLad.getDestination().getLabel()};
    }

    @Override
    public String opossite(String vertex, String edge) {
        VertexLad vertexLad = vertices.findElement(vertex);
        EdgeLad edgeLad = edges.findElement(edge);

        for (EdgeLad edgeObj : vertexLad.getEdges()) {
            if(edgeObj == edgeLad)
            {
                return edgeObj.myOpossite(vertexLad).getLabel();
            }
        }

        return null;
    }

    @Override
    public Vertex insertVertex(Object value) {
        Integer id = generateVertexId();
        String label = id.toString();

        VertexLad vertexLad = new VertexLad(id,label, value);

        vertices.insertItem(label,vertexLad);

        return vertexLad;
    }

    @Override
    public Vertex insertVertex(Object value, String label) {
        Integer id = generateVertexId();

        VertexLad vertexLad = new VertexLad(id,label, value);

        vertices.insertItem(label,vertexLad);

        return vertexLad;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, Object value) {
        VertexLad vertexLad1 = vertices.findElement(vertex1.getLabel());
        VertexLad vertexLad2 = vertices.findElement(vertex2.getLabel());

        Integer id = globalEdgeID++;
        String label = id.toString();

        EdgeLad edgeLad = new EdgeLad(id,label,value,vertexLad1,vertexLad2);
        vertexLad1.addEdgeIn(edgeLad);
        vertexLad2.addEdgeOut(edgeLad);

        return edgeLad;
    }

    @Override
    public Edge insertEdge(Vertex vertex1, Vertex vertex2, Object value, String label) {
        VertexLad vertexLad1 = vertices.findElement(vertex1.getLabel());
        VertexLad vertexLad2 = vertices.findElement(vertex2.getLabel());

        Integer id = globalEdgeID++;

        EdgeLad edgeLad = new EdgeLad(id,label,value,vertexLad1,vertexLad2);
        vertexLad1.addEdgeIn(edgeLad);
        vertexLad2.addEdgeOut(edgeLad);

        return edgeLad;
    }

    @Override
    public Object removeVertex(Vertex vertex) {
        return vertices.removeElement(vertex.getLabel());
    }

    @Override
    public Object removeEdge(Edge edge) {
        return edges.removeElement(edge.getLabel());
    }

    @Override
    public int degree(Vertex vertex)
    {
        return ((VertexLad)vertex).myDegree();
    }

    @Override
    public boolean areaAdjacent(Vertex vertex1, Vertex vertex2)
    {
        for (EdgeLad edge : ((VertexLad)vertex1).getEdges()) {
            if(edge.isEndPoint(((VertexLad)vertex2)))
            {
                return true;
            }
        }
        return false;
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

    protected int generateVertexId(){
        return globalVertexID++;
    }


}
