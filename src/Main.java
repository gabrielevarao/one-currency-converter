import connection.CurrencyExchangeApiClient;
import model.CurrencyConverter;
import model.MenuScreen;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        CurrencyExchangeApiClient apiClient = new CurrencyExchangeApiClient();

        boolean endLoop = false;

        MenuScreen.start();

        while (!endLoop) {
            CurrencyConverter.convertCurrency(apiClient.getExchangeRate(), MenuScreen.getAmount());
            endLoop = MenuScreen.endLoop();
        }

    }
}