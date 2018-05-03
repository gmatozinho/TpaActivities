package HashLib.Core;

import HashLib.HashEngine.HashDefault;
import HashLib.HashEngine.HashEngine;
import NumLib.Prime;

import java.util.LinkedList;

import static HashLib.Functions.AuxHashFunctions.DefineIndex;


@SuppressWarnings("ALL")
public class MyHashListChain<K,V> extends MyHash<K,V>  {

    private int length;
    private LinkedList[] hashVector;
    private int maxSize;
    private int actualSize;
    private float threshold = 0.40f;
    private HashEngine hashEngine;

    public MyHashListChain() {
        this.hashEngine = new HashDefault();
        this.length = 1<<4;
        actualSize = 0;
        calcMaxSize();
        hashVector = new LinkedList[this.length];
        fillVector();
    }

    public MyHashListChain(int length) {
        this.hashEngine = new HashDefault();
        this.length = Prime.calcArraySize(length);
        actualSize = 0;
        calcMaxSize();
        hashVector = new LinkedList[this.length];
        fillVector();
    }

    public MyHashListChain(HashEngine hashEngine) {
        this.hashEngine = hashEngine;
        this.length = Prime.calcArraySize(length);
        actualSize = 0;
        calcMaxSize();
        hashVector = new LinkedList[this.length];
        fillVector();
    }

    public MyHashListChain(int length, HashEngine hashEngine) {
        this.hashEngine = hashEngine;
        this.length = Prime.calcArraySize(length);
        actualSize = 0;
        calcMaxSize();
        hashVector = new LinkedList[this.length];
        fillVector();
    }

    private void fillVector()
    {
        for(int i = 0; i < hashVector.length; i++){
            hashVector[i] = new LinkedList<MyNode>();
        }
    }

    private void calcMaxSize()
    {
        this.maxSize = (int) (threshold * (Math.pow(length,2)));
    }

    @Override
    public V findElements(K key){

        try {
            long code = hashEngine.generateHashCode(key);
            int vectorPos = getElementVectorPos(code);
            LinkedList<MyNode> list = hashVector[vectorPos];
            int listPos = getElementListPos(key, list);

            if (listPos != -1) {
                return (V) list.get(listPos).getValue();
            }
            return null;
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

        try{
            long myHashCode = hashEngine.generateHashCode(key);
            MyNode node = new MyNode(myHashCode,key,value);

            long code = node.getMyHashCode();
            int vectorPos = getElementVectorPos(code);
            LinkedList<MyNode> list = hashVector[vectorPos];
            int listPos = getElementListPos(key,list);

            if(listPos == -1)
            {
                list.add(node);
                actualSize ++;
            }else{
                list.get(listPos).setValue(value);
            }

            return  true;
        }catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public V removeElement(K key) {

        V aux;
        try{
            long code = hashEngine.generateHashCode(key);
            int vectorPos = getElementVectorPos(code);
            LinkedList<MyNode> list = hashVector[vectorPos];
            int listPos = getElementListPos(key,list);
            aux = (V) list.remove(listPos).getValue();
            actualSize --;
            return aux;
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

        for (LinkedList<MyNode> list: hashVector) {
            if(list.size() > 0)
            {
                for (MyNode node:list) {
                    keys.add((K) node.getKey());
                }
            }
        }

        return keys;
    }

    @Override
    public LinkedList<V> values() {
        LinkedList<Object> values = new LinkedList<>();

        for (LinkedList<MyNode> list: hashVector) {
            if(list.size() > 0)
            {
                for (MyNode node:list) {
                    values.add(node.getValue());
                }
            }
        }

        return (LinkedList<V>) values;
    }

    private synchronized void resizeVector()
    {
        LinkedList[] oldVector = hashVector;
        length = Prime.calcArraySize(length * 2);
        hashVector = new LinkedList[this.length];
        calcMaxSize();
        fillVector();

        for ( LinkedList<MyNode> list: oldVector) {
            for ( MyNode node: list) {
                hashVector[getElementVectorPos(node.getMyHashCode())].add(node);
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

    private int getElementListPos(K key, LinkedList<MyNode> list)
    {
        for(int i=0;i<list.size();i++ )
        {
            if(key.equals(list.get(i).getKey()))
            {
                return i;
            }
        }

        return -1;
    }
}
