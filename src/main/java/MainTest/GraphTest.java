package MainTest;

import GraphsLib.GraphMad.GraphMadND;

public class GraphTest {
    public static void main(String[] args) {
        GraphMadND graphMadND = GraphMadND.carrega("database/"+"tgfexample.txt");
        System.out.println(graphMadND);
        //graphMadND.salva("results/grafoResult.txt");
    }
}
