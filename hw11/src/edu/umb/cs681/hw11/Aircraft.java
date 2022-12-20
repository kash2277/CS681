package edu.umb.cs681.hw11;

import java.util.concurrent.locks.ReentrantLock;

public class Aircraft {
	
	private ReentrantLock lock = new ReentrantLock();
    private Position position;

    public Aircraft(Position pos) {
        this.position = pos;
    }

    public void setPosition(double newLat,double newLong,double newAlt) {
        lock.lock();
        System.out.println("Locked!");
        try {
        	this.position = this.position.change(newLat, newLong, newAlt);
        }
        finally {
            lock.unlock();
            System.out.println("Unlocked!");
        }
    }

    public Position getPosition() {
        lock.lock();
        System.out.println("Locked!");
        try {
            return this.position;
        }
        finally {
            lock.unlock();
            System.out.println("Unlocked!");
        }
    }
	
}
