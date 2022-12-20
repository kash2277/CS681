package edu.umb.cs681.hw18;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
public class AccessCounter {
	
	private AccessCounter() {};
	private ConcurrentHashMap<Path, Integer> AC = new ConcurrentHashMap<Path, Integer>();
	private static ReentrantLock slock = new ReentrantLock();
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
		if(AC.containsKey(path)) {
			return AC.get(path);
		} else {
			return 0;
		}
	}	
	
	public void increment(Path path) {
		if(AC.containsKey(path)) {
			AC.put(path, AC.get(path)+1);
		} else {
			AC.put(path, 1);
		}
	}
	
}
