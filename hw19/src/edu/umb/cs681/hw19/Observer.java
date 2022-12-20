package edu.umb.cs681.hw19;

public interface Observer {
    public abstract void update(Observable sender, StockEvent event);
}
