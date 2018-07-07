package MainTest;

import TeacherAppBenchmark.graphs.GraphMad.TADGrafoMadjND;

public class GraphTest {
    public static void main(String[] args) {
        TADGrafoMadjND graphMadND = TADGrafoMadjND.load("database/"+"tgfexample.txt");
        System.out.println(graphMadND);
        //graphMadND.save("results/grafoResult.txt");
    }
}
