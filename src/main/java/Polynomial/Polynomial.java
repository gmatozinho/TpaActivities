package Polynomial;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;

import static java.lang.Character.isDigit;

public class Polynomial {
    private MyHash<Integer,Integer> hashTable;
    private LinkedList<String> termsList;
    LinkedList<Character> signal;

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

    public Polynomial(MyHash polynomial)
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

    private MyHash insideSumRoutine(MyHash<Integer,Integer> poly1, MyHash<Integer,Integer> poly2)
    {
        MyHashListChain<Integer,Integer> auxHash = new MyHashListChain<>();

        for (int key: poly1.keys()) {
            try {
                Integer valuePoly1 = poly1.findElements(key);
                Integer valuePoly2 = poly2.findElements(key);

                if(valuePoly2 == null){
                    auxHash.insertItem(key,valuePoly1);
                }else{
                    auxHash.insertItem(key,valuePoly1+valuePoly2);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return  auxHash;
    }

    public Polynomial multiply(Polynomial polynomial)
    {


        return null;
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
                    String term0 = terms[0];
                    String term1 = "0";
                    terms = new String[]{term0,term1};
                }
            }

            int key = Integer.parseInt(terms[1]);
            int value = Integer.parseInt(terms[0]);

            try {
                hashTable.insertItem(key,value);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String toString()
    {
        String polynomial = "";
        boolean first = true;
        for (int key : hashTable.keys()) {


                String valueText;
                String keyText = "";

                int value = 0;
                try {
                    value = hashTable.findElements(key);
                } catch (IOException e) {
                    e.printStackTrace();
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


    public LinkedList<String> getTermsList() {
        return termsList;
    }

    public MyHash<Integer, Integer> getHashTable() {
        return hashTable;
    }
}
