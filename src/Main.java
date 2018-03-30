import com.sun.org.apache.bcel.internal.generic.BIPUSH;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        CallHashsBench();
    }


    private static void CallHashsBench() throws IOException {
        Hash.StartArrays(100);
        HashAscii();
        HashPolynomial();
        HashBernstein();
        HashModifiedBernstein();
        HashFNV();
        HashJSW();
        HashELF();
    }

    private static void HashAscii() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            int sum = Hash.Ascii(text);
            Hash.ContColision(sum,Hash.getArrayAscii());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchAscii.csv");
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

        FileWriter file = WorkWithCsvFile.OpenFile("benchPolynomial.csv");
        int cont = 0;

        for (int value : Hash.getArrayPolynomial()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashBernstein() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = Hash.Bernstein(text);
            Hash.ContColision(sum,Hash.getArrayBernstein());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchBernstein.csv");
        int cont = 0;

        for (int value : Hash.getArrayBernstein()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashModifiedBernstein() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = Hash.ModifiedBernstein(text);
            Hash.ContColision(sum,Hash.getArrayModifiedBernstein());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchModifiedBernstein.csv");
        int cont = 0;

        for (int value : Hash.getArrayModifiedBernstein()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashFNV() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = Hash.FNV(text);
            Hash.ContColision(sum,Hash.getArrayFNV());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchFNV.csv");
        int cont = 0;

        for (int value : Hash.getArrayFNV()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashJSW() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = Hash.JSW(text);
            Hash.ContColision(sum,Hash.getArrayJSW());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchJSW.csv");
        int cont = 0;

        for (int value : Hash.getArrayJSW()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashELF() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            long sum = Hash.ELF(text);
            Hash.ContColision(sum,Hash.getArrayELF());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchELF.csv");
        int cont = 0;

        for (int value : Hash.getArrayELF()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }
}