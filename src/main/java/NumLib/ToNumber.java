package NumLib;

import java.math.BigInteger;

public class ToNumber {

    public static int ConvertCharToAsciiValue(char letter)
    {
        return (int) letter;
    }

    public static BigInteger intToBigInteger(int value)
    {
        return new BigInteger(""+value);
    }

    public static BigInteger charToBigInteger(char value)
    {
        return new BigInteger(""+(int)value);
    }



}
