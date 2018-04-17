package GHash;

import ByteLib.ByteArray;

import java.io.IOException;

public class HashDefault extends HashEngine {
    @Override
    public int generateHashCode(Object key) throws IOException {
        byte[] bytes = ByteArray.toBytesStream(key);
        return HashFunctions.Polynomial(bytes);
    }
}
