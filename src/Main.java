import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        //System.out.println("Hello World");
        HashAscii();
    }

    private static void HashAscii() throws IOException {

        Hash hash = new Hash();
        BufferedReader reader = TreatWords.OpenFile("en-usa-20k.txt");
        String text = reader.readLine();
        while (text  != null) {
            int sum = TreatWords.ConvertStringToAsciiSum(text);
            hash.HashAscii(sum);
            text = reader.readLine();
        }

        for (int value : hash.getArrayAscii()) {
            System.out.println(value);
        }
    }
}