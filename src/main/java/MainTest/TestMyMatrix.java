package MainTest;

import MatrixLib.MySparseMatrix;

import java.io.IOException;

public class TestMyMatrix {
    public static void main(String[] args) throws IOException {

        MySparseMatrix matrix = MySparseMatrix.load("matrix.csv");
        MySparseMatrix matrix1 = MySparseMatrix.load("matrix1.csv");

        System.out.println(matrix.values());
        System.out.println(matrix1.values());

        MySparseMatrix result = matrix.multiplyByValue(3);

        System.out.println(result.values());

    }
}
