public class Hash {
    private static int[] arrayAscii =  new int[100];
    private static int[] arrayPolynomial =  new int[100];

    public static void ContColision(int sum, int[] array){
        int index = DefineIndex(sum,array);
        array[index] += 1;
    }

    private static int DefineIndex(int sum, int[] array)
    {
        return sum % array.length;
    }

    public static int HashAscii(String word)
    {
        int sum = 0;
        for (int i=0; i< word.length();i++)
        {
            sum += TreatWords.ConvertCharToAsciiValue(word.charAt(i));
        }

        return sum;
    }

    public static int HashPolynomial(String word)
    {
        int sum = 0;
        int N = 42;
        for (int i=0; i< word.length();i++)
        {
            sum += TreatWords.ConvertCharToAsciiValue(word.charAt(i)) * Math.pow(N,i);
        }

        return sum;
    }

    public static int[] getArrayAscii() {
        return arrayAscii;
    }

    public static int[] getArrayPolynomial() {
        return arrayPolynomial;
    }

}
