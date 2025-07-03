package model;

import connection.CurrencyExchangeApiClient;
import connection.ListOfCurrenciesDto;
import exception.CurrencyConversionException;

import java.util.List;
import java.util.function.Predicate;

public class ConverterValidation {

    public static boolean isValidCurrencyCode(String userCurrency){
        Predicate<ListOfCurrenciesDto> currencyFilter = c -> c.code().equals(userCurrency);
        List<ListOfCurrenciesDto> currencyList = CurrencyExchangeApiClient.getCurrencyList();
        boolean exists = currencyList.stream().anyMatch(currencyFilter);

        if (exists){
            return true;
        } else {
            throw new CurrencyConversionException("""
                      -----------------------
                      !!! CÓDIGO INVÁLIDO !!!
                      -----------------------""");
        }
    }

    public static boolean isValidAmount (String amount){
        try {
            Double.parseDouble(amount);
            return true;
        } catch (NumberFormatException e){
            throw new CurrencyConversionException("""
                      ------------------------
                      !!! FORMATO INVÁLIDO !!!
                      ------------------------""");
        }
    }

    public static boolean exit(String input) {
        if (input == null) {
            System.out.println(MenuScreen.endMessage);
            return true;
        }
        return false;
    }
}
