package GHash;

import java.io.IOException;

public abstract class HashEngine {
    public abstract int generateHashCode(Object key) throws IOException;
}
