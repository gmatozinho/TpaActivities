package TeacherAppBenchmark.graphs.GraphLad;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import TeacherAppBenchmark.graphs.Edge;
import TeacherAppBenchmark.graphs.Graph;
import TeacherAppBenchmark.graphs.Vertice;

import java.util.LinkedList;

public class GraphLad extends Graph {
    protected int globalVertexID = 0;
    protected int globalEdgeID = 0;
    protected MyHash<Integer,VerticeLad> vertices = new MyHashListChain<>();
    protected MyHash<Integer,EdgeLad> edges = new MyHashListChain<>();

    @Override
    public int numVertices() {
        return vertices.size();
    }

    @Override
    public LinkedList<Vertice> vertices() {
        return (LinkedList<Vertice>)(LinkedList<?>) vertices.values();
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

//        VerticeLad vertexObj1 = vertices.findElement(vertex1);
////        VerticeLad vertexObj2 = vertices.findElement(vertex2);

        VerticeLad vertexObj1 = findVertices(vertex1);
        VerticeLad vertexObj2 = findVertices(vertex2);

        if(vertexObj1 != null && vertexObj2 != null)  {

            for (EdgeLad edgeLad : vertexObj1.getEdges()) {
                if (edgeLad.isEndPoint(vertexObj2)) {
                    return edgeLad;
                }
            }
        }

        return null;
    }

    private VerticeLad findVertices(String vertex)
    {
        for (VerticeLad vertice : vertices.values()) {
            if(vertice.getLabel()== vertex)
            {
                return vertice;
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
        VerticeLad vertexLad = vertices.findElement(vertex);
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
    public Vertice insertVertex(Object value) {
        Integer id = generateVertexId();
        String label = id.toString();

        VerticeLad vertexLad = new VerticeLad(id,label, value);

        vertices.insertItem(label,vertexLad);

        return vertexLad;
    }

    @Override
    public Vertice insertVertex(Object value, String label) {
        Integer id = generateVertexId();

        VerticeLad vertexLad = new VerticeLad(id,label, value);

        vertices.insertItem(label,vertexLad);

        return vertexLad;
    }

    @Override
    public Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value) {
        VerticeLad vertexLad1 = vertices.findElement(vertice1.getLabel());
        VerticeLad vertexLad2 = vertices.findElement(vertice2.getLabel());

        Integer id = globalEdgeID++;
        String label = id.toString();

        EdgeLad edgeLad = new EdgeLad(id,label,value,vertexLad1,vertexLad2);
        vertexLad1.addEdgeIn(edgeLad);
        vertexLad2.addEdgeOut(edgeLad);

        return edgeLad;
    }

    @Override
    public Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value, String label) {
        VerticeLad vertexLad1 = vertices.findElement(vertice1.getLabel());
        VerticeLad vertexLad2 = vertices.findElement(vertice2.getLabel());

        Integer id = globalEdgeID++;

        EdgeLad edgeLad = new EdgeLad(id,label,value,vertexLad1,vertexLad2);
        vertexLad1.addEdgeIn(edgeLad);
        vertexLad2.addEdgeOut(edgeLad);

        return edgeLad;
    }

    @Override
    public Object removeVertex(Vertice vertice) {
        return this.vertices.removeElement(vertice.getLabel());
    }

    @Override
    public Object removeEdge(Edge edge) {
        return edges.removeElement(edge.getLabel());
    }

    @Override
    public int degree(Vertice vertice)
    {
        return ((VerticeLad) vertice).myDegree();
    }

    @Override
    public boolean areAdjacent(Vertice vertice1, Vertice vertice2)
    {
        for (EdgeLad edge : ((VerticeLad) vertice1).getEdges()) {
            if(edge.isEndPoint(((VerticeLad) vertice2)))
            {
                return true;
            }
        }
        return false;
    }

    public int degree(VerticeLad vertex)
    {
        return vertex.myDegree();
    }

    public boolean areaAdjacent(VerticeLad vertex1, VerticeLad vertex2)
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
