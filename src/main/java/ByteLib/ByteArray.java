package ByteLib;

import java.io.*;

public class ByteArray {
    public static byte[] toBytesStream(Object object) throws IOException {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            try (ObjectOutput output = new ObjectOutputStream(byteArrayOutputStream)) {
                output.writeObject(object);
            }

            return byteArrayOutputStream.toByteArray();
        }
    }
}
