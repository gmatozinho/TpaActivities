package MatrixLib;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;
import TreatFilesAndTextLib.TreatWords;
import TreatFilesAndTextLib.WorkWithCsvFile;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class MyMatrix {
    private int rows;
    private int columns;
    private MyHash<String,Integer> matrix;

    private MyMatrix()
    {
        rows =0;
        columns =0;

        matrix = new MyHashListChain<>();
    }

    private MyMatrix(int rows, int columns)
    {
        this.rows = rows;
        this.columns = columns;
        matrix = new MyHashListChain<>();
    }

    private boolean insert(int x, int y, int value)
    {
        try{
            if(value != 0)
            {
                String coordMatrix = CoordMatrix.makeCoordinate(x,y);
                matrix.insertItem(coordMatrix,value);
            }else{
                String coordMatrix = CoordMatrix.makeCoordinate(x,y);
                matrix.insertItem(coordMatrix,null);
            }

            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    public Integer remove(int x,int y)
    {
        String coordMatrix = CoordMatrix.makeCoordinate(x,y);
        return matrix.removeElement(coordMatrix);
    }

    private Integer find(int x, int y)
    {
        String coordMatrix = CoordMatrix.makeCoordinate(x,y);
        return find(coordMatrix);
    }

    private Integer find(String coordMatrix)
    {
        try {
            Object w = matrix.findElements(coordMatrix);
            if(null == w)
            {
                return 0;
            }
            return (Integer)w;
        } catch (IOException e) {
            return 0;
        }

    }


    public LinkedList<Integer> values()
    {
        LinkedList<Integer> values = new LinkedList<>();
        LinkedList<String> keys = matrix.keys();

        for (String key: keys) {
            values.add(find(key));
        }

        return values;
    }

    public static MyMatrix load(String fileName) throws IOException {
        MyMatrix myMatrix = new MyMatrix();
        BufferedReader reader = TreatWords.OpenFile(fileName);

        String line = reader.readLine();
        while (line  != null  && !line.isEmpty()) {
            String[] auxArray = line.split(";");
            myMatrix.insert(Integer.parseInt(auxArray[0]),Integer.parseInt(auxArray[1]),Integer.parseInt(auxArray[2]));
            line = reader.readLine();
        }

        reader.close();

        defineRowsAndColumns(myMatrix);
        return myMatrix;
    }

    public String save(String fileName) throws IOException {
        String separator = ";";
        FileWriter file = WorkWithCsvFile.OpenFile(fileName);
        String line;
        LinkedList<String> coordenates = matrix.keys();
        for (String coordMatrix: coordenates) {
            int value = matrix.findElements(coordMatrix);
            line = "" + CoordMatrix.getColumn(coordMatrix) + separator + CoordMatrix.getColumn(coordMatrix) + separator + value +"\n";
            WorkWithCsvFile.writeLine(file,line);
        }

        WorkWithCsvFile.CloseFile(file);

        return fileName;
    }

    public MyMatrix multiply(MyMatrix matrix2) {
        MyMatrix matrix1 = this;
        if (!matrix1.isMultiplicationCompatible(matrix2)){
            throw new IllegalArgumentException(
                    "Matrices are not multiplication compatible");
        }

        MyMatrix resultMatrix = new MyMatrix(matrix1.getRows(), matrix2.getColumns());
        for (int i = 0; i < matrix1.rows; i++) {
            for (int j = 0; j < matrix2.columns; j++) {
                for (int k = 0; k < matrix1.columns; k++) {
                    int value1 = matrix1.find(i,k);
                    int value2 = matrix2.find(k,j);
                    int result = value1 * value2;
                    resultMatrix.increment(i, j, result);
                }
            }
        }

        return resultMatrix;
    }

    private int getRows() {
        return rows;
    }

    private int getColumns() {
        return columns;
    }

    public MyMatrix sum(MyMatrix m2) {
        MyMatrix m1 = this;

        if (!m1.isAdditionCompatible(m2)){
            throw new IllegalArgumentException(
                    "Matrices are not addition compatible");
        }

        MyMatrix resultMatrix = new MyMatrix(rows,columns);
        for (int i = 0; i < m1.rows; i++) {
            for (int j = 0; j < m1.columns; j++) {
                resultMatrix.increment(i,j,m1.find(i,j));
                resultMatrix.increment(i,j,m2.find(i,j));
            }
        }

        return resultMatrix;
    }

    public MyMatrix transposed() {
        MyMatrix m = this;
        MyMatrix resultMatrix = new MyMatrix(columns, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int value = m.find(i,j);
                resultMatrix.insert(j, i, value);
            }
        }
        return resultMatrix;
    }

    public MyMatrix multiplyByValue(int multiplier) {
        MyMatrix m = this;
        MyMatrix resultMatrix = new MyMatrix(columns, rows);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int value = m.find(i,j) * multiplier;
                resultMatrix.insert(i, j, value);
            }
        }
        return resultMatrix;
    }

    private void increment(int row, int column, int increment){
        if (row >= rows || column >= columns){
            throw new IllegalArgumentException("Invalid index");
        }

        Integer value = find(row, column);

        if (value != null)
        {
            value += increment;
            insert(row, column, value);
        }else{
            value = 0;
            value += increment;
            insert(row, column, value);
        }
    }

    private boolean isMultiplicationCompatible(MyMatrix m2){
        MyMatrix m1 = this;
        boolean compatible = false;

        if (m1.getColumns() == m2.getRows()){
            compatible = true;
        }
        return compatible;
    }

    private boolean isAdditionCompatible(MyMatrix m2){
        MyMatrix m1 = this;
        boolean compatible = false;

        if (m1.getRows() == m2.getRows() &&
                m1.getColumns() == m2.getColumns()){

            compatible = true;
        }
        return compatible;
    }

    private static void defineRowsAndColumns(MyMatrix matrix)
    {
        int auxRows = 0;
        int auxColumns = 0;

        LinkedList<String> keys = matrix.matrix.keys();
        String checker = keys.get(0);

        int checkerRow = CoordMatrix.getRow(checker);
        int checkerColumns = CoordMatrix.getColumn(checker);

        for (String coord : keys) {
            if(CoordMatrix.getRow(coord) == checkerRow)
            {
                auxRows+=1;
            }
            if(CoordMatrix.getColumn(coord) == checkerColumns)
            {
                auxColumns +=1;
            }
        }

        matrix.rows = auxRows;
        matrix.columns = auxColumns;
    }

}
