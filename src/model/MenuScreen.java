package model;
import connection.CurrencyExchangeApiClient;
import exception.CurrencyConversionException;

import java.util.Scanner;

import static connection.CurrencyExchangeApiClient.currencyListToString;

public class MenuScreen {
    public static Scanner scanner = new Scanner (System.in);
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
                | Digite o código correpondente (ex.: BRL, USD).
                
                ------ Quer ver a lista das moedas disponíveis? Digite [0].
                ------ Quer encerrar a aplicação? Digite [sair].
                
                Código:""";
    public static String targetCurrencyMessage = """
                
                >> O VALOR DEVE SER CONVERTIDO PARA QUE MOEDA?
                | Digite o código correpondente (ex.: BRL, USD).
                
                ------ Quer ver a lista das moedas disponíveis? Digite [0].
                ------ Quer encerrar a aplicação? Digite [sair].
                
                Código:""";
    public static String amountMessage = """
                
                >> QUAL A QUANTIA A SER CONVERTIDA?
                Escreva apenas os números separados por ponto (ex.: 100.10).
                
                ------ Quer encerrar a aplicação? Digite [sair].
                
                Quantia:""";
    public static String endMessage = """
                
                **********************************************************
                $       OBRIGADA POR UTILIZAR O CURRENCY CONVERTER       $
                **********************************************************""";

    public static String getBaseCurrency() {
        boolean validationGetLoop = false;
        String baseCurrency = "";

        while (!validationGetLoop) {
            System.out.println(baseCurrencyMessage);
            baseCurrency = scanner.nextLine().toUpperCase();

            if (baseCurrency.equals("SAIR")) {return null;}

            if (baseCurrency.equals("0")) {
                currencyListToString(CurrencyExchangeApiClient.getCurrencyList());
                System.out.println("""
                        
                        Agora, digite o código da moeda a ser convertida:""");
                baseCurrency = scanner.nextLine().toUpperCase();
                if (baseCurrency.equals("SAIR")) {return null;}
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

    public static String getTargetCurrency() {
        boolean validationGetLoop = false;
        String targetCurrency = "";

        while (!validationGetLoop) {
            System.out.println(targetCurrencyMessage);
            targetCurrency = scanner.nextLine().toUpperCase();

            if (targetCurrency.equals("SAIR")) {return null;}

            if (targetCurrency.equals("0")) {
                currencyListToString(CurrencyExchangeApiClient.getCurrencyList());
                System.out.println("""
                        
                        Agora, digite o código da moeda a ser convertida:""");
                targetCurrency = scanner.nextLine().toUpperCase();

                if (targetCurrency.equals("SAIR")) {return null;}
            }

            try {
                if (ConverterValidation.isValidCurrencyCode(targetCurrency)) {
                    validationGetLoop = true;
                }
            } catch (CurrencyConversionException e) {
                System.out.println(e.getMessage());
            }
        }
        return targetCurrency;
    }

    public static String getAmount(){
        boolean validationGetLoop = false;
        String amount ="";

        while (!validationGetLoop){
            System.out.println(amountMessage);
            amount = scanner.nextLine().toUpperCase().replace(",", ".");

            if (amount.equals("SAIR")) {return null;}

            try {
                if (ConverterValidation.isValidAmount(amount)) {
                    validationGetLoop = true;
                }
            } catch (CurrencyConversionException e) {
                System.out.println(e.getMessage());
            }
        }
        return amount;
    }

    public static boolean newConversion(){
        System.out.println("""
                
                
                Quer fazer outra consulta? Digite S para 'sim' ou qualquer letra para encerrar.""");

        String endLoop = scanner.nextLine().toUpperCase();

        if (endLoop.equalsIgnoreCase("S")){
            return false;}
        else {
            System.out.println(endMessage);return true;}
    }
}