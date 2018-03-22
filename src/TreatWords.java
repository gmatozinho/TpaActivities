import java.io.*;

public class TreatWords {

    public static int ConvertStringToAsciiSum(String word)
    {
        int sum =0;
        for (int i=0; i< word.length();i++)
        {
            sum += ConvertCharToAsciiValue(word.charAt(i));
        }

        return sum;
    }

    private static int ConvertCharToAsciiValue(char letter)
    {
        return (int) letter;
    }

    public static BufferedReader OpenFile(String fileName)
    {
        File file = new File(fileName);
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return reader;
    }
}
