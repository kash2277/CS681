package edu.umb.cs681.hw09;

import java.util.concurrent.locks.ReentrantLock;

public class Singleton {
	static ReentrantLock lock = new ReentrantLock();
	private static Singleton instance = null;
	
	public Singleton() {};

	public static Singleton getInstance() {
		lock.lock();
		try {
			System.out.println("locked");
			if (instance == null) {	
				new Singleton();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println("Instance interrupted");
					e.printStackTrace();
				}
				instance = new Singleton();
				System.out.println("Instance: "+instance);
			}
			return instance;
		}finally {
			System.out.println("unlocked!");
			lock.unlock();
		}
	}
}