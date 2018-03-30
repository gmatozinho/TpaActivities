import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.server.ExportException;
import java.util.Random;

public class WorkWithCsvFile {


    public static FileWriter OpenFile(String fileName) throws IOException {
        try
        {
            return new FileWriter("results" + File.separator + fileName);
        }catch (Exception e)
        {
            return new FileWriter("results" + File.separator +fileName + System.currentTimeMillis() +".csv");
        }

    }

    public static void CloseFile(FileWriter writer) throws IOException {
        writer.close();
    }

    public static void writeLine(FileWriter writer, String string) throws IOException {
        writer.write(string);
        writer.flush();
    }

    public static String writeResults(int pos,int value){
        String separator = ",";
        return pos+separator+value +"\n";
    }



}