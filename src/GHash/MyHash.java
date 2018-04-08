package GHash;

import NumBib.Prime;

import java.util.LinkedList;

import static GHash.AuxHashFunctions.DefineIndex;

class MyNode{
    private Object key;
    private Object object;

    MyNode(Object key, Object object) {
        this.key = key;
        this.object = object;
    }

    public void setKey(Object key) {
        this.key = key;
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
}


public class MyHash implements MyMap {
    private int lenght;
    private LinkedList[] hashVector;

    public MyHash() {
        this.lenght = 1<<4;
        hashVector = new LinkedList[this.lenght];
        for(int i = 0; i < hashVector.length; i++){
            hashVector[i] = new LinkedList<>();
        }
    }

    public MyHash(int length) {
        this.lenght = Prime.decideArraySize(length);
        hashVector = new LinkedList[this.lenght];
        for(int i = 0; i < hashVector.length; i++){
            hashVector[i] = new LinkedList<>();
        }
    }

    @Override
    public Object findElements(Object key) {
        int code = key.hashCode();
        int vectorPos = calcElementVectorPos(code);
        LinkedList<MyNode> list = hashVector[vectorPos];
        int listPos = getElementListPos(key,list);

        if(listPos != -1)
        {
            return list.get(listPos).getObject();
        }
        return null;
    }



    private int calcElementVectorPos(String word)
    {
        int sum = HashFunctions.Polynomial(word);
        return DefineIndex(sum,this.lenght);
    }

    private int calcElementVectorPos(int word)
    {
        //long sum = HashFunctions.Polynomial(word);
        return DefineIndex(word,this.lenght);
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



    @Override
    public boolean insertItem(Object key, Object object) {

        try{
            MyNode node = new MyNode(key,object);

            //String word = (String)key;
            int code = key.hashCode();
            int vectorPos = calcElementVectorPos(code);
            LinkedList<MyNode> list = hashVector[vectorPos];
            int listPos = getElementListPos(key,list);

            if(listPos == -1)
            {
                list.add(node);
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

        try{
            //String word = (String) key;
            int code = key.hashCode();
            int vectorPos = calcElementVectorPos(code);
            LinkedList<MyNode> list = hashVector[vectorPos];
            int listPos = getElementListPos(key,list);
            return list.remove(listPos).getObject();}
        catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public int size() {
        return this.lenght;
    }

    @Override
    public boolean isEmpty() {
        return this.lenght != 0;
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

    public LinkedList[] getHashVector() {
        return hashVector;
    }
}
