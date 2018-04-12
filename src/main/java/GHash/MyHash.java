package GHash;

import ByteLib.ByteArray;
import NumLib.Prime;

import java.io.IOException;
import java.util.LinkedList;

import static GHash.AuxHashFunctions.DefineIndex;

class MyNode{
    private Object key;
    private Object object;
    private int myHashCode;

    MyNode(Object key, Object object,int myHashCode) {
        this.key = key;
        this.object = object;
        this.myHashCode = myHashCode;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getKey() {
        return key;
    }

    public Object getObject() {
        return object;
    }

    public int getMyHashCode() {
        return myHashCode;
    }

    public void setMyHashCode(int myHashCode) {
        this.myHashCode = myHashCode;
    }
}


public class MyHash implements MyMap {
    private int length;
    private LinkedList[] hashVector;
    private int maxSize;
    private int actualSize;
    private float threshold = 0.75f;

    public MyHash() {
        this.length = 1<<4;
        actualSize = 0;
        calcMaxSize();
        hashVector = new LinkedList[this.length];
        fillVector();
    }

    public MyHash(int length) {
        this.length = Prime.decideArraySize(length);
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
    public Object findElements(Object key) throws IOException {
        int code = generateHashCode(key);
        int vectorPos = getElementVectorPos(code);
        LinkedList<MyNode> list = hashVector[vectorPos];
        int listPos = getElementListPos(key,list);

        if(listPos != -1)
        {
            return list.get(listPos).getObject();
        }
        return null;
    }


    @Override
    public boolean insertItem(Object key, Object object) {

        if(actualSize == maxSize)
        {
            resizeVector();
        }

        try{
            int myHashCode = generateHashCode(key);
            MyNode node = new MyNode(key,object,myHashCode);

            int code = node.getMyHashCode();
            int vectorPos = getElementVectorPos(code);
            LinkedList<MyNode> list = hashVector[vectorPos];
            int listPos = getElementListPos(key,list);

            if(listPos == -1)
            {
                list.add(node);
                actualSize ++;
            }else{
                list.get(listPos).setObject(object);
            }

            return  true;
        }catch (Exception e)
        {
            return false;
        }

    }

    @Override
    public Object removeElement(Object key) {

        Object aux;
        try{
            int code = generateHashCode(key);
            int vectorPos = getElementVectorPos(code);
            LinkedList<MyNode> list = hashVector[vectorPos];
            int listPos = getElementListPos(key,list);
            aux = list.remove(listPos).getObject();
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
    public LinkedList<Object> keys() {
        LinkedList<Object> keys = new LinkedList<>();

        for (LinkedList<MyNode> list: hashVector) {
            if(list.size() > 0)
            {
                for (MyNode node:list) {
                    keys.add(node.getKey());
                }
            }
        }

        return keys;
    }

    @Override
    public LinkedList<Object> values() {
        LinkedList<Object> values = new LinkedList<>();

        for (LinkedList<MyNode> list: hashVector) {
            if(list.size() > 0)
            {
                for (MyNode node:list) {
                    values.add(node.getObject());
                }
            }
        }

        return values;
    }

    private int generateHashCode(Object key) throws IOException {
        byte[] bytes = ByteArray.toBytesStream(key);
        return HashFunctions.Polynomial(bytes);
    }

    private synchronized void resizeVector()
    {
        LinkedList[] oldVector = hashVector;
        length = Prime.decideArraySize(length * 2);
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

    private int getElementListPos(Object key,LinkedList<MyNode> list)
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
