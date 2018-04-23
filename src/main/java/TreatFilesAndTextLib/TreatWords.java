package TreatFilesAndTextLib;

import java.io.*;
//TODO TreatTHisClass and WorkWithCsv Class
public class TreatWords {

    public static int ConvertCharToAsciiValue(char letter)
    {
        return (int) letter;
    }

    public static BufferedReader OpenFile(String fileName)
    {
        File file = new File("database/"+fileName);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return reader;
    }
}
