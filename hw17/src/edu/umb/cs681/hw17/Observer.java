package edu.umb.cs681.hw17;

public interface Observer {
    public abstract void update(Observable sender, StockEvent event);
}
