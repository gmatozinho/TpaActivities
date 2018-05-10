package Polynomial;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;

import java.util.Arrays;
import java.util.LinkedList;

public class Polynomial {
    MyHash<Integer,Integer> hashTable;
    private LinkedList<String> termsList;
    LinkedList<Character> signal;




    public Polynomial(String polynomial)
    {
        if(polynomial != null || polynomial.isEmpty())
        {
            termsList = new LinkedList<>();
            signal = new LinkedList<>(Arrays.asList('-', '+', ' '));
            hashTable = new MyHashListChain<>();
            splitPolynomial(polynomial);
        }
    }

    public Polynomial sum(Polynomial polynomial)
    {
        return null;
    }

    public Polynomial multiply(Polynomial polynomial)
    {
        return null;
    }
    private void splitPolynomial(String line)
    {
        String word = "";
        //String newLine;
        if(line.toCharArray()[0] != '-')
        {
            line = "+" + line;
        }
        char[] letters = line.toCharArray();

        //for (char letter : letters )
        for (int i = 0; i < letters.length ; i++) {

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
            termsList.add(word);
        }
    }

    public String toString()
    {
        return null;
    }

    public LinkedList<String> getTermsList() {
        return termsList;
    }
}
