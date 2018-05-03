package NumLib;

public class Prime {

    private static boolean isPrime(int n)
    {
        int qtdDiv = 0, div =1;
        int limit = (int)Math.sqrt(n);

        while(div < limit)
        {
            if((n%div)==0)
            {
                qtdDiv++;
            }

            div ++;
        }

        return qtdDiv==1;
    }

    public static int calcArraySize(int n)
    {
        if(isPrime(n))
        {
            return n;
        }
        return calcArraySize(n+1);
    }
}
