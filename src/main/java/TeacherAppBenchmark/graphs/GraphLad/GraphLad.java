package TeacherAppBenchmark.graphs.GraphLad;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import TeacherAppBenchmark.graphs.Edge;
import TeacherAppBenchmark.graphs.Graph;
import TeacherAppBenchmark.graphs.Vertice;
import WorkFilesLib.ArquivoTxt;

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
            if(vertice.getLabel().equals(vertex))
            {
                return vertice;
            }
        }

        return null;
    }

    private EdgeLad findEdges(String edge)
    {
        for (EdgeLad edgeLad : edges.values()) {
            if(edgeLad.getLabel().equals(edge))
            {
                return edgeLad;
            }
        }

        return null;
    }


    @Override
    public String[] endVertices(String edge) {
        EdgeLad edgeLad = findEdges(edge);
        return new String[] { edgeLad.getOrigin().getLabel(),edgeLad.getDestination().getLabel()};
    }

    public LinkedList<Vertice> endVertices(Edge edge) {
        EdgeLad edgeLad = edges.findElement(edge.getId());
        LinkedList<Vertice> lst = new LinkedList<>();
        lst.add(edgeLad.getOrigin());
        lst.add(edgeLad.getDestination());


        return lst;
    }

    @Override
    public String opossite(String vertex, String edge) {
        VerticeLad vertexLad = findVertices(vertex);
        EdgeLad edgeLad = findEdges(edge);

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

        vertices.insertItem(id,vertexLad);

        return vertexLad;
    }

    @Override
    public Vertice insertVertex(Object value, String label) {
        Integer id = generateVertexId();

        VerticeLad vertexLad = new VerticeLad(id,label, value);

        vertices.insertItem(id,vertexLad);

        return vertexLad;
    }

    @Override
    public Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value) {
        VerticeLad vertexLad1 = vertices.findElement(vertice1.getId());
        VerticeLad vertexLad2 = vertices.findElement(vertice2.getId());

        Integer id = globalEdgeID++;
        String label = id.toString();

        EdgeLad edgeLad = new EdgeLad(id,label,value,vertexLad1,vertexLad2);
        vertexLad1.addEdgeIn(edgeLad);
        vertexLad2.addEdgeOut(edgeLad);

        edges.insertItem(id,edgeLad);

        return edgeLad;
    }

    @Override
    public Edge insertEdge(Vertice vertice1, Vertice vertice2, Object value, String label) {
        VerticeLad vertexLad1 = vertices.findElement(vertice1.getId());
        VerticeLad vertexLad2 = vertices.findElement(vertice2.getId());

        Integer id = globalEdgeID++;

        EdgeLad edgeLad = new EdgeLad(id,label,value,vertexLad1,vertexLad2);
        vertexLad1.addEdgeIn(edgeLad);
        vertexLad2.addEdgeOut(edgeLad);

        edges.insertItem(id,edgeLad);

        return edgeLad;
    }

    @Override
    public Object removeVertex(Vertice vertice) {

        VerticeLad verticeLad = ((VerticeLad)vertice);
        LinkedList<EdgeLad> edgesLad = verticeLad.getEdges();
        for (Edge edge: edgesLad) {
            Edge edgeFind = edges.findElement(edge.getId());
            if( edgeFind != null)
            {
                removeEdge(edge);
            }
        }

        return this.vertices.removeElement(vertice.getId());
    }

    @Override
    public Object removeEdge(Edge edge) {
        return edges.removeElement(edge.getId());
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

    protected static void loadCommon(GraphLad graph, ArquivoTxt arq){
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

            Vertice vertice1 = graph.vertices.findElement(idVertex1);
            Vertice vertice2 = graph.vertices.findElement(idVertex2);

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


}
