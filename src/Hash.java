import java.math.BigInteger;

public class Hash {
    private static int[] arrayAscii =  new int[100];
    private static int[] arrayPolynomial =  new int[100];
    private static int[] arrayBernstein =  new int[100];
    private static int[] arrayModifiedBernstein =  new int[100];
    private static int[] arrayFNV =  new int[100];
    private static int[] arrayJSW =  new int[100];
    private static int[] arrayELF =  new int[100];


    public static void StartArrays(int n){

        int size = Prime.decideArraySize(n);
        arrayAscii =  new int[size];
        arrayPolynomial =  new int[size];
        arrayBernstein =  new int[size];
        arrayModifiedBernstein =  new int[size];
        arrayFNV =  new int[size];
        arrayJSW =  new int[size];
        arrayELF =  new int[size];
    }

    public static void ContColision(int sum, int[] array){
        int index = DefineIndex(sum,array);
        array[index] += 1;
    }

    private static int DefineIndex(int sum, int[] array)
    {
        return sum % array.length;
    }

    public static void ContColision(long sum, int[] array){
        int index = DefineIndex(sum,array);
        array[index] += 1;
    }

    private static int DefineIndex(long sum, int[] array)
    {
        return (int) sum % array.length;
    }

    public static void ContColision(BigInteger sum, int[] array){
        int index = DefineIndex(sum,array);
        array[index] += 1;
    }

    private static int DefineIndex(BigInteger sum, int[] array)
    {
        return sum.mod(TreatBigInteger.intToBigInteger(array.length)).intValue();
    }

    public static int Ascii(String word)
    {
        int sum = 0;
        for (int i=0; i< word.length();i++)
        {
            sum += word.charAt(i);
        }

        return sum;
    }

    public static int Polynomial(String word)
    {
        int sum = 0;
        int N = 42;
        for (int i=0; i< word.length();i++)
        {
            sum += word.charAt(i) * Math.pow(N,i);
        }

        return sum;
    }

    public static BigInteger Bernstein(String word)
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

    public static BigInteger ModifiedBernstein(String word)
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

    public static BigInteger FNV(String word)
    {
        BigInteger fnvSum = new BigInteger("2166136261");
        BigInteger fnvValue = new BigInteger("16777619");

        for (int i = 0; i < word.length(); i++)
        {
            fnvSum = (fnvSum.multiply(fnvValue)).or(TreatBigInteger.charToBigInteger(word.charAt(i)));
        }

        return fnvSum;
    }

    public static BigInteger JSW(String word)
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

    public static long ELF(String word)
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


    public static int[] getArrayAscii() {
        return arrayAscii;
    }

    public static int[] getArrayPolynomial() {
        return arrayPolynomial;
    }

    public static int[] getArrayBernstein() {
        return arrayBernstein;
    }

    public static int[] getArrayModifiedBernstein() {
        return arrayModifiedBernstein;
    }

    public static int[] getArrayFNV() {
        return arrayFNV;
    }

    public static int[] getArrayJSW() {
        return arrayJSW;
    }

    public static int[] getArrayELF() {
        return arrayELF;
    }

}
