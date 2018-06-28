package GraphsLib.GraphLad;

import GraphsLib.Edge;

public class EdgeLad extends Edge {

    public EdgeLad(int id, String label, Object value) {
        super(id, label, value);
    }

    private VertexLad in;
    private VertexLad out;

    boolean isEndPoint(VertexLad vertex)
    {
        return in.equals(vertex) || out.equals(vertex);
    }

    public VertexLad getIn() {
        return in;
    }

    public void setIn(VertexLad in) {
        this.in = in;
    }

    public VertexLad getOut() {
        return out;
    }

    public void setOut(VertexLad out) {
        this.out = out;
    }

    public VertexLad myOpossite(VertexLad vertex)
    {
        if(vertex.equals(in))
        {
            return out;
        }else if(vertex.equals(out)){
            return in;
        }

        return null;
    }


}
