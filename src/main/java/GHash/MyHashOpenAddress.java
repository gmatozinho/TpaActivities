package GHash;

import NumLib.Prime;

import java.io.IOException;
import java.util.LinkedList;

import static GHash.AuxHashFunctions.DefineIndex;

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
        this.maxSize = (int) (threshold * (Math.pow(length,2)));
    }

    @Override
    public V findElements(K key) throws IOException {

        //TODO tratar a buscar, pois tem q comparar a chave da posição encontrada com a inserida
        try{
            int code = hashEngine.generateHashCode(key);
            int vectorPos = getElementVectorPos(code);

            return (V) hashVector[vectorPos].getValue();
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
            MyNode node = new MyNode(myHashCode,key,value);

            int code = node.getMyHashCode();
            int vectorPos = getElementVectorPos(code);
            //int pos = checaPos(vectorPos);

            //TODO Somente fazer inserção se não ouver elemento inserido ou se a chave da posição encontrada for igual ao valor
//            if(pos == -1)
//            {
//                hashVector[vectorPos] = node;
//                actualSize ++;
//            }else{
//                hashVector[vectorPos].setValue(value);
//            }

            return  true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public V removeElement(K key) {
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
    public LinkedList<K> keys() {
        return null;
    }

    @Override
    public LinkedList<V> values() {
        return null;
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
