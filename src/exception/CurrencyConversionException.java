package exception;

public class CurrencyConversionException extends RuntimeException {
    private final String message;
    public CurrencyConversionException(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
