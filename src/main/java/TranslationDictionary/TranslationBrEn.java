package TranslationDictionary;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import WorkFilesLib.WorkWithFiles;

import java.io.BufferedReader;
import java.io.IOException;

public class TranslationBrEn {
    private static MyHash<String,String> dictionaryBrEn;
    private static String wordsFile = "eng-pt.txt";

    public TranslationBrEn() {
        this.dictionaryBrEn = new MyHashListChain<>();
    }

    public static TranslationBrEn create() throws IOException {
        TranslationBrEn translationBrEn = new TranslationBrEn();
        BufferedReader reader = WorkWithFiles.OpenFileToRead(wordsFile);

        String line = reader.readLine();
        while (line != null) {
            String[] auxVector = line.replaceAll("\t","").split(";");
            String value = auxVector[0];

            String[] aux = auxVector[1].split(",");

            for (String key: aux
                 ) {
                dictionaryBrEn.insertItem(key,value);
            }

            line = reader.readLine();
        }

        reader.close();

        return translationBrEn;

    }

    public String Translate(String word) {
        String string = dictionaryBrEn.findElement(word);

        if(string != null)
        {
            return string;
        }

        return "Não foi possivel traduzir";

    }

    public MyHash<String, String> getDictionaryEnBr() {
        return dictionaryBrEn;
    }

}
