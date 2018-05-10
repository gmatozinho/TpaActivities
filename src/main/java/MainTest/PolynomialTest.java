package MainTest;

import Polynomial.Polynomial;

public class PolynomialTest {
    public static void main(String[] args) {

        Polynomial pol = new Polynomial("2x4-4x3+2x2+x+5");
        System.out.println(pol.getTermsList());

    }
}
