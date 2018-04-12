package GHash;

import java.io.IOException;
import java.util.LinkedList;

interface MyMap {
    Object findElements(Object key) throws IOException;
    boolean insertItem(Object key, Object object);
    Object removeElement(Object key);
    int size();
    boolean isEmpty();
    LinkedList<Object> keys();
    LinkedList<Object> values();

}