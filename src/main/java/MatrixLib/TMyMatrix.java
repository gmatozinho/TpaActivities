package MatrixLib;

interface TMyMatrix {
    TMyMatrix load(String fileName);
    String save(String fileName);
    TMyMatrix multiply(TMyMatrix matrix);
    TMyMatrix sum(TMyMatrix matrix);
    TMyMatrix transposed();
    TMyMatrix multiplyByValue(int value);


}
