package HashLib.CalcHashEngine;

import ByteLib.ByteArray;
import HashLib.Functions.HashFunctions;

import java.io.IOException;

public class HashDefault extends HashEngine {
    @Override
    public long generateHashCode(Object key) throws IOException {
        byte[] bytes = ByteArray.toBytesStream(key);
        return HashFunctions.Polynomial(bytes);
    }
}
