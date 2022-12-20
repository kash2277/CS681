package edu.umb.cs681.primes;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    
    public RunnableCancellablePrimeFactorizer(long dividend) {
        super(dividend);
    }
    
    public void setDone(){
        lock.lock();
        try{
            done = true;
        }finally{
            lock.unlock();
        }
    }
    
    public void generatePrimeFactors(){
        long divisor = 2;
        while( dividend != 1 && divisor <= to ){
            if(dividend % divisor == 0) {
                factors.add(divisor);
                dividend /= divisor;
            }else {
            	if(divisor==2){ divisor++; }
            	else{ divisor += 2; }
            }
            lock.lock();
            try{
            	if(done){
            		System.out.println("Stopped generating prime factors.");
            		this.factors.clear();
            		break;
            	}
            }finally{
            	lock.unlock();
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.print("Prime factors of 6: ");
        RunnableCancellablePrimeFactorizer fac = new RunnableCancellablePrimeFactorizer(6);
        Thread thread = new Thread(fac);
        thread.start();
        fac.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(fac.getPrimeFactors());
        
        System.out.print("Prime factors of 17: ");
        fac = new RunnableCancellablePrimeFactorizer(17);
        thread = new Thread(fac);
        thread.start();
        fac.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(fac.getPrimeFactors());

        System.out.print("Prime factors of 131: ");
        fac = new RunnableCancellablePrimeFactorizer(131);
        thread = new Thread(fac);
        thread.start();
        fac.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(fac.getPrimeFactors());
        
        System.out.print("Prime factors of 84: ");
        fac = new RunnableCancellablePrimeFactorizer(84);
        thread = new Thread(fac);
        thread.start();
        fac.setDone();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(fac.getPrimeFactors());
    }
}