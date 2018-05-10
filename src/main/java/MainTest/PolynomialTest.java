package MainTest;

import Polynomial.Polynomial;

public class PolynomialTest {
    public static void main(String[] args) {

        Polynomial pol = new Polynomial("2x4-4x3+2x2+x+5");
        Polynomial pol2 = new Polynomial("-5x2+x");
        Polynomial result = pol.sum(pol2);
        System.out.println(result.toString());

    }
}
