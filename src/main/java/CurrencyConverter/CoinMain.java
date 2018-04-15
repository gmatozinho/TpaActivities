package CurrencyConverter;

import java.io.IOException;

public class CoinMain {
    public static void main(String[] args) throws IOException {
        MyCurrencyConveter coinConveter = new MyCurrencyConveter();
        coinConveter.addCoin("Real");
        coinConveter.addCoinToConverter("Real","Real",1);
        coinConveter.addCoinToConverter("Real","Dolar",0.292184);
        coinConveter.addCoinToConverter("Real","Euro",0.236633945);
        coinConveter.addCoin("Dolar");
        coinConveter.addCoinToConverter("Dolar","Real",3.42250089);
        coinConveter.addCoinToConverter("Dolar","Dolar",1);
        coinConveter.addCoinToConverter("Dolar","Euro",0.809879887);
        coinConveter.addCoin("Euro");
        coinConveter.addCoinToConverter("Euro","Real",4.2259364);
        coinConveter.addCoinToConverter("Euro","Dolar",1.234751);
        coinConveter.addCoinToConverter("Euro","Euro",1);

        //coinConveter.printAll();

        double realToDolar = coinConveter.getCoinValue("Real","Dolar") * 850;
        System.out.println("850 reais em dolar = " + realToDolar);

    }
}
