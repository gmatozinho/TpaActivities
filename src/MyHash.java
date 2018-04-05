import java.util.LinkedList;
class MyNode{
    Object key;
    Object object;

    public MyNode(Object key, Object object) {
        this.key = key;
        this.object = object;
    }

    public Object getKey() {
        return key;
    }

    public Object getObject() {
        return object;
    }
}


public class MyHash implements MyMap {
    int lenght;
    LinkedList<MyNode>[] hashVector;


    @Override
    public Object findElements(Object key) {
        return null;
    }

    @Override
    public boolean insertItem(Object key, Object object) {
        return false;
    }

    @Override
    public Object removeElement(Object key) {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public LinkedList<Object> keys() {
        return null;
    }

    @Override
    public LinkedList<Object> values() {
        return null;
    }
}
