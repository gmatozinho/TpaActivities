package MatrixLib;

class CoordMatrix {
    private String coordinates;

    static String makeCoordinate(int row, int column)
    {
        return row + ";" + column;
    }

    static int getRow(String coordinates)
    {
        return Integer.parseInt(coordinates.split(";")[1]);
    }

    static int getColumn(String coordinates)
    {
        return Integer.parseInt(coordinates.split(";")[0]);
    }
}
