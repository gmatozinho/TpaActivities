package HashLib.Functions;

import WorkFilesLib.WorkWithFiles;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

import static HashLib.Functions.AuxHashFunctions.ContColision;

public class BenchmarkHashFunctions {

    private static void HashAscii() throws IOException {

        BufferedReader reader = WorkWithFiles.OpenFileToRead("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            int sum = HashFunctions.Ascii(text);
            ContColision(sum, HashFunctions.getArrayAscii());
            text = reader.readLine();
        }

        FileWriter file = WorkWithFiles.OpenFileToWrite("benchAscii.csv");
        int cont = 0;
        for (int value : HashFunctions.getArrayAscii()) {
            WorkWithFiles.writeLine(file,WorkWithFiles.writeResultsHashBench(cont++,value));
        }

        WorkWithFiles.CloseWrittenFile(file);
    }

    private static void HashPolynomial() throws IOException {

        BufferedReader reader = WorkWithFiles.OpenFileToRead("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            int sum = HashFunctions.Polynomial(text);
            ContColision(sum, HashFunctions.getArrayPolynomial());
            text = reader.readLine();
        }

        FileWriter file = WorkWithFiles.OpenFileToWrite("benchPolynomial.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayPolynomial()) {
            WorkWithFiles.writeLine(file,WorkWithFiles.writeResultsHashBench(cont++,value));
        }

        WorkWithFiles.CloseWrittenFile(file);
    }

    private static void HashBernstein() throws IOException {

        BufferedReader reader = WorkWithFiles.OpenFileToRead("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = HashFunctions.Bernstein(text);
            ContColision(sum, HashFunctions.getArrayBernstein());
            text = reader.readLine();
        }

        FileWriter file = WorkWithFiles.OpenFileToWrite("benchBernstein.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayBernstein()) {
            WorkWithFiles.writeLine(file,WorkWithFiles.writeResultsHashBench(cont++,value));
        }

        WorkWithFiles.CloseWrittenFile(file);
    }

    private static void HashModifiedBernstein() throws IOException {

        BufferedReader reader = WorkWithFiles.OpenFileToRead("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = HashFunctions.ModifiedBernstein(text);
            ContColision(sum, HashFunctions.getArrayModifiedBernstein());
            text = reader.readLine();
        }

        FileWriter file = WorkWithFiles.OpenFileToWrite("benchModifiedBernstein.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayModifiedBernstein()) {
            WorkWithFiles.writeLine(file,WorkWithFiles.writeResultsHashBench(cont++,value));
        }

        WorkWithFiles.CloseWrittenFile(file);
    }

    private static void HashFNV() throws IOException {

        BufferedReader reader = WorkWithFiles.OpenFileToRead("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = HashFunctions.FNV(text);
            ContColision(sum, HashFunctions.getArrayFNV());
            text = reader.readLine();
        }

        FileWriter file = WorkWithFiles.OpenFileToWrite("benchFNV.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayFNV()) {
            WorkWithFiles.writeLine(file,WorkWithFiles.writeResultsHashBench(cont++,value));
        }

        WorkWithFiles.CloseWrittenFile(file);
    }

    private static void HashJSW() throws IOException {

        BufferedReader reader = WorkWithFiles.OpenFileToRead("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = HashFunctions.JSW(text);
            ContColision(sum, HashFunctions.getArrayJSW());
            text = reader.readLine();
        }

        FileWriter file = WorkWithFiles.OpenFileToWrite("benchJSW.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayJSW()) {
            WorkWithFiles.writeLine(file,WorkWithFiles.writeResultsHashBench(cont++,value));
        }

        WorkWithFiles.CloseWrittenFile(file);
    }

    private static void HashELF() throws IOException {

        BufferedReader reader = WorkWithFiles.OpenFileToRead("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            long sum = HashFunctions.ELF(text);
            ContColision(sum, HashFunctions.getArrayELF());
            text = reader.readLine();
        }

        FileWriter file = WorkWithFiles.OpenFileToWrite("benchELF.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayELF()) {
            WorkWithFiles.writeLine(file,WorkWithFiles.writeResultsHashBench(cont++,value));
        }

        WorkWithFiles.CloseWrittenFile(file);
    }

    public static void CallHashsBench() throws IOException {
        HashFunctions.StartArrays(100);
        HashAscii();
        HashPolynomial();
        HashBernstein();
        HashModifiedBernstein();
        HashFNV();
        HashJSW();
        HashELF();
    }
}
