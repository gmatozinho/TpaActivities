import java.math.BigInteger;

public class Hash {
    private static int[] arrayAscii =  new int[100];
    private static int[] arrayPolynomial =  new int[100];


    public static void StartArrays(int n){

        int size = Prime.decideArraySize(n);
        arrayAscii =  new int[size];
        arrayPolynomial =  new int[size];
    }

    public static void ContColision(int sum, int[] array){
        int index = DefineIndex(sum,array);
        array[index] += 1;
    }

    private static int DefineIndex(int sum, int[] array)
    {
        return sum % array.length;
    }

    public static int Ascii(String word)
    {
        int sum = 0;
        for (int i=0; i< word.length();i++)
        {
            sum += TreatWords.ConvertCharToAsciiValue(word.charAt(i));
        }

        return sum;
    }

    public static int Polynomial(String word)
    {
        int sum = 0;
        int N = 42;
        for (int i=0; i< word.length();i++)
        {
            sum += TreatWords.ConvertCharToAsciiValue(word.charAt(i)) * Math.pow(N,i);
        }

        return sum;
    }

    public static int Bernstein(String word)
    {
        int len = word.length();
        int h=0;
        int i;

        for (i = 0; i < len; i++)
        {
            h = 33 * h + word.charAt(i);
        }

        return h;
    }

    public static int ModifiedBernstein(String word)
    {
        int len = word.length();
        int h=0;
        int i;

        for (i = 0; i < len; i++)
        {
            h = 33 * h ^ word.charAt(i);
        }

        return h;
    }

    public static BigInteger FNV(String word)
    {
        BigInteger h = new BigInteger("2166136261");
        BigInteger w = new BigInteger("1677761");
        int i;

        for (i = 0; i < word.length(); i++)
        {
            h = new BigInteger(""+ h.multiply(w).compareTo(new BigInteger(""+word.charAt(i))));
        }

        return h;
    }

    public static int[] getArrayAscii() {
        return arrayAscii;
    }

    public static int[] getArrayPolynomial() {
        return arrayPolynomial;
    }

}
