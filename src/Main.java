import connection.CurrencyExchangeApiClient;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        CurrencyExchangeApiClient apiClient =
                new CurrencyExchangeApiClient("USD", "BRL");
        System.out.println(apiClient.getApiResponse(apiClient.getUrlRequest(), apiClient.getRequest()));

    }
}