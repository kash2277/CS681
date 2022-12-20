package edu.umb.cs681.hw14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount2 {
    private double balance = 0;
    private final ReentrantLock lock = new ReentrantLock();

    Condition sufficientFundsCondition = lock.newCondition();
    Condition belowUpperLimitFundsCondition = lock.newCondition();

    public void deposit(double amount) {
        lock.lock();
        try {
            System.out.println("Current balance (d): " + balance);
            while (balance >= 300) {
                try {
                    System.out.println(Thread.currentThread().getName() + " Balance exceeded limit!");
                    belowUpperLimitFundsCondition.await();
                } catch (InterruptedException e) {
                    return;
                }
            }
            balance += amount;
            System.out.println(Thread.currentThread().getName()+ " New balance after depositing is: " + balance);
            sufficientFundsCondition.signalAll();
        } finally {
            lock.unlock();
            System.out.println("Lock released");
        }
    }

    public void withdraw(double amount) {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+" Current balance after withdrawal is: " + balance);

            while (balance <= 0) {
                try {
                    System.out.println(Thread.currentThread().getName() + " Current balance is low!");
                    sufficientFundsCondition.await();
                } catch (InterruptedException e) {
                    return;
                }
            }
            balance -= amount;
            System.out.println("New balance after withdrawal is: " + balance);
            belowUpperLimitFundsCondition.signalAll();
        } finally {
            lock.unlock();
            System.out.println("Lock released");
        }
    }
    
    public static void main(String[] args) throws Exception {

    	ThreadSafeBankAccount2 account = new ThreadSafeBankAccount2();
		WithdrawRunnable wr = new WithdrawRunnable(account);
		DepositRunnable dr= new DepositRunnable(account);
		
		Thread d1  = new Thread(dr);
		Thread d2  = new Thread(dr);
		Thread d3  = new Thread(dr);
		Thread d4  = new Thread(dr);
		Thread d5  = new Thread(dr);
		Thread d6  = new Thread(dr);
		Thread d7  = new Thread(dr);
		Thread d8  = new Thread(dr);
		Thread d9  = new Thread(dr);
		Thread d10  = new Thread(dr);
		Thread d11  = new Thread(dr);
		
		Thread w1  = new Thread(wr);
		Thread w2  = new Thread(wr);
		Thread w3  = new Thread(wr);
		Thread w4  = new Thread(wr);
		Thread w5  = new Thread(wr);
		Thread w6  = new Thread(wr);
		Thread w7  = new Thread(wr);
		Thread w8  = new Thread(wr);
		Thread w9  = new Thread(wr);
		Thread w10  = new Thread(wr);
		Thread w11  = new Thread(wr);
		
		d1.start();			
		w1.start();		
		d2.start();			
		w2.start();
		d3.start();			
		w3.start();		
		d4.start();			
		w4.start();		
		d5.start();			
		w5.start();		
		d6.start();			
		w6.start();		
		d7.start();			
		w7.start();
		d8.start();			
		w8.start();
		d9.start();			
		w9.start();
		d10.start();		
		w10.start();
		d11.start();		
		w11.start();	
		
		dr.setDone();
		wr.setDone();
		
		d1.interrupt();		
		w1.interrupt();
		d2.interrupt();		
		w2.interrupt();
		d3.interrupt();		
		w3.interrupt();
		d4.interrupt();		
		w4.interrupt();
		d5.interrupt();		
		w5.interrupt();
		d6.interrupt();		
		w6.interrupt();
		d7.interrupt();		
		w7.interrupt();
		d8.interrupt();		
		w8.interrupt();
		d9.interrupt();		
		w9.interrupt();
		d10.interrupt();	
		w10.interrupt();
		d11.interrupt();	
		w11.interrupt();
		
		try {
			d1.join();
			d2.join();
			d3.join();
			d4.join();
			d5.join();
			d6.join();
			d7.join();
			d8.join();
			d9.join();
			d10.join();
			d11.join();
			
			w1.join();
			w2.join();
			w3.join();
			w4.join();
			w5.join();
			w6.join();
			w7.join();
			w8.join();
			w9.join();
			w10.join();
			w11.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 		
	}
}
