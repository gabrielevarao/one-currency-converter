package connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class CurrencyExchangeApiClient {

    private String baseCurrency;
    private String targetCurrency;

    private String apiUrl = "https://api.vatcomply.com/";
    private String urlRequest;
    private HttpRequest request;
    private HttpClient client = HttpClient.newHttpClient();
    private String apiResponse;

    public CurrencyExchangeApiClient(String base, String target){
        this.baseCurrency = base;
        this.targetCurrency = target;

        this.urlRequest = this.apiUrl + "rates?base=" + baseCurrency + "&symbols=" + targetCurrency;

        this.request = HttpRequest.newBuilder()
                .uri(URI.create(this.urlRequest))
                .build();
    }

    public void setApiResponse(String urlRequest) throws IOException, InterruptedException{

        HttpResponse<String> response = this.client
                .send(this.request, HttpResponse.BodyHandlers.ofString());

        this.apiResponse = response.body();
    }

    public String getApiResponse(String urlRequest, HttpRequest request) throws IOException, InterruptedException{

        HttpResponse<String> response = this.client
                .send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

//    public String getCurrencyList() throws IOException, InterruptedException {
//        String urlCurrencyList = this.apiUrl + "currencies";
//        getApiResponse(urlCurrencyList, this.request);
//        return this.apiResponse;
//    }

    public double getExchangeRate (){
        Gson gson = new GsonBuilder().create();
        ExchangeRate exchangeRateResponse = gson.fromJson(this.apiResponse, ExchangeRate.class);

        return exchangeRateResponse.rates().get(this.targetCurrency);
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public String getUrlRequest() {
        return urlRequest;
    }

    public HttpRequest getRequest() {
        return request;
    }
}
