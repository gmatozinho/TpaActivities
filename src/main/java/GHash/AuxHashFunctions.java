package GHash;

import NumLib.TreatBigInteger;

import java.math.BigInteger;

class AuxHashFunctions {
    static void ContColision(int sum, int[] array){
        int index = DefineIndex(sum,array);
        array[index] += 1;
    }

    private static int DefineIndex(int sum, int[] array)
    {
        return sum % array.length;
    }

    static int DefineIndex(int sum, int length)
    {
        return sum % length;
    }

    private static int DefineIndex(BigInteger sum, int[] array)
    {
        return sum.mod(TreatBigInteger.intToBigInteger(array.length)).intValue();
    }

    static int DefineIndex(long sum, int length)
    {
        return (int)sum % length;
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
