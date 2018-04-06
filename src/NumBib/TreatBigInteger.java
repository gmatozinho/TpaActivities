package NumBib;

import java.math.BigInteger;

public class TreatBigInteger {

    public static BigInteger intToBigInteger(int value)
    {
        return new BigInteger(""+value);
    }

    public static BigInteger charToBigInteger(char value)
    {
        return new BigInteger(""+(int)value);
    }

}
