package MainTest;

import GraphsLib.Edge;
import GraphsLib.GraphMad.GraphMadDD;
import GraphsLib.Vertex;

public class GraphTest {
    public static void main(String[] args) {
        GraphMadDD graphMadND = GraphMadDD.load("database/"+"tgfexample.txt");
        Vertex v = new Vertex(0,"January",null);
        Edge e = new Edge(3,"Happy New Year!",null);
        System.out.println(graphMadND.opossite(v.getLabel(),e.getLabel()));
        System.out.println(graphMadND);
        //graphMadND.save("results/grafoResult.txt");
    }
}
