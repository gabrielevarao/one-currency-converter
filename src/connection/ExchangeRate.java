package connection;

import java.util.Map;

public record ExchangeRate(String base, Map<String, Double> rates) {
}
