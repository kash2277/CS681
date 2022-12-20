package edu.umb.cs681.primes;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellableInterruptiblePrimeFactorizer extends RunnableCancellablePrimeFactorizer {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();
    
    public RunnableCancellableInterruptiblePrimeFactorizer(long dividend) {
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
        // TODO Auto-generated method stub
        RunnableCancellableInterruptiblePrimeFactorizer rcpf = new RunnableCancellableInterruptiblePrimeFactorizer(1000000000000000000L);
        Thread t = new Thread(rcpf);
        t.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        t.interrupt();
        try {
            t.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Prime factors of 1000000000000000000L are: " + rcpf.getPrimeFactors());
    }

}