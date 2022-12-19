package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton implements Runnable{
	private ConcurrentSingleton(){};

	private static Singleton instance = null;
	private static ReentrantLock lock = new ReentrantLock();

	public static Singleton getInstance() {
		lock.lock();
		try {
			if (instance == null) {
				instance = new Singleton();
			}
			return instance;
		} finally {
			lock.unlock();
		}
	}
	
	public void run() {
		System.out.println(Singleton.getInstance());
	}

	public static void main(String[] args) {
		Thread t1 = new Thread(new ConcurrentSingleton());
		Thread t2 = new Thread(new ConcurrentSingleton());
		Thread t3 = new Thread(new ConcurrentSingleton());
		
		t1.start();		
		t2.start();
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
