package GraphsLib.GraphLad;

import GraphsLib.Vertex;
import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;

import java.util.LinkedList;

public class VertexLad extends Vertex {

    public VertexLad(int id, String label, Object value) {
        super(id, label, value);
    }

    private MyHash<String,EdgeLad> in = new MyHashListChain<>();
    private MyHash<String,EdgeLad> out = new MyHashListChain<>();

    public int myInDegree()
    {
        return in.size();
    }

    public int myOutDegree()
    {
        return  out.size();
    }

    int myDegree()
    {
        return myInDegree() + myOutDegree();
    }

    public EdgeLad addEdgeIn(EdgeLad edge)
    {
        in.insertItem(edge.getLabel(),edge);
        return edge;
    }

    public EdgeLad addEdgeOut(EdgeLad edge)
    {
        out.insertItem(edge.getLabel(),edge);
        return edge;
    }

    public boolean isEndPoint(EdgeLad edge)
    {
        return (in.findElement(edge.getLabel()) != null || out.findElement(edge.getLabel()) != null);
    }

    public boolean isOrigin(EdgeLad edge)
    {
        return out.findElement(edge.getLabel()) != null;
    }

    public boolean isDestiny(EdgeLad edge)
    {
        return in.findElement(edge.getLabel()) != null;
    }

    public LinkedList<EdgeLad> getEdgesIn(){
        return in.values();
    }

    public LinkedList<EdgeLad> getEdgesOut(){
        return out.values();
    }

    LinkedList<EdgeLad> getEdges(){

        LinkedList<EdgeLad> edges = getEdgesIn();
        edges.addAll(getEdgesOut());

        return edges;
    }



}
