package TeacherAppBenchmark.graphs.GraphLad;

import TeacherAppBenchmark.graphs.Edge;

public class EdgeLad extends Edge {

    public EdgeLad(int id, String label, Object value, VerticeLad origin, VerticeLad destination) {
        super(id, label, value);
        this.destination = destination;
        this.origin = origin;
    }

    private VerticeLad destination;
    private VerticeLad origin;

    public boolean isEndPoint(VerticeLad vertex)
    {
        return destination.equals(vertex) || origin.equals(vertex);
    }

    public VerticeLad getDestination() {
        return destination;
    }

    public void setDestination(VerticeLad destination) {
        this.destination = destination;
    }

    public VerticeLad getOrigin() {
        return origin;
    }

    public void setOrigin(VerticeLad origin) {
        this.origin = origin;
    }

    public VerticeLad myOpossite(VerticeLad vertex)
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
