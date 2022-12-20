package edu.umb.cs681.hw19;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
    private boolean changed = false;
    private ConcurrentLinkedQueue<Observer> observers;
    private ReentrantLock lockObs = new ReentrantLock();

    Observable() {
        observers = new ConcurrentLinkedQueue<>();
    }

    public void addObserver(Observer o) {
		this.observers.offer(o);
    }

    public void removeObserver(Observer o) {
		this.observers.remove(o);
    }
    public int countObservers() {
		return observers.size();
    }
    
    public  void clearObservers(){
		observers.clear();
	 }
    
    public ConcurrentLinkedQueue<Observer> getObservers() {
		return observers;
    }

    protected void setChanged() {
    	lockObs.lock();
		try {
			changed = true;
		}finally {
			lockObs.unlock();
		}
    }

    protected void resetChanged() {
    	lockObs.lock();
		try {
	        changed = false;
	    }finally {
			lockObs.unlock();
		}
    }

    public boolean getChanged() {
        lockObs.lock();
		try {
			return changed;
	    }finally {
			lockObs.unlock();
		}
    }

    public void notifyObservers(StockEvent obj) {
		if (getChanged()) {
            observers.forEach(observer -> observer.update(this, obj));
            resetChanged();
        }
    }
    
    public static void main(String[] args) {
        StockQuoteObservable s = new StockQuoteObservable();
        s.addObserver((Observable o, StockEvent obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            double quote = ((StockEvent) obj).getQuote();
            System.out.println("First observer - ticker: " + ticker + " quote: " + quote);
        });

        s.addObserver((Observable o, StockEvent obj) -> {
            String ticker = ((StockEvent) obj).getTicker();
            double quote = ((StockEvent) obj).getQuote();
            System.out.println("Second observer - ticker: " + ticker + " quote: " + quote);
        });
		
		ArrayList<Thread> threads = new ArrayList<Thread>();
		for (int i = 0; i < 15; i++) {
			Thread t = new Thread(() -> {
				s.changeQuote("ABC", 100 + Thread.currentThread().getId());
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			threads.add(t);
			t.start();
		}
		for (int i = 0; i < 15; i++) 
		{
			 try {								
					 threads.get(i).join();
				 }
			catch (Exception e) {
				System.out.println(e);
			}
		}	
    }
}