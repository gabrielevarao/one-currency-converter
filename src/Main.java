import model.CurrencyConverter;
import model.MenuScreen;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        CurrencyConverter currencyConverter = new CurrencyConverter();
        boolean endLoop = false;

        System.out.println(MenuScreen.banner);

        while (!endLoop) {
            System.out.println(MenuScreen.newConsultMessage);
                currencyConverter.getExchangeRate();
                currencyConverter.convertCurrency();
                endLoop = MenuScreen.anotherConversion();
        }
    }
}