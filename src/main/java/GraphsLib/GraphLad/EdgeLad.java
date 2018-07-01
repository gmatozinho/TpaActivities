package GraphsLib.GraphLad;

import GraphsLib.Edge;

public class EdgeLad extends Edge {

    public EdgeLad(int id, String label, Object value,VertexLad origin,VertexLad destination) {
        super(id, label, value);
        this.destination = destination;
        this.origin = origin;
    }

    private VertexLad destination;
    private VertexLad origin;

    public boolean isEndPoint(VertexLad vertex)
    {
        return destination.equals(vertex) || origin.equals(vertex);
    }

    public VertexLad getDestination() {
        return destination;
    }

    public void setDestination(VertexLad destination) {
        this.destination = destination;
    }

    public VertexLad getOrigin() {
        return origin;
    }

    public void setOrigin(VertexLad origin) {
        this.origin = origin;
    }

    public VertexLad myOpossite(VertexLad vertex)
    {
        if(vertex.equals(destination))
        {
            return origin;
        }else if(vertex.equals(origin)){
            return destination;
        }

        return null;
    }


}
