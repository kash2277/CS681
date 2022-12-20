package edu.umb.cs681.hw13;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;
public class AccessCounter {
	
	private AccessCounter() {};	
	
	private HashMap <Path, Integer> AC = new HashMap<Path, Integer>();
	private static ReentrantLock slock = new ReentrantLock();
	private static ReentrantLock lock = new ReentrantLock();
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
			lock.lock();
			if(AC.containsKey(path)) {
				return AC.get(path);
			} else {
				return 0;
			}
		} finally {
			lock.unlock();
		}
	}	
	
	public void increment(Path path) {
		try {
			lock.lock();
			if(AC.containsKey(path)) {
				AC.put(path, AC.get(path)+1);
			} else {
				AC.put(path, 1);
			}
		} finally {
			lock.unlock();
		}
	}
	
}
