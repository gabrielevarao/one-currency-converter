package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import connection.CurrencyExchangeApiClient;
import connection.ExchangeRate;

public class CurrencyConverter {
    CurrencyExchangeApiClient apiClient = new CurrencyExchangeApiClient();
    double mid;
    String baseCurrency;
    String targetCurrency;

    public void getExchangeRate(String baseCurrency, String targetCurrency) {
        Gson gson = new GsonBuilder().create();
        ExchangeRate exchangeRateResponse = gson.fromJson(apiClient
                .getConversionResponse(baseCurrency, targetCurrency), ExchangeRate.class);
        this.mid = (double) exchangeRateResponse.data().get("mid");
        this.baseCurrency = (String) exchangeRateResponse.data().get("base");
        this.targetCurrency = (String) exchangeRateResponse.data().get("target");
    }

    public void convertCurrency(String amountString) {
        double amount = Double.parseDouble(amountString);
        double result = amount * this.mid;
        System.out.printf("""
                ----------------------------------------------------------
                >> A quantia %.2f (%s) equivale a %.2f (%s).
                ----------------------------------------------------------""", amount, baseCurrency, result, targetCurrency);
    }
}
