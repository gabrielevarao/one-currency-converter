package connection;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.MenuScreen;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CurrencyExchangeApiClient {

    private String exRateApiUrl = "https://hexarate.paikama.co/api/rates/latest/";
    private String currencyListUrl = "https://gist.githubusercontent.com/gp187/4393cbc6dd761225071270c29b341b7b/raw/eb21c79192c4308152ba74924a4efc4bdfaa4377/currencies.json";
    HttpClient client = HttpClient.newHttpClient();

    public String getApiResponse() throws IOException, InterruptedException{

        String urlRequest = this.exRateApiUrl + MenuScreen.getBaseCurrency() + "?target=" + MenuScreen.getTargetCurrency();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlRequest))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String getCurrenciesList (){
        Gson gson = new GsonBuilder().create();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(currencyListUrl))
                .build();
        String responseBody = "";

        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

//            Type listType = new TypeToken<List<ListOfCurrenciesDto>>() {}.getType();
//            List<ListOfCurrenciesDto> currencies = gson.fromJson(response.body(), listType);
//
//            for (ListOfCurrenciesDto m : currencies) {
//                System.out.println( "Nome: " + m.name() + ", Código: " + m.code());
//            }
            responseBody = response.body();

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return responseBody;
    }

}
