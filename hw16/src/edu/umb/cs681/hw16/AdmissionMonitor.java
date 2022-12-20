package edu.umb.cs681.hw16;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class AdmissionControl {
	private int currentVisitors;
	private boolean done = false;
	private ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
	private ReentrantLock lock = new ReentrantLock();
	private Condition sufficientVisitorsCondition;
	private AdmissionControl control;
	 public AdmissionControl()
	 {
		 sufficientVisitorsCondition = rwlock.writeLock().newCondition();
		 this.currentVisitors = 0;
		 control = this;
	 }
	 public void setDone() {
		 try {
			 lock.lock();
			 this.done = true;
		 } finally {
			 lock.unlock();
		 }		
	}
	 public void resetDone() {
		 try {
			 lock.lock();
			 this.done = false;
		 } finally {
			 lock.unlock();
		 }		
	}
	public void enter() {	
		rwlock.writeLock().lock();
		try{
			System.out.println("rwlock obtained");
			System.out.println(Thread.currentThread().getId() + " ENTER current Visitors: " + currentVisitors);
			while(currentVisitors >= 5){
				System.out.println(Thread.currentThread().getId() + " ENTER await(): visitors exceed the limit of 5.");
				sufficientVisitorsCondition.await();
			}
			currentVisitors ++;
			System.out.println(Thread.currentThread().getId() + " ENTER new visitors: " + currentVisitors);
			
		}
		catch (InterruptedException exception){
			exception.printStackTrace();
		}
		finally{
			rwlock.writeLock().unlock();
			System.out.println("rwlock released");
		}		
		
	}
	public void exit() {
		rwlock.writeLock().lock();
		try{
			System.out.println("rwlock obtained");
			System.out.println(Thread.currentThread().getId() + " EXIT current visitors: " + currentVisitors);			
			currentVisitors --;
			System.out.println(Thread.currentThread().getId() + " EXIT new visitors: " + currentVisitors);
			sufficientVisitorsCondition.signalAll();
		}		
		finally{
			rwlock.writeLock().unlock();
			System.out.println("rwlock released");
		}
	}

	public int countCurrentVisitors() {
		rwlock.readLock().lock();
		try{			
			return currentVisitors;	
		}		
		finally{
			rwlock.readLock().unlock();
			System.out.println("rwlock released");
		}
	}
	
	class EntranceHandler implements Runnable {
		
		public void run()
		{
			while(!done)
			{  
				control.enter();				
			}
			System.out.println(Thread.currentThread().getId() + " done = true");	
		}
	}
	class ExitHandler implements Runnable {
		public void run()
		{
			while(!done)
			{
				control.exit();					
			}
			System.out.println(Thread.currentThread().getId() + " done = true");
			
		}
	}
	class StatsHandler implements Runnable {
		public void run()
		{			
				control.countCurrentVisitors();	
		}
	}

}

