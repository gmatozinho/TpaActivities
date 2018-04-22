package MatrixLib;

public class CoordMatrix {
    private int[] coordinates;

    public CoordMatrix(int x,int y)
    {
        coordinates = new int[2];
        coordinates[0] = x;
        coordinates[1] = y;
    }

    public int getX()
    {
        return coordinates[0];
    }

    public int getY()
    {
        return coordinates[1];
    }
}
