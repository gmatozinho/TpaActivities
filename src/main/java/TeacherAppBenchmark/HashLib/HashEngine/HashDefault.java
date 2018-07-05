package TeacherAppBenchmark.HashLib.HashEngine;

import ByteLib.ByteArray;
import TeacherAppBenchmark.HashLib.Functions.HashFunctions;

import java.io.IOException;

public class HashDefault extends HashEngine {
    @Override
    public long generateHashCode(Object key) throws IOException {
        byte[] bytes = ByteArray.toBytesStream(key);
        return Math.abs(HashFunctions.Polynomial(bytes));
    }
}
