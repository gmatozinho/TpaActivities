package HashLib.Functions;

import NumLib.Prime;
import NumLib.TreatBigInteger;

import java.math.BigInteger;

public class HashFunctions {
    private static int[] arrayAscii =  new int[100];
    private static int[] arrayPolynomial =  new int[100];
    private static int[] arrayBernstein =  new int[100];
    private static int[] arrayModifiedBernstein =  new int[100];
    private static int[] arrayFNV =  new int[100];
    private static int[] arrayJSW =  new int[100];
    private static int[] arrayELF =  new int[100];


    static void StartArrays(int n){

        int size = Prime.decideArraySize(n);
        arrayAscii =  new int[size];
        arrayPolynomial =  new int[size];
        arrayBernstein =  new int[size];
        arrayModifiedBernstein =  new int[size];
        arrayFNV =  new int[size];
        arrayJSW =  new int[size];
        arrayELF =  new int[size];
    }

    static int Ascii(String word)
    {
        int sum = 0;
        for (int i=0; i< word.length();i++)
        {
            sum += word.charAt(i);
        }

        return sum;
    }

    public static int Ascii(byte[] bytes)
    {
        int sum = 0;
        for (byte aByte : bytes) {
            sum += aByte;
        }

        return sum;
    }

    static int Polynomial(String word)
    {
        int sum = 0;
        int N = 33;
        for (int i=0; i< word.length();i++)
        {
            sum += word.charAt(i) * Math.pow(N,i);
        }

        return sum;
    }

    public static int Polynomial(byte[] bytes)
    {
        int sum = 0;
        int N = 42;
        for (int i=0; i< bytes.length;i++)
        {
            sum += bytes[i] * Math.pow(N,i);
        }

        return sum;
    }

    static BigInteger Bernstein(String word)
    {
        BigInteger sum = new BigInteger("0");
        for (int i = 0; i < word.length(); i++)
        {
            BigInteger temporary = sum.multiply(new BigInteger("33"));
            sum = temporary.add(TreatBigInteger.charToBigInteger(word.charAt(i)));
        }

        return sum;
    }

    public static BigInteger Bernstein(byte[] bytes)
    {

        BigInteger sum = new BigInteger("0");
        for (byte aByte : bytes) {
            BigInteger temporary = sum.multiply(new BigInteger("33"));
            sum = temporary.add(TreatBigInteger.intToBigInteger(aByte));
        }

        return sum;
    }

    static BigInteger ModifiedBernstein(String word)
    {
        BigInteger sum = new BigInteger("0");

        for (int i = 0; i < word.length(); i++)
        {
            BigInteger temporary = sum.multiply(new BigInteger("33"));
            sum = temporary.xor(TreatBigInteger.charToBigInteger(word.charAt(i)));
        }

        return sum;
    }

    public static BigInteger ModifiedBernstein(byte[] bytes)
    {

        BigInteger sum = new BigInteger("0");
        for (byte aByte : bytes) {
            BigInteger temporary = sum.multiply(new BigInteger("33"));
            sum = temporary.xor(TreatBigInteger.intToBigInteger(aByte));
        }

        return sum;
    }

    static BigInteger FNV(String word)
    {
        BigInteger sum = new BigInteger("2166136261");
        BigInteger fnvValue = new BigInteger("16777619");

        for (int i = 0; i < word.length(); i++)
        {
            sum = (sum.multiply(fnvValue)).or(TreatBigInteger.charToBigInteger(word.charAt(i)));
        }

        return sum;
    }

    public static BigInteger FNV(byte[] bytes)
    {


        BigInteger sum = new BigInteger("2166136261");
        BigInteger fnvValue = new BigInteger("16777619");
        for (byte aByte : bytes) {
            sum = (sum.multiply(fnvValue)).or(TreatBigInteger.intToBigInteger(aByte));
        }

        return sum;
    }


    static BigInteger JSW(String word)
    {
        BigInteger sum = new BigInteger("16777551");

        for (int i = 0; i < word.length(); i++)
        {
            sum = (sum.shiftLeft(1).or(sum.shiftRight(31))).or(TreatBigInteger.charToBigInteger(word.charAt(i)));
        }

        return sum;
    }

    public static BigInteger JSW(byte[] bytes)
    {
        BigInteger sum = new BigInteger("16777551");
        for (byte aByte : bytes) {
            sum = (sum.shiftLeft(1).or(sum.shiftRight(31))).or(TreatBigInteger.intToBigInteger(aByte));
        }

        return sum;
    }

    static long ELF(String word)
    {
        long sum = 0, var;

        for (int i = 0; i < word.length(); i++)
        {
            sum = (sum << 4) + word.charAt(i);
            var = sum & 0xf0000000L;

            if (var != 0)
            {
                sum ^= var >> 24;
            }

            sum &= ~var;
        }

        return sum;
    }

    public static long ELF(byte[] bytes)
    {
        long sum = 0, var;

        for (byte aByte : bytes) {
            sum = (sum << 4) + aByte;
            var = sum & 0xf0000000L;

            if (var != 0) {
                sum ^= var >> 24;
            }

            sum &= ~var;
        }

        return sum;
    }



    static int[] getArrayAscii() {
        return arrayAscii;
    }

    static int[] getArrayPolynomial() {
        return arrayPolynomial;
    }

    static int[] getArrayBernstein() {
        return arrayBernstein;
    }

    static int[] getArrayModifiedBernstein() {
        return arrayModifiedBernstein;
    }

    static int[] getArrayFNV() {
        return arrayFNV;
    }

    static int[] getArrayJSW() {
        return arrayJSW;
    }

    static int[] getArrayELF() {
        return arrayELF;
    }

}
