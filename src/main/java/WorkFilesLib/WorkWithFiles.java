package WorkFilesLib;

import java.io.*;

public class WorkWithFiles {

    public static int ConvertCharToAsciiValue(char letter)
    {
        return (int) letter;
    }

    public static BufferedReader OpenFileToRead(String fileName)
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

    public static FileWriter OpenFileToWrite(String fileName) throws IOException {
        try
        {
            return new FileWriter("results" + File.separator + fileName);
        }catch (Exception e)
        {
            return new FileWriter("results" + File.separator +fileName + System.currentTimeMillis());
        }

    }

    public static void CloseWrittenFile(FileWriter writer) throws IOException {
        writer.close();
    }

    public static void writeLine(FileWriter writer, String string) throws IOException {
        writer.write(string);
        writer.flush();
    }

    public static String writeResultsHashBench(int pos,int value){
        String separator = ",";
        return pos+separator+value +"\n";
    }

}
