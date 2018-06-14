package MainTest;

import GraphsLib.GraphND.GraphNDMat;

public class GraphTest {
    public static void main(String[] args) {
        GraphNDMat graphNDMat = GraphNDMat.carrega("database/"+"tgfexample.txt");
        System.out.println(graphNDMat);
        graphNDMat.salva("results/grafoResult.txt");
    }
}
