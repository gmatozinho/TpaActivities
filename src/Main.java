import GHash.HashFunctions;
import GHash.MyHash;
import TreatFilesAndText.TreatWords;
import TreatFilesAndText.WorkWithCsvFile;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;

public class Main {

    public static void main(String[] args) throws IOException {
        CallHashsBench();
    }


    private static void CallHashsBench() throws IOException {
//        HashFunctions.StartArrays(100);
//        HashAscii();
//        HashPolynomial();
//        HashBernstein();
//        HashModifiedBernstein();
//        HashFNV();
//        HashJSW();
//        HashELF();
        MyHash hash = new MyHash(100);

        Pessoa pessoa = new Pessoa("Fabricio",23,"braquinho");

        hash.insertItem(pessoa.nome,pessoa);
        for (int i=0;i<hash.size();i++){
            System.out.println(hash.getHashVector()[i].size());
        }

        Pessoa pessoa1 = (Pessoa) hash.findElements(pessoa.nome);
        System.out.println(pessoa1.getCor());
    }

    static class Pessoa
    {
        private String nome;
        private int idade;
        private String cor;

        Pessoa(String nome, int idade, String cor) {
            this.nome = nome;
            this.idade = idade;
            this.cor = cor;
        }

        public String getNome() {
            return nome;
        }

        public int getIdade() {
            return idade;
        }

        public String getCor() {
            return cor;
        }
    }

    private static void HashAscii() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            int sum = HashFunctions.Ascii(text);
            HashFunctions.ContColision(sum, HashFunctions.getArrayAscii());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchAscii.csv");
        int cont = 0;
        for (int value : HashFunctions.getArrayAscii()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashPolynomial() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            int sum = HashFunctions.Polynomial(text);
            HashFunctions.ContColision(sum, HashFunctions.getArrayPolynomial());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchPolynomial.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayPolynomial()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashBernstein() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = HashFunctions.Bernstein(text);
            HashFunctions.ContColision(sum, HashFunctions.getArrayBernstein());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchBernstein.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayBernstein()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashModifiedBernstein() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = HashFunctions.ModifiedBernstein(text);
            HashFunctions.ContColision(sum, HashFunctions.getArrayModifiedBernstein());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchModifiedBernstein.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayModifiedBernstein()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashFNV() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = HashFunctions.FNV(text);
            HashFunctions.ContColision(sum, HashFunctions.getArrayFNV());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchFNV.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayFNV()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashJSW() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            BigInteger sum = HashFunctions.JSW(text);
            HashFunctions.ContColision(sum, HashFunctions.getArrayJSW());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchJSW.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayJSW()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }

    private static void HashELF() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            long sum = HashFunctions.ELF(text);
            HashFunctions.ContColision(sum, HashFunctions.getArrayELF());
            text = reader.readLine();
        }

        FileWriter file = WorkWithCsvFile.OpenFile("benchELF.csv");
        int cont = 0;

        for (int value : HashFunctions.getArrayELF()) {
            WorkWithCsvFile.writeLine(file,WorkWithCsvFile.writeResults(cont++,value));
        }

        WorkWithCsvFile.CloseFile(file);
    }
}