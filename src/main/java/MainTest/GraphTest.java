package MainTest;

import GraphsLib.Edge;
import GraphsLib.Vertex;
import TeacherAppBenchmark.graphs.GraphLad.TADGrafoLadjND;

public class GraphTest {
    public static void main(String[] args) {
        TADGrafoLadjND graphMadND = TADGrafoLadjND.carrega("database/"+"tgfexample.txt");
        Vertex v = new Vertex(0,"January",null);
        Edge e = new Edge(3,"Happy New Year!",null);
        System.out.println(graphMadND.opossite(v.getLabel(),e.getLabel()));
        System.out.println(graphMadND);
        //graphMadND.save("results/grafoResult.txt");
    }
}
