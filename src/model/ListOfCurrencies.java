package model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import connection.ListOfCurrenciesDto;

import java.lang.reflect.Type;
import java.util.List;

public class ListOfCurrencies {
    Gson gson = new GsonBuilder().create();

    public void printListOfCurrencies (String response){

        Type listType = new TypeToken<List<ListOfCurrenciesDto>>() {}.getType();
        List<ListOfCurrenciesDto> currencies = gson.fromJson(response, listType);

        for (ListOfCurrenciesDto m : currencies) {
            System.out.println( "Nome: " + m.name() + ", Código: " + m.code());
        }

    }
//    ListOfCurrencies (ListOfCurrenciesDto list){
//        this.name = list.name();
//        this.code = list.code();
//    }
//    Type listType = new TypeToken<List<ListOfCurrenciesDto>>() {}.getType();
//    List<ListOfCurrenciesDto> currencies = gson.fromJson(response.body(), listType);
//
//            for (ListOfCurrenciesDto m : currencies) {
//        System.out.println( "Nome: " + m.name() + ", Código: " + m.code());
//    }

}
