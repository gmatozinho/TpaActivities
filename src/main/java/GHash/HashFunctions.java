package GHash;

import NumBib.Prime;
import NumBib.TreatBigInteger;

import java.math.BigInteger;

class HashFunctions {
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

    static int Polynomial(String word)
    {
        int sum = 0;
        int N = 42;
        for (int i=0; i< word.length();i++)
        {
            sum += word.charAt(i) * Math.pow(N,i);
        }

        return sum;
    }

    static int Polynomial(byte[] bytes)
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
        BigInteger bigIntegerSum = new BigInteger("0");
        for (int i = 0; i < word.length(); i++)
        {
            BigInteger temporary = bigIntegerSum.multiply(new BigInteger("33"));
            bigIntegerSum = temporary.add(TreatBigInteger.charToBigInteger(word.charAt(i)));
        }

        return bigIntegerSum;

        /*@ Sem Big Integer
                long sum = 0;

                for (int i = 0; i < word.length(); i++)
                {
                    sum = 33 * sum + word.charAt(i);
                }

        return sum;*/
    }

    static BigInteger ModifiedBernstein(String word)
    {
        BigInteger bigIntegerSum = new BigInteger("0");

        for (int i = 0; i < word.length(); i++)
        {
            BigInteger temporary = bigIntegerSum.multiply(new BigInteger("33"));
            bigIntegerSum = temporary.xor(TreatBigInteger.charToBigInteger(word.charAt(i)));
        }

        return bigIntegerSum;

        /* @ Sem Big Integer
                int sum=0;

                for (int i = 0; i < word.length(); i++)
                {
                    sum = 33 * sum ^ word.charAt(i);
                }

                return sum;*/
    }

    static BigInteger FNV(String word)
    {
        BigInteger fnvSum = new BigInteger("2166136261");
        BigInteger fnvValue = new BigInteger("16777619");

        for (int i = 0; i < word.length(); i++)
        {
            fnvSum = (fnvSum.multiply(fnvValue)).or(TreatBigInteger.charToBigInteger(word.charAt(i)));
        }

        return fnvSum;
    }

    static BigInteger JSW(String word)
    {
        BigInteger sum = new BigInteger("16777551");

        for (int i = 0; i < word.length(); i++)
        {
            sum = (sum.shiftLeft(1).or(sum.shiftRight(31))).or(TreatBigInteger.charToBigInteger(word.charAt(i)));
        }

        return sum;

        /* @Sem Big Integer
        long sum = 16777551;

        for (int i = 0; i < word.length(); i++)
        {
            sum = (sum << 1 | sum >> 31) ^ word.charAt(i);
        }

        return sum;*/
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
