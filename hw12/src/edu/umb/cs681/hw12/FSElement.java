package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.concurrent.locks.ReentrantLock;

public abstract class FSElement {
    protected Directory parent;
    private String name;
    private int size;
    private LocalDateTime creationTime;
    
    private static ReentrantLock lock = new ReentrantLock();

    protected FSElement(Directory parent, String name, int size, LocalDateTime creationTime) {
        if (parent != null) {
            parent.appendChild(this);
            this.parent = parent;
        }
        this.name = name;
        this.size = size;
        this.creationTime = creationTime;
    }

    public Directory getParent() {
        return this.parent;
    }
    
     public void setName(String name) {
		try {
			lock.lock();
			this.name = name; 
		} finally {
			lock.unlock();
		}
	}

	public void setSize(int size) {
		try {
			lock.lock();
			this.size = size; 
		} finally {
			lock.unlock();
		}
	}

	public String getName() {
		try {
			lock.lock();
			return this.name; 
		} finally {
			lock.unlock();
		}
    }

    public int getSize() {
    	try {
			lock.lock();
			return this.size;
		} finally {
			lock.unlock();
		}
    }

    public LocalDateTime getCreationTime() {
        return this.creationTime;
    }

    public abstract boolean isDirectory();
    public abstract boolean isFile();
    
    
}
