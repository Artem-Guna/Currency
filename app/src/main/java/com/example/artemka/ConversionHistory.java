package com.example.artemka;

import java.util.Date;

public class ConversionHistory {
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double result;
    private Date date;

    public ConversionHistory(String fromCurrency, String toCurrency, double amount, double result, Date date) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.result = result;
        this.date = date;
    }

    // Get
    public String getFromCurrency() { return fromCurrency; }
    public String getToCurrency() { return toCurrency; }
    public double getAmount() { return amount; }
    public double getResult() { return result; }
    public Date getDate() { return date; }
}
