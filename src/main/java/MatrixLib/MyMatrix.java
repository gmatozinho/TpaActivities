package MatrixLib;

import HashLib.Core.MyHash;
import HashLib.Core.MyHashListChain;

//TODO FINISH THIS
public class MyMatrix implements TMyMatrix {
    private MyHash<CoordMatrix,Integer> Matrix;

    public MyMatrix()
    {
        Matrix = new MyHashListChain<>();
    }

    @Override
    public TMyMatrix load(String fileName) {
        return this;
    }

    @Override
    public String save(String fileName) {
        return null;
    }

    @Override
    public TMyMatrix multiply(TMyMatrix matrix) {
        return this;
    }

    @Override
    public TMyMatrix sum(TMyMatrix matrix) {
        return this;
    }

    @Override
    public TMyMatrix transposed() {
        return this;
    }

    @Override
    public TMyMatrix multiplyByValue(int value) {
        return this;
    }
}
