package com.example.artemka;

import java.util.ArrayList;
import java.util.List;

public class CurrencyViewModel {
    private List<Currency> currencies = new ArrayList<>();
    private List<ConversionHistory> history = new ArrayList<>();

    public CurrencyViewModel() {
        // Инициализация валют (курсы примерные, нужно обновлять из API)
        currencies.add(new Currency("Рубль", 1.0));
        currencies.add(new Currency("Белорусский рубль", 0.29));
        currencies.add(new Currency("Евро", 92.5));
        currencies.add(new Currency("Доллар", 85.3));
        currencies.add(new Currency("Юань", 11.8));
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<ConversionHistory> getHistory() {
        return history;
    }

    public void addToHistory(ConversionHistory item) {
        history.add(0, item);
    }

    public void clearHistory() {
        history.clear();
    }
}
