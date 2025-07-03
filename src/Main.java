import model.ConverterValidation;
import model.CurrencyConverter;
import model.MenuScreen;

public class Main {
    public static void main(String[] args){

        CurrencyConverter currencyConverter = new CurrencyConverter();
        boolean endLoop = false;

        System.out.println(MenuScreen.banner);

        while (!endLoop) {
            System.out.println(MenuScreen.newConsultMessage);
            String baseCurrency = MenuScreen.getBaseCurrency();
                if (ConverterValidation.exit(baseCurrency)){break;}
            String targetCurrency = MenuScreen.getTargetCurrency();
                if (ConverterValidation.exit(targetCurrency)){break;}
            String amount = MenuScreen.getAmount();
                if (ConverterValidation.exit(amount)){break;}

            System.out.println("\n... convertendo ...");

            currencyConverter.getExchangeRate(baseCurrency, targetCurrency);
            currencyConverter.convertCurrency(amount);
            endLoop = MenuScreen.newConversion();
        }
    }
}