package RomanAlgorismConverter;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;

import java.io.IOException;

public class RomanConvert {

    //TODO Polir melhor as classes
    private MyHash<Integer,String> decimalToRoman = new MyHashListChain<>();

    private MyHash<String,Integer> romanToDecimal = new MyHashListChain<>();

    private String[] romanos = new String[]{"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    private int arabicos[] = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

    public RomanConvert() throws IOException {
        for (int i = 0; i < romanos.length ; i++) {
            decimalToRoman.insertItem(arabicos[i],romanos[i]);
            romanToDecimal.insertItem(romanos[i],arabicos[i]);
        }
    }

    public int toInt(String number) {
        if (number.isEmpty()) return 0;
        if (number.startsWith("M")) return 1000 + toInt(number.substring(1, number.length()));
        if (number.startsWith("CM")) return 900 + toInt(number.substring(2, number.length()));
        if (number.startsWith("D")) return 500 + toInt(number.substring(1, number.length()));
        if (number.startsWith("CD")) return 400 + toInt(number.substring(2, number.length()));
        if (number.startsWith("C")) return 100 + toInt(number.substring(1, number.length()));
        if (number.startsWith("XC")) return 90 + toInt(number.substring(2, number.length()));
        if (number.startsWith("L")) return 50 + toInt(number.substring(1, number.length()));
        if (number.startsWith("XL")) return 40 + toInt(number.substring(2, number.length()));
        if (number.startsWith("X")) return 10 + toInt(number.substring(1, number.length()));
        if (number.startsWith("IX")) return 9 + toInt(number.substring(2, number.length()));
        if (number.startsWith("V")) return 5 + toInt(number.substring(1, number.length()));
        if (number.startsWith("IV")) return 4 + toInt(number.substring(2, number.length()));
        if (number.startsWith("I")) return 1 + toInt(number.substring(1, number.length()));
        else{
            return -1;
        }
    }


    public String toRomano(int number) throws IOException {
        StringBuilder m_number= new StringBuilder();

        if(number < 4000) {
            while (number >= 1000) {
                m_number.append(decimalToRoman.findElements(1000));
                number -= 1000;
            }
            while (number >= 900) {
                m_number.append(decimalToRoman.findElements(900));
                number -= 900;
            }
            while (number >= 500) {
                m_number.append(decimalToRoman.findElements(500));
                number -= 500;
            }
            while (number >= 400) {
                m_number.append(decimalToRoman.findElements(400));
                number -= 400;
            }
            while (number >= 100) {
                m_number.append(decimalToRoman.findElements(100));
                number -= 100;
            }
            while (number >= 90) {
                m_number.append(decimalToRoman.findElements(90));
                number -= 90;
            }
            while (number >= 50) {
                m_number.append(decimalToRoman.findElements(50));
                number -= 50;
            }
            while (number >= 40) {
                m_number.append(decimalToRoman.findElements(40));
                number -= 40;
            }
            while (number >= 10) {
                m_number.append(decimalToRoman.findElements(10));
                number -= 10;
            }
            while (number >= 9) {
                m_number.append(decimalToRoman.findElements(9));
                number -= 9;
            }
            while (number >= 5) {
                m_number.append(decimalToRoman.findElements(5));
                number -= 5;
            }
            while (number >= 4) {
                m_number.append(decimalToRoman.findElements(4));
                number -= 4;
            }
            while (number >= 1) {
                m_number.append(decimalToRoman.findElements(1));
                number -= 1;
            }

            return m_number.toString();
        }

        return "Nao foi possivel conveter esse valor";

    }

    public String substrai(String a,String b)
    {

        return "";

    }

    public String soma(String a,String b)
    {

        return "";

    }


}
