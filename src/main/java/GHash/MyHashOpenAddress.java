package GHash;

import NumLib.Prime;

import java.util.LinkedList;

import static GHash.AuxHashFunctions.DefineIndex;

@SuppressWarnings("ALL")
public class MyHashOpenAddress<K,V> extends MyHash<K,V>{

    private MyNode[] hashVector;
    private int length;
    private int maxSize;
    private int actualSize;
    private float threshold = 0.40f;
    private HashEngine hashEngine;

    public MyHashOpenAddress() {
        this.hashEngine = new HashDefault();
        this.length = 1<<4;
        actualSize = 0;
        calcMaxSize();
        hashVector = new MyNode[this.length];
    }

    MyHashOpenAddress(int length) {
        this.hashEngine = new HashDefault();
        this.length = Prime.decideArraySize(length);
        actualSize = 0;
        calcMaxSize();
        hashVector = new MyNode[this.length];
    }

    public MyHashOpenAddress(HashEngine hashEngine) {
        this.hashEngine = hashEngine;
        this.length = Prime.decideArraySize(length);
        actualSize = 0;
        calcMaxSize();
        hashVector = new MyNode[this.length];
    }

    public MyHashOpenAddress(int length, HashEngine hashEngine) {
        this.hashEngine = hashEngine;
        this.length = Prime.decideArraySize(length);
        actualSize = 0;
        calcMaxSize();
        hashVector = new MyNode[this.length];
    }


    private void calcMaxSize()
    {
        this.maxSize = (int) (threshold * (Math.pow(length,2)));
    }

    @Override
    public V findElements(K key) {

        try{
            int code = hashEngine.generateHashCode(key);
            int vectorPos = getElementVectorPos(code);
            if(hashVector[vectorPos].getKey() == key)
            {
                return (V) hashVector[vectorPos].getValue();
            }else{
                int searchPos = vectorPos;
                while(true)
                {
                    searchPos ++;

                    if(searchPos == length)
                    {
                        searchPos = 0;
                    }else if(searchPos == vectorPos)
                    {
                        return null;
                    }
                    if(hashVector[searchPos].getKey() == key)
                    {
                        return (V) hashVector[searchPos].getValue();
                    }
                }
            }

        }
        catch (Exception e)
        {
            return null;
        }

    }

    @Override
    public boolean insertItem(K key, V value) {
        if(actualSize == maxSize)
        {
            resizeVector();
        }

        try{
            int myHashCode = hashEngine.generateHashCode(key);
            MyNode<K, V> node = new MyNode<>(myHashCode,key,value);

            int code = node.getMyHashCode();
            int vectorPos = getElementVectorPos(code);

            if(hashVector[vectorPos] == null)
            {
                hashVector[vectorPos] = node;
                actualSize ++;

            }else{
                if(hashVector[vectorPos].getKey() == key)
                {
                    hashVector[vectorPos].setValue(value);
                }else{
                    int searchPos = vectorPos;


                    while(true)
                    {
                        searchPos ++;

                        if(searchPos == length)
                        {
                            searchPos = 0;
                        }else if(searchPos == vectorPos)
                        {
                            return false;
                        }

                        if(hashVector[searchPos] == null)
                        {
                            hashVector[searchPos] = node;
                            actualSize ++;
                            break;
                        }
                        else if(hashVector[searchPos].getKey() == key)
                        {
                            hashVector[searchPos].setValue(value);
                            break;
                        }
                    }
                }
            }
            return  true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public V removeElement(K key) {
        try{
            V aux;
            int code = hashEngine.generateHashCode(key);
            int vectorPos = getElementVectorPos(code);
            if(hashVector[vectorPos].getKey() == key)
            {
                aux = (V) hashVector[vectorPos].getValue();
                hashVector[vectorPos] = null;
                actualSize --;
                return aux;
            }else{
                int searchPos = vectorPos;
                while(true)
                {
                    searchPos ++;

                    if(searchPos == length)
                    {
                        searchPos = 0;
                    }else if(searchPos == vectorPos)
                    {
                        return null;
                    }
                    if(hashVector[searchPos].getKey() == key)
                    {
                        aux = (V) hashVector[searchPos].getValue();
                        hashVector[vectorPos] = null;
                        actualSize --;
                        return aux;
                    }
                }
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
            keys.add((K) node.getKey());
        }

        return keys;
    }

    @Override
    public LinkedList<V> values() {
        LinkedList<V> values = new LinkedList<>();

        for (MyNode node: hashVector) {
            values.add((V) node.getValue());
        }

        return values;
    }

    private synchronized void resizeVector()
    {
        MyNode[] oldVector = hashVector;
        length = Prime.decideArraySize(length * 2);
        hashVector = new MyNode[this.length];
        calcMaxSize();

        for ( MyNode node: oldVector) {
            hashVector[getElementVectorPos(node.getMyHashCode())] = node;
        }

    }

    private int getElementVectorPos(int hashCode)
    {
        return DefineIndex(hashCode,this.length);
    }

}
