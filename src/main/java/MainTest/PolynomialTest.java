package MainTest;

import Polynomial.Polynomial;
import WorkFilesLib.WorkWithFiles;

import java.io.BufferedReader;

public class PolynomialTest {
    public static void main(String[] args) {

       MainRoutine("bdpoli.txt");

    }

    private static void MainRoutine(String polifile)
    {
        try {
            BufferedReader reader = WorkWithFiles.OpenFileToRead(polifile);

            Polynomial result = null;
            String line = reader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(",");
                String stringPolynomial = lineSplit[0];

                if(lineSplit.length >1 && result!=null) {
                    String operator = lineSplit[1];
                    result = Polynomial.doOperation(operator,result,new Polynomial(stringPolynomial));
                }
                else{
                    result = new Polynomial(stringPolynomial);
                }

                line = reader.readLine();
            }

            reader.close();

            assert result != null;
            System.out.println(result.toString());
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }


}
