package TeacherAppBenchmark.HashLib.Functions;

import NumLib.ToNumber;

import java.math.BigInteger;

public class AuxHashFunctions {
    static void ContColision(int sum, int[] array){
        int index = DefineIndex(sum,array);
        array[index] += 1;
    }

    private static int DefineIndex(int sum, int[] array)
    {
        return sum % array.length;
    }

    public static int DefineIndex(int sum, int length)
    {
        return sum % length;
    }

    private static int DefineIndex(BigInteger sum, int[] array)
    {
        return sum.mod(ToNumber.intToBigInteger(array.length)).intValue();
    }

    public static int DefineIndex(long sum, int length)
    {
        return Math.abs((int)sum % length);
    }

    private static int DefineIndex(long sum, int[] array)
    {
        return (int) sum % array.length;
    }

    static void ContColision(long sum, int[] array)
    {
        int index = DefineIndex(sum,array);
        array[index] += 1;
    }

    static void ContColision(BigInteger sum, int[] array)
    {
        int index = DefineIndex(sum,array);
        array[index] += 1;
    }
}
