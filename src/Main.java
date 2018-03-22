import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //System.out.println("Hello World");
        //HashAscii();
        HashPolynomial();
    }

    private static void HashAscii() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            int sum = Hash.HashAscii(text);
            Hash.ContColision(sum,Hash.getArrayAscii());
            text = reader.readLine();
        }

        for (int value : Hash.getArrayAscii()) {
            System.out.println(value);
        }
    }

    private static void HashPolynomial() throws IOException {

        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            int sum = Hash.HashPolynomial(text);
            Hash.ContColision(sum,Hash.getArrayPolynomial());
            text = reader.readLine();
        }


        for (int value : Hash.getArrayPolynomial()) {
            System.out.println(value);
        }
    }
}