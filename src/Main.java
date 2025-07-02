import model.CurrencyConverter;
import model.MenuScreen;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        CurrencyConverter currencyConverter = new CurrencyConverter();
        boolean endLoop = false;

        MenuScreen.start();

        while (!endLoop) {
            currencyConverter.setExchangeRate();
            currencyConverter.convertCurrency();
            endLoop = MenuScreen.endLoop();
        }
    }
}