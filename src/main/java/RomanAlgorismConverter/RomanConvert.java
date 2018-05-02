package RomanAlgorismConverter;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;

import java.io.IOException;

public class RomanConvert {

    private String errorMsg = "Could not convert this value";
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

    private int toInt(String number) throws IOException {
        if (number.isEmpty()) return 0;
        if (number.startsWith("M")) return romanToDecimal.findElements("M") + toInt(number.substring(1, number.length()));
        if (number.startsWith("CM")) return romanToDecimal.findElements("CM") + toInt(number.substring(2, number.length()));
        if (number.startsWith("D")) return romanToDecimal.findElements("D") + toInt(number.substring(1, number.length()));
        if (number.startsWith("CD")) return romanToDecimal.findElements("CD") + toInt(number.substring(2, number.length()));
        if (number.startsWith("C")) return romanToDecimal.findElements("C") + toInt(number.substring(1, number.length()));
        if (number.startsWith("XC")) return romanToDecimal.findElements("XC") + toInt(number.substring(2, number.length()));
        if (number.startsWith("L")) return romanToDecimal.findElements("L") + toInt(number.substring(1, number.length()));
        if (number.startsWith("XL")) return romanToDecimal.findElements("XL") + toInt(number.substring(2, number.length()));
        if (number.startsWith("X")) return romanToDecimal.findElements("X") + toInt(number.substring(1, number.length()));
        if (number.startsWith("IX")) return romanToDecimal.findElements("IX") + toInt(number.substring(2, number.length()));
        if (number.startsWith("V")) return romanToDecimal.findElements("V") + toInt(number.substring(1, number.length()));
        if (number.startsWith("IV")) return romanToDecimal.findElements("IV") + toInt(number.substring(2, number.length()));
        if (number.startsWith("I")) return romanToDecimal.findElements("I") + toInt(number.substring(1, number.length()));

        return 0;
    }

    public String toRoman(int number) throws IOException {

        if(number < 4000) {
            if(number == 0) return "";
            if(number >= 1000) return decimalToRoman.findElements(1000) + toRoman(number - 1000);
            if(number >= 900) return decimalToRoman.findElements(900) + toRoman(number - 900);
            if(number >= 500) return decimalToRoman.findElements(500) + toRoman(number - 500);
            if(number >= 400) return decimalToRoman.findElements(400) + toRoman(number - 400);
            if(number >= 100) return decimalToRoman.findElements(100) + toRoman(number - 100);
            if(number >= 90) return decimalToRoman.findElements(90) + toRoman(number - 90);
            if(number >= 50) return decimalToRoman.findElements(50) + toRoman(number - 50);
            if(number >= 40) return decimalToRoman.findElements(40) + toRoman(number - 40);
            if(number >= 10) return decimalToRoman.findElements(10) + toRoman(number - 10);
            if(number >= 9) return decimalToRoman.findElements(9) + toRoman(number - 9);
            if(number >= 5) return decimalToRoman.findElements(5) + toRoman(number - 5);
            if(number >= 4) return decimalToRoman.findElements(4) + toRoman(number - 4);
            if(number >= 1) return decimalToRoman.findElements(1) + toRoman(number - 1);
        }

        return errorMsg;

    }

    public String subtract(String a, String b) throws IOException {
        int intA = toInt(a);
        int intB = toInt(b);

        int result =  intA - intB;
        if(result> 0 && result < 4000 && (!a.equals(errorMsg) && !b.equals(errorMsg))) return toRoman(result);
        else return "Undefined";

    }

    public String add(String a, String b) throws IOException {
        int intA = toInt(a);
        int intB = toInt(b);

        int result =  intA + intB;
        if(result> 0 && result < 4000 && (!a.equals(errorMsg) && !b.equals(errorMsg))) return toRoman(result);
        else return "Undefined";


    }


}
