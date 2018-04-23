package DocumentLib;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import TreatFilesAndTextLib.TreatWords;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Document {
    MyHash<String,Integer> frequencyTab = null;
    static LinkedList<String> wordsList;
    static String separator = " ,;:.!?()[]''-/|{}";

    public Document()
    {
        wordsList = new LinkedList<>();
        frequencyTab = new MyHashListChain();
    }

    public static Document create(String fileName) throws IOException {
        Document document = new Document();
        BufferedReader reader = TreatWords.OpenFile(fileName);
        
        String line = reader.readLine();
        while (line != null)
        {
            splitWord(line);
            line = reader.readLine();
        }
                
        return document;
    }

    public void makeTabFreq(String fileName){
    }

    public String save(String fileName){return null;}

    public MyHash getTabFreq(){return null;}


    static void splitWord(String line)
    {
        String word = "";
        char[] letters = line.toCharArray();
        char[] separators = separator.toCharArray();
        LinkedList<Character> list = new LinkedList<>();
        for (char c : separators) {
            list.add(c);
        }

        list.add('\"');
        list.add(' ');

        System.out.println(separators);
        for (char letter : letters ) {
            if(!list.contains(letter)){
                word=word+letter;
            }else if(word !="")
            {
                wordsList.add(word);
                word ="";
            }
        }
        if(word !="")
        {
            wordsList.add(word);
        }
    }

    public LinkedList<String> getWordsList() {
        return wordsList;
    }
}
