package Polynomial;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.logging.Level;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@SuppressWarnings("ALL")
public class Polynomial {
    private MyHash<Integer,Integer> hashTable;
    private LinkedList<String> termsList;
    private LinkedList<Character> signal;

    public Polynomial(String polynomial)
    {
        if(polynomial != null || polynomial.isEmpty())
        {
            termsList = new LinkedList<>();
            signal = new LinkedList<>(Arrays.asList('-', '+'));
            hashTable = new MyHashListChain<>();
            buildHashMapPolynomial(polynomial);
        }
    }

    private Polynomial(MyHash polynomial)
    {
        if(polynomial != null || polynomial.isEmpty())
        {
            termsList = new LinkedList<>();
            signal = new LinkedList<>(Arrays.asList('-', '+'));
            hashTable = polynomial;
        }
    }

    public Polynomial sum(Polynomial polynomial)
    {
        MyHash<Integer,Integer> auxHash;

        if(hashTable.size() > polynomial.getHashTable().size())
        {
            auxHash = insideSumRoutine(hashTable,polynomial.getHashTable());

        }else{
            auxHash = insideSumRoutine(polynomial.getHashTable(),hashTable);
        }

        return new Polynomial(auxHash);
    }

    public Polynomial multiply(Polynomial polynomial){
        LinkedList<Integer> keys1 = hashTable.keys();
        LinkedList<Integer> keys2 = polynomial.getHashTable().keys();
        MyHash<Integer,Integer> auxHash = new MyHashListChain<>();

        for (int key1: keys1) {
            for (int key2: keys2) {
                int newKey = key1+key2;
                try {
                    int newValue = hashTable.findElements(key1) * polynomial.getHashTable().findElements(key2);
                    insertPolynomial(auxHash,newKey,newValue);
                } catch (IOException e) {
                    LOGGER.log(Level.ALL,e.toString());
                }
            }

        }

        return new Polynomial(auxHash);
    }

    public String toString()
    {
        String polynomial = "";
        boolean first = true;
        LinkedList<Integer> keys = hashTable.keys();
        keys.sort(Collections.reverseOrder());

        for (int key : keys) {

                String valueText;
                String keyText = "";

                int value = 0;
                try {
                    value = hashTable.findElements(key);
                } catch (IOException e) {
                    LOGGER.log(Level.ALL,e.toString());
                }

                if(value>0 && !first)
                {
                    if(value == 1) {
                        valueText = "+";
                    }else{
                        valueText = "+" + value;
                    }

                }else{
                    if(value == -1){
                        valueText = "-";
                    }else {
                        valueText = "" + value;
                    }
                    first = false;
                }

                if(key == 1)
                {
                    keyText = "x";
                }else if(key > 1)
                {
                    keyText = "x" + key;
                }

                polynomial +=  valueText+ keyText;

        }
        return polynomial;
    }

    public static Polynomial doOperation(String operator, Polynomial polynomial1, Polynomial polynomial2)
    {
        switch (operator)
        {
            case "+":
                return polynomial1.sum(polynomial2);
            case "-":
                return polynomial1.sum(polynomial2.multiply(new Polynomial("-1")));
            case "*":
                return polynomial1.multiply(polynomial2);
            default:
                return polynomial1;
        }
    }

    private MyHash insideSumRoutine(MyHash<Integer,Integer> poly1, MyHash<Integer,Integer> poly2)
    {
        MyHashListChain<Integer,Integer> auxHash = new MyHashListChain<>();

        for (int key : poly1.keys()) {
            try {
                Integer valuePoly1 = poly1.findElements(key);
                Integer valuePoly2 = poly2.findElements(key);

                if(valuePoly2 == null){
                    insertPolynomial(auxHash,key,valuePoly1);
                }else{
                    insertPolynomial(auxHash,key,valuePoly1+valuePoly2);
                }

            } catch (IOException e) {
                LOGGER.log(Level.ALL,e.toString());
            }
        }

        for (int key: poly2.keys()) {
            if(!poly1.keys().contains(key))
            {
                try {
                    insertPolynomial(auxHash,key,poly2.findElements(key));
                } catch (IOException e) {
                    LOGGER.log(Level.ALL,e.toString());
                }
            }
        }

        return  auxHash;
    }

    private void insertPolynomial(MyHash<Integer,Integer> myHash,int key,int value){
        LinkedList<Integer> keys = myHash.keys();

        if(value ==0)return;
        if(keys.contains(key))
        {
            try {
                int newValue = myHash.findElements(key) + value;
                myHash.insertItem(key,newValue);
            } catch (IOException e) {
                LOGGER.log(Level.ALL,e.toString());
            }
        }
        else{
            try {
                myHash.insertItem(key,value);
            } catch (IOException e) {
                LOGGER.log(Level.ALL,e.toString());
            }
        }
    }

    private void splitPolynomial(String line)
    {
        int i;
        String word = "";
        if(line.toCharArray()[0] != '-')
        {
            line = "+" + line;
        }
        char[] letters = line.toCharArray();

        for (i = 0; i < letters.length ; i++) {

            if(!signal.contains(letters[i])){
                word=word+letters[i];
            }else if(!word.equals(""))
            {
                word = letters[(i-word.length())-1] + word;
                termsList.add(word.toLowerCase());
                word ="";
            }
        }
        if(!word.equals(""))
        {
            word = letters[(i-word.length())-1] + word;
            termsList.add(word);
        }
    }

    private void buildHashMapPolynomial(String polynomial)
    {
        splitPolynomial(polynomial);

        for (String word: termsList) {
            String[] terms = word.split("x");

            if(terms.length == 1)
            {
                if(terms[0].length() == 1 && signal.contains((terms[0].charAt(0))))
                {
                    String term0 = terms[0] + 1;
                    String term1 = "1";
                    terms = new String[]{term0,term1};
                }else
                {
                    if((word.toCharArray()[word.length()-1]) == 'x')
                    {
                        String term0 = terms[0];
                        String term1 = "1";
                        terms = new String[]{term0,term1};
                    }else{
                        String term0 = terms[0];
                        String term1 = "0";
                        terms = new String[]{term0,term1};
                    }

                }
            }else if(terms[0].length() == 1 &&  signal.contains((terms[0].charAt(0))))
            {
                String term0 = terms[0] + 1;
                terms = new String[]{term0,terms[1]};
            }

            int key = Integer.parseInt(terms[1]);
            int value = Integer.parseInt(terms[0]);

            try {
                hashTable.insertItem(key,value);
            } catch (IOException e) {
                LOGGER.log(Level.ALL,e.toString());
            }
        }

    }

    public LinkedList<String> getTermsList() {
        return termsList;
    }

    public MyHash<Integer, Integer> getHashTable() {
        return hashTable;
    }
}
