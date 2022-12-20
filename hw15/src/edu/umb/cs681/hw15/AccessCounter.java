package edu.umb.cs681.hw15;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
public class AccessCounter {
	
	private AccessCounter() {};	
	
	private HashMap <Path, Integer> AC = new HashMap<Path, Integer>();
	private static ReentrantLock slock = new ReentrantLock();
	private static ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
	private static AccessCounter instance = null;
	
	public static AccessCounter getInstance() {
		slock.lock();
		try {
			if (instance == null)
				instance = new AccessCounter();
			return instance;
		}
		finally {
			slock.unlock();
		}
	}
	
	public int getCount(Path path) {
		try {
			rwlock.readLock().lock();
			if(AC.containsKey(path)) {
				return AC.get(path);
			} else {
				return 0;
			}
		} finally {
			rwlock.readLock().unlock();
		}
	}	
	
	public void increment(Path path) {
		try {
			rwlock.writeLock().lock();
			if(AC.containsKey(path)) {
				AC.put(path, AC.get(path)+1);
			} else {
				AC.put(path, 1);
			}
		} finally {
			rwlock.writeLock().unlock();
		}
	}
	
}
