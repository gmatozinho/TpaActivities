package TeacherAppBenchmark.HashLib.Core;

class Item<K,V>{
    private long myHashCode;
    private K key;
    private V value;

    Item(long myHashCode, K key, V value) {
        this.key = key;
        this.value = value;
        this.myHashCode = myHashCode;
    }

    Item(K key, V value) {
        this.key = key;
        this.value = value;
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
