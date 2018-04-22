package MainTest;

import MatrixLib.MyMatrix;

import java.io.IOException;

public class TestMyMatrix {
    public static void main(String[] args) throws IOException {

        MyMatrix matrix = MyMatrix.load("matrix.csv");
        MyMatrix matrix1 = MyMatrix.load("matrix1.csv");

        System.out.println(matrix.values());
        System.out.println(matrix1.values());

        MyMatrix result = matrix.multiplyByValue(3);

        System.out.println(result.values());

    }
}
