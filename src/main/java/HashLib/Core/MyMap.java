package HashLib.Core;

import java.io.IOException;
import java.util.LinkedList;

interface MyMap <K,V>{
    V findElements(K key);
    boolean insertItem(K key, V value);
    V removeElement(K key);
    int size();
    boolean isEmpty();
    LinkedList<K> keys();
    LinkedList<V> values();
}