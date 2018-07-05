package MainTest;

import GraphsLib.Edge;
import GraphsLib.Vertex;
import TeacherAppBenchmark.graphs.GraphLad.TADGrafoLadjDD;
import TeacherAppBenchmark.graphs.GraphLad.TADGrafoLadjND;
import TeacherAppBenchmark.graphs.GraphMad.TADGrafoMadjDD;
import TeacherAppBenchmark.graphs.GraphMad.TADGrafoMadjND;

public class GraphTest {
    public static void main(String[] args) {
        TADGrafoMadjND graphMadND = TADGrafoMadjND.carrega("database/"+"tgfexample.txt");
        System.out.println(graphMadND);
        //graphMadND.save("results/grafoResult.txt");
    }
}
