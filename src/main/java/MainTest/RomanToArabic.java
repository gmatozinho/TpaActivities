package MainTest;

import RomanAlgorismConverter.RomanConvert;

import java.io.IOException;

public class RomanToArabic {
    public static void main(String[] args) throws IOException {
        RomanConvert convert = new RomanConvert();

        System.out.println(convert.toRomano(3999));
        System.out.println(convert.toInt(convert.toRomano(3999)));

    }
}
