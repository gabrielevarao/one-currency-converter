package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.CurrencyExchangeApiClient;
import connection.ExchangeRate;

import java.io.IOException;

public class CurrencyConverter {
    CurrencyExchangeApiClient apiClient = new CurrencyExchangeApiClient();
    MenuScreen menu = new MenuScreen();
    double mid;
    String baseCurrency;
    String targetCurrency;

    public void getExchangeRate() throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().create();
        ExchangeRate exchangeRateResponse = gson.fromJson(apiClient.getApiResponse(), ExchangeRate.class);
        this.mid = (double) exchangeRateResponse.data().get("mid");
        this.baseCurrency = (String) exchangeRateResponse.data().get("base");
        this.targetCurrency = (String) exchangeRateResponse.data().get("target");
    }

    public void convertCurrency() {
        double amount = Double.parseDouble(menu.getAmount());
        double result = amount * this.mid;
        System.out.printf("""
                              
                >> A quantia %.2f (%s) equivale a %.2f (%s).""", amount, baseCurrency, result, targetCurrency);
    }

}
