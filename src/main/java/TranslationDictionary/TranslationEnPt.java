package TranslationDictionary;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import WorkFilesLib.WorkWithFiles;

import java.io.BufferedReader;
import java.io.IOException;

public class TranslationEnPt {
    private static MyHash<String,String> dictionaryEnBr;
    private static String wordsFile = "eng-pt.txt";

    public TranslationEnPt() {
        this.dictionaryEnBr = new MyHashListChain<>();
    }


    public static TranslationEnPt create() throws IOException {
        TranslationEnPt translationEnPt = new TranslationEnPt();
        BufferedReader reader = WorkWithFiles.OpenFileToRead(wordsFile);

        String line = reader.readLine();
        while (line != null) {
            String[] auxVector = line.replaceAll("\t","").split(";");
            String key = auxVector[0];
            String value = auxVector[1];
            dictionaryEnBr.insertItem(key,value);
            
            line = reader.readLine();
        }

        reader.close();

        return translationEnPt;

    }
    
    public String Translate(String word) throws IOException {
        String string = dictionaryEnBr.findElements(word);

        if(string != null)
        {
            return string;
        }

        return "Não foi possivel traduzir";
        
    }

    public MyHash<String, String> getDictionaryEnBr() {
        return dictionaryEnBr;
    }
}
