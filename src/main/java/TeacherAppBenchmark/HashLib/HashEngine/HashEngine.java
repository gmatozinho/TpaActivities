package TeacherAppBenchmark.HashLib.HashEngine;

import java.io.IOException;

public abstract class HashEngine {
    public abstract long generateHashCode(Object key) throws IOException;
}
