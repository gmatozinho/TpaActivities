package HashLib.Core;

class MyNode<K,V>{
    private long myHashCode;
    private K key;
    private V value;

    MyNode(long myHashCode,K key, V value) {
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

    long getMyHashCode() {
        return myHashCode;
    }

}
