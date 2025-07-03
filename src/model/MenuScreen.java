package model;
import connection.CurrencyExchangeApiClient;
import exception.CurrencyConversionException;

import java.io.IOException;
import java.util.Scanner;

import static connection.CurrencyExchangeApiClient.currencyListToString;

public class MenuScreen {
    public static Scanner scanner = new Scanner (System.in);
    public static CurrencyExchangeApiClient apiClient = new CurrencyExchangeApiClient();
    public static String banner = """
                
                **********************************************************
                $                   CURRENCY CONVERTER                   $
                **********************************************************""";
    public static String newConsultMessage = """
                ----------------------------------------------------------
                                     Nova consulta
                ----------------------------------------------------------""";
    public static String baseCurrencyMessage = """

                >> O VALOR ESTÁ EM QUE MOEDA? 
                Digite o código da moeda (ex.: BRL, USD) ou digite 0 para ver a lista das moedas disponíveis. """;
    public static String targetCurrencyMessage = """
                
                >> O VALOR DEVE SER CONVERTIDO PARA QUE MOEDA?
                Digite o código da moeda (ex.: BRL, USD) ou digite 0 para ver a lista das moedas disponíveis.""";
    public static String amountMessage = """
                
                >> QUAL A QUANTIA A SER CONVERTIDA?
                Escreva apenas os números.""";
    public static String endMessage = """
                
                **********************************************************
                $       OBRIGADA POR UTILIZAR O CURRENCY CONVERTER       $
                **********************************************************""";

    public static String getBaseCurrency() throws IOException, InterruptedException {
        boolean validationGetLoop = false;
        String baseCurrency = "";
        while (!validationGetLoop) {
            System.out.println(baseCurrencyMessage);
            baseCurrency = scanner.nextLine().toUpperCase();

            if (baseCurrency.equals("0")) {
                currencyListToString(apiClient.getCurrencyList());
                System.out.println("""
                        
                        Agora, digite o código da moeda a ser convertida: """);
                baseCurrency = scanner.nextLine().toUpperCase();
            }

            try {
                if (ConverterValidation.isValidCurrencyCode(baseCurrency)) {
                    validationGetLoop = true;
                }
            } catch (CurrencyConversionException e) {
                System.out.println(e.getMessage());
            }
        }
        return baseCurrency;
    }

    public static String getTargetCurrency() throws IOException, InterruptedException {
        System.out.println(targetCurrencyMessage);
        String targetCurrency = scanner.nextLine().toUpperCase();

        if (targetCurrency.equals("0")) {
            currencyListToString(apiClient.getCurrencyList());
            System.out.println("""
                    
                    Agora, digite o código da moeda a ser convertida: """);
            targetCurrency = scanner.nextLine().toUpperCase();
        }

        return targetCurrency;
    }

    public String getAmount(){
        System.out.println(amountMessage);
        return scanner.nextLine();
    }

    public static boolean anotherConversion(){
        System.out.println("""
                
                
                Quer fazer outra consulta? Digite S para 'sim' ou N para 'não'.""");

        String endLoop = scanner.nextLine().toUpperCase();

        if (endLoop.equalsIgnoreCase("S")){return false;}
        else {
            System.out.println(endMessage);
            return true;
        }
    }
}
