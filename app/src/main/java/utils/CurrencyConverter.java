package utils;

import com.example.artemka.Currency;
public class CurrencyConverter {
    public static double convert(double amount, Currency from, Currency to) {
        // Конвертируем через рубль как базовую валюту
        double amountInRub = amount * from.getRate();
        return amountInRub / to.getRate();

    }
}
