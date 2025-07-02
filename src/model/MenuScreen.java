package model;

import connection.CurrencyExchangeApiClient;
import connection.ListOfCurrenciesDto;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Predicate;

public class MenuScreen {
    static Scanner scanner = new Scanner (System.in);
    static CurrencyExchangeApiClient apiClient = new CurrencyExchangeApiClient();
    static String banner = """
                
                **********************************************************
                $                   CURRENCY CONVERTER                   $
                **********************************************************""";
    static String baseCurrencyMessage = """
                ----------------------------------------------------------
                                     Nova consulta
                ----------------------------------------------------------
                >> O VALOR ESTÁ EM QUE MOEDA? 
                Digite o código da moeda (ex.: BRL, USD) ou digite 0 para ver a lista das moedas disponíveis. """;
    static String targetCurrencyMessage = """
                
                >> O VALOR DEVE SER CONVERTIDO PARA QUE MOEDA?
                Digite o código da moeda (ex.: BRL, USD) ou digite 0 para ver a lista das moedas disponíveis.""";
    static String amountMessage = """
                
                >> QUAL A QUANTIA A SER CONVERTIDA?
                Escreva apenas os números.""";
    static String endMessage = """
                
                **********************************************************
                $       OBRIGADA POR UTILIZAR O CURRENCY CONVERTER       $
                **********************************************************""";

    public static void start(){
        System.out.println(banner);
    }

    public static String getBaseCurrency() throws IOException, InterruptedException {
        System.out.println(baseCurrencyMessage);
        String baseCurrency = scanner.nextLine();

        boolean validade = MenuScreen.validateCurrencyCode(baseCurrency);
        if(validade){
            System.out.println("Moeda válida.");
        } else {
            System.out.println("Moeda inválida.");
        }

        if (baseCurrency.equals("0")) {
            apiClient.getCurrenciesList();
            System.out.println("""
                    
                    Agora, digite o código da moeda a ser convertida: """);
            baseCurrency = scanner.nextLine();
        }
        return baseCurrency;
    }

    public static String getTargetCurrency(){
        System.out.println(targetCurrencyMessage);
        String targetCurrency = scanner.nextLine();

//        if (targetCurrency.equals("0")){
//            apiClient.getCurrenciesList();
//            System.out.println("""
//
//                           Agora, digite o código da moeda a ser convertida: """);
//            targetCurrency = scanner.nextLine();
//        }
        return targetCurrency;
    }

    public static double getAmount(){
        System.out.println(amountMessage);
        return scanner.nextDouble();
    }

    public static boolean endLoop (){
        System.out.println("""
                
                
                Quer fazer outra consulta? Digite S para 'sim' ou N para 'não'.""");

        scanner.nextLine();
        String endLoop = scanner.nextLine();

        if (endLoop.equalsIgnoreCase("S")){return false;}
        else {
            System.out.println(endMessage);
            return true;
        }
    }
    public static boolean validateCurrencyCode(String userCurrency) throws IOException, InterruptedException {
        Predicate<ListOfCurrenciesDto> currencyFilter = c -> c.code().equals(userCurrency);
        CurrencyExchangeApiClient apiClient = new CurrencyExchangeApiClient();
        List<ListOfCurrenciesDto> currencyList = apiClient.getCurrenciesList();
        boolean exists = currencyList.stream().anyMatch(currencyFilter);

        if (exists){
            return true;
        } else {
            return false;
        }
    }
}
