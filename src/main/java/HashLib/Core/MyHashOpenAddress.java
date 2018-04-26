package HashLib.Core;

import HashLib.CalcHashEngine.HashDefault;
import HashLib.CalcHashEngine.HashEngine;
import NumLib.Prime;

import java.util.LinkedList;

import static HashLib.Functions.AuxHashFunctions.DefineIndex;

@SuppressWarnings("ALL")
public class MyHashOpenAddress<K,V> extends MyHash<K,V>{

    private MyNode[] hashVector;
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
        this.maxSize = (int) (threshold * length);
    }

    @Override
    public V findElements(K key) {


        try {
            long code = hashEngine.generateHashCode(key);
            int vectorPos = getElementVectorPos(code);
            int pos = vectorPos;

            if(hashVector[vectorPos] != null && hashVector[vectorPos].getKey().equals(key))
            {
                return (V) hashVector[vectorPos].getValue();
            }else
            {
                pos = (pos + 1) % length;
                while (pos != vectorPos )//&& hashVector[pos].getKey() != key
                {
                    if(hashVector[pos] != null && hashVector[pos].getKey().equals(key))
                    {
                        return  (V) hashVector[pos].getValue();
                    }
                    pos = (pos + 1) % length;
                }
                return null;
            }

        }catch (Exception e)
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

        try {
            long code = hashEngine.generateHashCode(key);
            int vectorPos = getElementVectorPos(code);
            int pos = vectorPos;

            if(hashVector[vectorPos] == null)// && hashVector[vectorPos].getKey().equals(key))
            {
                hashVector[vectorPos] = new MyNode<>(code,key,value);
                actualSize++;
                return true;
            }
            else
            {
                pos = (pos + 1) % length;
                while (pos != vectorPos )
                {
                    if(hashVector[pos] != null && hashVector[pos].getKey().equals(key))
                    {
                        hashVector[pos].setValue(value);
                        return true;
                    }else if (hashVector[pos]== null){
                        hashVector[pos] = new MyNode<>(code,key,value);
                        actualSize++;
                        return true;
                    }

                    pos = (pos + 1) % length;
                }

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
            long code = hashEngine.generateHashCode(key);
            int vectorPos = getElementVectorPos(code);

            if(hashVector[vectorPos] == null || hashVector[vectorPos].getKey() != key)
            {
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
                    if(hashVector[searchPos] != null)
                    {
                        if(hashVector[searchPos].getKey() == key)
                        {
                            aux = (V) hashVector[searchPos].getValue();
                            hashVector[searchPos] = null;
                            actualSize --;
                            return aux;
                        }
                    }
                }

            }
            else
            {
                aux = (V) hashVector[vectorPos].getValue();
                hashVector[vectorPos] = null;
                actualSize --;
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
        length = Prime.decideArraySize(length * 2);
        hashVector = new MyNode[this.length];
        calcMaxSize();

        for (int i = 0; i<oldMaxSize;i++) {
            MyNode node = oldVector[i];
            if(node!= null)
            {
                hashVector[getElementVectorPos(node.getMyHashCode())] = node;
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
