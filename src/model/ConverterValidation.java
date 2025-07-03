package model;

import connection.CurrencyExchangeApiClient;
import connection.ListOfCurrenciesDto;
import exception.CurrencyConversionException;

import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;

public class ConverterValidation {

    public static boolean isValidCurrencyCode(String userCurrency) throws IOException, InterruptedException {
        Predicate<ListOfCurrenciesDto> currencyFilter = c -> c.code().equals(userCurrency);
        CurrencyExchangeApiClient apiClient = new CurrencyExchangeApiClient();
        List<ListOfCurrenciesDto> currencyList = apiClient.getCurrencyList();
        boolean exists = currencyList.stream().anyMatch(currencyFilter);

        if (exists){
            return true;
        } else {
            throw new CurrencyConversionException("Código de moeda inválido. Digite novamente.");
        }
    }

    public static boolean isValidAmount (String amount){
        try {
            Double.parseDouble(amount);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }

}
