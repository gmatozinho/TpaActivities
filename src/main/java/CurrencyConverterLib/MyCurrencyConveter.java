package CurrencyConverterLib;

import HashLib.Core.MyHashListChain;

import java.io.IOException;

public class MyCurrencyConveter {
    private MyHashListChain<String, MyHashListChain<String,Double>> currencyConveter;

    public MyCurrencyConveter()
    {
        currencyConveter = new MyHashListChain<>();
    }

    public void addCoin(String coin)
    {
        currencyConveter.insertItem(coin, new MyHashListChain<>());
    }

    public void removeCoin(String coin)
    {
        currencyConveter.removeElement(coin);
    }

    public void addCoinToConverter(String coinToConverter, String coin, double value)
    {
        try {
            currencyConveter.findElements(coinToConverter).insertItem(coin,value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCoinToConverter(String coinToConverter, String coin, double value)
    {
        try {
            currencyConveter.findElements(coinToConverter).removeElement(coin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double getCoinValue(String coinToConvert, String coin) throws IOException {
        return currencyConveter.findElements(coinToConvert).findElements(coin);
    }

    public void printAll() throws IOException {
        for (String key: currencyConveter.keys()) {
            System.out.println("\n"+key);
            for ( String internalKey: currencyConveter.findElements(key).keys()) {
                MyHashListChain<String, Double> hash = currencyConveter.findElements(key);
                System.out.println("Coin:" + internalKey +" Value:" + hash.findElements(internalKey));
            }

        }
    }

}
