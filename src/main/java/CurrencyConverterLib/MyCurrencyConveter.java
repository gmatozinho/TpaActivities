package CurrencyConverterLib;

import HashLib.Core.MyHashListChain;

public class MyCurrencyConveter {
    private MyHashListChain<String, MyHashListChain<String,Double>> currencyConverter;

    public MyCurrencyConveter()
    {
        currencyConverter = new MyHashListChain<>();
    }

    public void addCoin(String coin)
    {
        currencyConverter.insertItem(coin, new MyHashListChain<>());
    }

    public void removeCoin(String coin)
    {
        currencyConverter.removeElement(coin);
    }

    public void addCoinToConverter(String coinToConvert, String coin, double value)
    {
        currencyConverter.findElements(coinToConvert).insertItem(coin,value);
    }

    public void removeCoinToConverter(String coinToConvert, String coin, double value)
    {
        currencyConverter.findElements(coinToConvert).removeElement(coin);
    }

    public double getCoinValue(String coinToConvert, String coin) {
        return currencyConverter.findElements(coinToConvert).findElements(coin);
    }

    public void printAll() {
        for (String key: currencyConverter.keys()) {
            System.out.println("\n"+key);
            for ( String internalKey: currencyConverter.findElements(key).keys()) {
                MyHashListChain<String, Double> hash = currencyConverter.findElements(key);
                System.out.println("Coin:" + internalKey +" Value:" + hash.findElements(internalKey));
            }

        }
    }

}
