package HashLib.CalcHashEngine;

import java.io.IOException;

public abstract class HashEngine {
    public abstract long generateHashCode(Object key) throws IOException;
}
