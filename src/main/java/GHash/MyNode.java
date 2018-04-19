package GHash;

public class MyNode<K,V>{
    private int myHashCode;
    private K key;
    private V value;

    MyNode(int myHashCode,K key, V value) {
        this.key = key;
        this.value = value;
        this.myHashCode = myHashCode;
    }

    void setValue(V value) {

        this.value = value;
    }

    K getKey() {
        return key;
    }

    V getValue() {
        return value;
    }

    int getMyHashCode() {
        return myHashCode;
    }

}
