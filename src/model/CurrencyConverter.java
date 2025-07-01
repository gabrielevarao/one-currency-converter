package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.CurrencyExchangeApiClient;
import connection.ExchangeRate;

import java.io.IOException;

public class CurrencyConverter {
    CurrencyExchangeApiClient apiClient = new CurrencyExchangeApiClient();
    double mid;

    public void setExchangeRate() throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().create();
        ExchangeRate exchangeRateResponse = gson.fromJson(apiClient.getApiResponse(), ExchangeRate.class);
        this.mid = (double) exchangeRateResponse.data().get("mid");
    }

    public void convertCurrency () {
        double amount = MenuScreen.getAmount();
        double result = amount * this.mid;
        System.out.printf("O valor convertido é: %.2f%n", result);
    }

}
