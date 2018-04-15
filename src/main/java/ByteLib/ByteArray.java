package ByteLib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

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
