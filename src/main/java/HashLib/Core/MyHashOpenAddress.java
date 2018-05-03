package HashLib.Core;

import HashLib.HashEngine.HashDefault;
import HashLib.HashEngine.HashEngine;
import NumLib.Prime;

import java.util.LinkedList;

import static HashLib.Functions.AuxHashFunctions.DefineIndex;

@SuppressWarnings("ALL")
public class MyHashOpenAddress<K,V> extends MyHash<K,V>{

    private MyNode<K,V>[] hashVector;
    private int length;
    private int maxSize;
    private int actualSize;
    private float threshold = 0.80f;
    private HashEngine hashEngine;

    public MyHashOpenAddress() {
        this.hashEngine = new HashDefault();
        this.length = 1<<4;
        actualSize = 0;
        calcMaxSize();
        hashVector = new MyNode[this.length];
    }

    public MyHashOpenAddress(int length) {
        this.hashEngine = new HashDefault();
        this.length = Prime.calcArraySize(length);
        actualSize = 0;
        calcMaxSize();
        hashVector = new MyNode[this.length];
    }

    public MyHashOpenAddress(HashEngine hashEngine) {
        this.hashEngine = hashEngine;
        this.length = Prime.calcArraySize(length);
        actualSize = 0;
        calcMaxSize();
        hashVector = new MyNode[this.length];
    }

    public MyHashOpenAddress(int length, HashEngine hashEngine) {
        this.hashEngine = hashEngine;
        this.length = Prime.calcArraySize(length);
        actualSize = 0;
        calcMaxSize();
        hashVector = new MyNode[this.length];
    }

    private void calcMaxSize()
    {
        this.maxSize = (int) (threshold * length);
    }

    @Override
    public V findElements(K key) {

        try {
            int pos = find(key);
            if(pos == -1){
                return null;
            }
            else{
                return hashVector[pos].getValue();
            }

        }catch (Exception e)
        {
            return null;
        }

    }

    //TODO put to do one run in vector, return {pos, pos of first null};
    public int find(K key)
    {
        try {
            long code = hashEngine.generateHashCode(key);
            int vectorPos = getElementVectorPos(code);

            int searchPos = vectorPos;

            if(hashVector[vectorPos] != null && hashVector[vectorPos].getKey().equals(key))
            {
                return vectorPos;
            }else
            {
                do{
                    if(hashVector[searchPos] != null && hashVector[searchPos].getKey().equals(key))
                    {
                        return searchPos;
                    }
                    searchPos = (searchPos + 1) % length;
                }while (searchPos != vectorPos);
            }

            return -1;
        }catch (Exception e){
            return -1;
        }
    }

    @Override
    public boolean insertItem(K key, V value) {
        if(actualSize == maxSize)
        {
            resizeVector();
        }

        try {
            long code = hashEngine.generateHashCode(key);
            int vectorPos = getElementVectorPos(code);
            int pos = vectorPos;
            int posFind = find(key);

            if(posFind != -1)
            {
                hashVector[posFind].setValue(value);
                return true;
            }
            else{
                do{
                    if (hashVector[pos]== null){
                        hashVector[pos] = new MyNode<>(code,key,value);
                        actualSize++;
                        return true;
                    }

                    pos = (pos + 1) % length;
                }while (pos != vectorPos );
            }

            return false;

        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public V removeElement(K key) {
        try{
            V aux;

            int removeFromPos = find(key);

            if(removeFromPos == -1)
            {
                return  null;
            }else{
                aux = hashVector[removeFromPos].getValue();
                hashVector[removeFromPos] = null;

                return aux;
            }

        }
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public boolean isEmpty() {
        return this.length != 0;
    }

    @Override
    public LinkedList<K> keys() {
        LinkedList<K> keys = new LinkedList<>();

        for (MyNode node: hashVector) {
            if(node != null)
            {
                keys.add((K) node.getKey());
            }
        }

        return keys;
    }

    @Override
    public LinkedList<V> values() {
        LinkedList<V> values = new LinkedList<>();

        for (MyNode node: hashVector) {
            if(node != null) {
                values.add((V) node.getValue());
            }
        }

        return values;
    }

    private synchronized void resizeVector()
    {
        int oldMaxSize = maxSize;
        MyNode[] oldVector = hashVector;
        length = Prime.calcArraySize(length * 2);
        hashVector = new MyNode[this.length];
        calcMaxSize();

        for (int i = 0; i<oldVector.length;i++) {
            if(oldVector[i] != null)
            {
                MyNode node = oldVector[i];
                insertItem((K)node.getKey(),(V)node.getValue());
            }
        }
    }

    private int getElementVectorPos(int hashCode)
    {
        return DefineIndex(hashCode,this.length);
    }
    private int getElementVectorPos(long hashCode)
    {
        return DefineIndex(hashCode,this.length);
    }

}
