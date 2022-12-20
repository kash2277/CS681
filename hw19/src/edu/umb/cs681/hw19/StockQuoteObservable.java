package edu.umb.cs681.hw19;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable extends Observable{
    private HashMap<String, Double> hm = new HashMap<>();
    private ReentrantLock lockTQ = new ReentrantLock();

    public void changeQuote(String t, double q) {
        lockTQ.lock();
        hm.put(t, q);
        setChanged();
        lockTQ.unlock();
        notifyObservers(new StockEvent(t, q));
    }
}
