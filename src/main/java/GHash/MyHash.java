package GHash;

import NumBib.Prime;
import sun.security.util.Length;

import java.util.LinkedList;

import static GHash.AuxHashFunctions.DefineIndex;


//TODO put all sincronized
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
    private int maxSize;
    private int actualSize;
    private float threshold = 0.75f;

    public MyHash() {
        this.lenght = 1<<4;
        actualSize = 0;
        calcMaxSize();
        hashVector = new LinkedList[this.lenght];
//        for(int i = 0; i < hashVector.length; i++){
//            hashVector[i] = new LinkedList<MyNode>();
//        }

        fillVector();
    }

    public MyHash(int length) {
        this.lenght = Prime.decideArraySize(length);
        actualSize = 0;
        calcMaxSize();
        hashVector = new LinkedList[this.lenght];
//        for(int i = 0; i < hashVector.length; i++){
//            hashVector[i] = new LinkedList<MyNode>();
//        }
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
        this.maxSize = (int) (threshold * (Math.pow(lenght,2)));
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

    private synchronized void resizeVector()
    {
        LinkedList[] oldVector = hashVector;
        lenght = Prime.decideArraySize(lenght * 2);
        hashVector = new LinkedList[this.lenght];
        calcMaxSize();
        fillVector();

        for ( LinkedList<MyNode> list: oldVector) {
            for ( MyNode node: list) {
                insertItem(node.getKey(),node.getObject());
            }
        }
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

        if(actualSize == maxSize)
        {
            resizeVector();
        }

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
            //String word = (String) key;
            int code = key.hashCode();
            int vectorPos = calcElementVectorPos(code);
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
