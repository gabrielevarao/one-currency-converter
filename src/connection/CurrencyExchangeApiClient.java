package connection;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import model.CurrenciesList;
import model.MenuScreen;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;



public class CurrencyExchangeApiClient {

    private String apiUrl = "https://hexarate.paikama.co/api/rates/latest/";
    private HttpClient client = HttpClient.newHttpClient();

    public String getApiResponse() throws IOException, InterruptedException{

        String urlRequest = this.apiUrl + MenuScreen.getBaseCurrency() + "?target=" + MenuScreen.getTargetCurrency();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlRequest))
                .build();
        HttpResponse<String> response = this.client
                .send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public double getExchangeRate() throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().create();
        ExchangeRate exchangeRateResponse = gson.fromJson(getApiResponse(), ExchangeRate.class);

        return (double) exchangeRateResponse.data().get("mid");
    }

    public void getCurrenciesList () throws IOException, InterruptedException {
        Gson gson = new GsonBuilder().create();
        String urlRequest = "https://gist.githubusercontent.com/gp187/4393cbc6dd761225071270c29b341b7b/raw/eb21c79192c4308152ba74924a4efc4bdfaa4377/currencies.json";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlRequest))
                .build();
        HttpResponse<String> response = this.client
                .send(request, HttpResponse.BodyHandlers.ofString());

        Type listType = new TypeToken<List<CurrenciesList>>() {}.getType();
        List<CurrenciesList> currencies = gson.fromJson(response.body(), listType);

        for (CurrenciesList m : currencies) {
            System.out.println( "Nome: " + m.name() + ", Código: " + m.code());
        }

    }

}
