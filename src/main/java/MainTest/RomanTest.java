package MainTest;

import RomanAlgorismConverter.Roman;

import java.io.IOException;

public class RomanTest {
    public static void main(String[] args) throws IOException {
        Roman convert = new Roman();

        String str = convert.toRoman(10);
        String str2 = convert.toRoman(5);
        System.out.println(convert.subtract(str,str2));


    }
}
