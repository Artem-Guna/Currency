package com.example.artemka;

import java.util.Date;

public class ConversionHistory {
    private final String fromCurrency;
    private final String toCurrency;
    private final double amount;
    private final double result;
    private final Date date;

    public ConversionHistory(String fromCurrency, String toCurrency,
                             double amount, double result, Date date) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.result = result;
        this.date = date;
    }

    // Геттеры
    public String getFromCurrency() { return fromCurrency; }
    public String getToCurrency() { return toCurrency; }
    public double getAmount() { return amount; }
    public double getResult() { return result; }
    public Date getDate() { return date; }
}