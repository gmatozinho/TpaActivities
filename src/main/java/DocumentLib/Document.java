package DocumentLib;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import HashLib.Core.MyHashOpenAddress;
import WorkFilesLib.WorkWithFiles;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class Document {
    private MyHash<String,Integer> frequencyTab;
    private static LinkedList<String> wordsList;
    private static String separator = "  \",;:.!?()[]''-/|{}“”1234567890";
    private static LinkedList<Character> separators;

    private Document()
    {
        wordsList = new LinkedList<>();
        frequencyTab = new MyHashOpenAddress<>();
        createSeparatorList();
    }

    public static Document create(String fileName) throws IOException {
        Document document = new Document();
        BufferedReader reader = WorkWithFiles.OpenFileToRead(fileName);
        
        String line = reader.readLine();
        while (line != null)
        {
            splitWord(line);
            line = reader.readLine();
        }

        reader.close();
                
        return document;
    }

    public void makeTabFreq(String stopWordsFileName) throws IOException {
        LinkedList<String> stopWordList = createStopWordList(stopWordsFileName);

        for (String word: wordsList) {
            if(!stopWordList.contains(word))
            {
                if(frequencyTab.findElements(word) == null)
                {
                    frequencyTab.insertItem(word,1);
                }else{
                    int value = frequencyTab.findElements(word);
                    frequencyTab.insertItem(word,value+1);
                }
            }
        }
    }

    public String save(String fileName) throws IOException {
        FileWriter file = WorkWithFiles.OpenFileToWrite(fileName);
        for (String word : frequencyTab.keys()) {
            int value = frequencyTab.findElements(word);
            String line = word + ";" + value + "\n";
            WorkWithFiles.writeLine(file,line);
        }

        WorkWithFiles.CloseWrittenFile(file);
        return fileName;
    }

    public MyHash getTabFreq(){
        return frequencyTab;
    }


    private static void splitWord(String line)
    {
        String word = "";
        char[] letters = line.toCharArray();

        for (char letter : letters ) {
            if(!separators.contains(letter)){
                word=word+letter;
            }else if(!word.equals(""))
            {
                wordsList.add(word);
                word ="";
            }
        }
        if(!word.equals(""))
        {
            wordsList.add(word);
        }
    }

    private LinkedList<String> createStopWordList(String fileName) throws IOException {

        LinkedList<String> stopWordList = new LinkedList<>();

        BufferedReader reader = WorkWithFiles.OpenFileToRead(fileName);
        String line = reader.readLine();
        while (line != null)
        {
            stopWordList.add(line);
            line = reader.readLine();
        }

        reader.close();

        return stopWordList;

    }

    private void createSeparatorList()
    {
        char[] charSeparator = separator.toCharArray();
        separators = new LinkedList<>();

        for (char c : charSeparator ) {
            separators.add(c);
        }

    }

    public LinkedList<String> getWordsList() {
        return wordsList;
    }
}
