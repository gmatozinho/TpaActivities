public class Hash {
    private int[] arrayAscii =  new int[100];

    public void HashAscii(int sum){
        int index = sum % getArrayAscii().length;
        getArrayAscii()[index] += 1;
    }

    public int[] getArrayAscii() {
        return arrayAscii;
    }

}
