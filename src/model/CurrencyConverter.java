package model;

import connection.CurrencyExchangeApiClient;

public class CurrencyConverter {
    CurrencyExchangeApiClient currencyExchangeApiClient;

    public static void convertCurrency (double amount, double mid) {

        double result = amount*mid;
        System.out.printf("O valor convertido é: %.2f%n", result);
    }

}
