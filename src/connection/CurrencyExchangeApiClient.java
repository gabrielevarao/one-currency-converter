package connection;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class CurrencyExchangeApiClient {

    private final String exRateApiUrl = "https://hexarate.paikama.co/api/rates/latest/";
    private static final String currencyListUrl = "https://gist.githubusercontent.com/gp187/4393cbc6dd761225071270c29b341b7b/raw/eb21c79192c4308152ba74924a4efc4bdfaa4377/currencies.json";
    static final HttpClient client = HttpClient.newHttpClient();
    static HttpRequest listRequest = HttpRequest.newBuilder()
            .uri(URI.create(currencyListUrl))
            .build();

    public String getConversionResponse(String baseCurrency, String targetCurrency){

        String urlRequest = this.exRateApiUrl + baseCurrency + "?target=" + targetCurrency;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlRequest))
                .build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response.body();
    }

    public static List<ListOfCurrenciesDto> getCurrencyList() {
        Gson gson = new GsonBuilder().create();

        HttpResponse<String> response = null;
        try {
            response = client.send(listRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Type listType = new TypeToken<List<ListOfCurrenciesDto>>() {}.getType();

        return gson.fromJson(response.body(), listType);
    }

    public static void currencyListToString(List<ListOfCurrenciesDto> currencies) {
        int currencyNameSize = 36;
        int codeSize = 4;

        String border = "+" + "-".repeat(currencyNameSize) + "+" + "-".repeat(codeSize) + "+";
        System.out.println(border);
        System.out.printf("|%-" + currencyNameSize + "s|%-" + codeSize + "s|%n",
                "               MOEDA               ", "COD");
        System.out.println(border);

        for (ListOfCurrenciesDto m : currencies) {
            System.out.printf("|%-" + currencyNameSize + "s|%-" + codeSize + "s|%n", m.name(), m.code());
            System.out.println(border);
        }
    }
}
