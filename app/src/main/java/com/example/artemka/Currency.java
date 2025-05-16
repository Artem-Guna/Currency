package com.example.artemka;

public class Currency {
    private String name;
    private double rate; // курс относительно базовой валюты (рубля)

    public Currency(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }
}
