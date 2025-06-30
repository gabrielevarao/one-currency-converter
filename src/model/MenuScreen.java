package model;

import java.util.Scanner;

public class MenuScreen {
    Scanner scanner = new Scanner (System.in);

    public void init(){
        System.out.println("""
                
                *****************************
                $     CURRENCY CONVERTER    $
                *****************************
                """);
    }

    public String getBaseCurrency(){
        System.out.println("""
                -----------------------------
                       Nova consulta         
                -----------------------------
                O valor está em que moeda? Digite o código correspondente. 
                Por ex.: USD (dólar americano); EUR (euro); BRL (real brasileiro).""");
        return scanner.nextLine();
    }

    public String getTargetCurrency(){
        System.out.println("""
                
                O valor deve ser convertido para que moeda? Digite o código correspondente.
                Por ex.: USD (dólar americano); EUR (euro); BRL (real brasileiro).""");
        return scanner.nextLine();
    }

    public Double getAmount(){
        System.out.println("""
                
                Qual a quantia a ser convertida?""");
        return scanner.nextDouble();
    }

}
