package edu.umb.cs681.hw19;

public class StockEvent {
    private String ticker;
    private double quote;

    StockEvent(String t, double q) {
        ticker = t;
        quote = q;
    }

    public String getTicker() {
        return ticker;
    }

    public double getQuote() {
        return quote;
    }
}
