package model;

import connection.CurrencyExchangeApiClient;

import java.util.Scanner;

public class MenuScreen {
    static Scanner scanner = new Scanner (System.in);

    public static void start(){
        System.out.println("""
                
                **********************************************************
                $                   CURRENCY CONVERTER                   $
                **********************************************************""");
    }

    public static String getBaseCurrency(){
        System.out.println("""
                ----------------------------------------------------------
                                     Nova consulta
                ----------------------------------------------------------
                O valor está em que moeda? Digite o código correspondente. Por ex.: BRL (real brasileiro).
                [Digite 0 para ver a lista as moedas disponíveis.] """);

               String baseCurrency = scanner.nextLine();

               if (baseCurrency.equals("0")){
                   CurrencyExchangeApiClient apiClient = new CurrencyExchangeApiClient();
                   apiClient.getCurrenciesList();
                   System.out.println("""
                           Agora, digite o código da moeda a ser convertida: """);
                   baseCurrency = scanner.nextLine();
               }
        return baseCurrency;
    }

    public static String getTargetCurrency(){
        System.out.println("""
                
                O valor deve ser convertido para que moeda? Digite o código correspondente. Por ex.: BRL (real brasileiro).
                [Digite 0 para ver a lista as moedas disponíveis.]""");
        return scanner.nextLine();
    }

    public static double getAmount(){
        System.out.println("""
                
                Qual a quantia a ser convertida? Escreva apenas os números.""");
        return scanner.nextDouble();
    }

    public static boolean endLoop (){

        System.out.println("""
                
                Quer fazer outra consulta? Digite S para 'sim' ou N para 'não'.""");

        scanner.nextLine();

        String endLoop = scanner.nextLine();
        if (endLoop.equalsIgnoreCase("S")){
            return false;
        } else {
            System.out.println("Obrigada por utilizar o Currency Converter!");
            return true;
        }
    }

}
