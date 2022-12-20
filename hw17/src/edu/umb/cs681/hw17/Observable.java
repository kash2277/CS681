package edu.umb.cs681.hw17;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Observable {
    private boolean changed = false;
    private LinkedList<Observer> observers;
    private ReentrantLock lockObs = new ReentrantLock();

    Observable() {
        observers = new LinkedList<>();
    }

    public void addObserver(Observer o) {
    	lockObs.lock();
		try {
			this.observers.add(o);
	    }finally {
			lockObs.unlock();
		}
    }

    public void removeObserver(Observer o) {
    	lockObs.lock();
		try {
			this.observers.remove(o);
		}finally {
			lockObs.unlock();
		}
    }
    public int countObservers() {
    	lockObs.lock();
		try {
			 return observers.size();
		}finally {
			lockObs.unlock();
		}
    }
    
    public  void clearObservers(){
		lockObs.lock();
		try {
			 observers.clear();
		}finally {
			lockObs.unlock();
		}
	 }
    
    public LinkedList<Observer> getObservers() {
    	lockObs.lock();
		try {
			return observers;
		}finally {
			lockObs.unlock();
		}
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
        lockObs.lock();
		try {
			if (getChanged()) {
	            observers.forEach(observer -> observer.update(this, obj));
	            resetChanged();
	        }
	    }finally {
			lockObs.unlock();
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