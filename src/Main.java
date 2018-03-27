import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //System.out.println("Hello World");

        Hash.StartArrays(100);

        HashAscii();
        HashPolynomial();

        //System.out.println(Prime.decideArraySize(10));
    }

    private static void HashAscii() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            int sum = Hash.Ascii(text);
            Hash.ContColision(sum,Hash.getArrayAscii());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchascii.csv");
        int cont = 0;
        for (int value : Hash.getArrayAscii()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashPolynomial() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            int sum = Hash.Polynomial(text);
            Hash.ContColision(sum,Hash.getArrayPolynomial());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchpolynomial.csv");
        int cont = 0;

        for (int value : Hash.getArrayPolynomial()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }
}