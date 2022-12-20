package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;


public class Directory extends FSElement {
	
    private LinkedList<FSElement> children;
    private static ReentrantLock lock = new ReentrantLock();
    public Directory(Directory parent, String name, LocalDateTime creationTime) {
        super(parent, name, 0, creationTime);
        children = new LinkedList<FSElement>();
    }; 

    public LinkedList<FSElement> getChildren() {
    	try {
			lock.lock();
			return this.children;
		} finally {
			lock.unlock();
		}
    }
    
    public void appendChild(FSElement elem) {
        for (FSElement child : this.children) {
            if (elem.getName() == child.getName()) {
                if (elem.isDirectory() && child.isDirectory()) {
                    throw new RuntimeException("a sibling directory already exists with that name");
                }
                else if (elem.isFile() && child.isFile()) {
                    throw new RuntimeException("a sibling file already exists with that name");
                }
            }
        }
        this.children.add(elem);
    }


    public int countChildren() {
        return this.children.size();
    }

    public LinkedList<Directory> getSubDirectories() {
    	try {
			lock.lock();
			LinkedList<Directory> subDirs = new LinkedList<Directory>();
	        for (FSElement elem : this.getChildren()) {
	            if (elem.isDirectory()) {
	                subDirs.add((Directory) elem);
	            }
	        }
	        return subDirs; 
		} finally {
			lock.unlock();
		}
        
    }

    public LinkedList<File> getFiles() {
    	try {
			lock.lock();
			LinkedList<File> files = new LinkedList<File>();
	        for (FSElement elem : this.getChildren()) {
	            if (elem.isFile()) {
	                files.add((File)elem);
	            }
	        }
	        return files;
		} finally {
			lock.unlock();
		}
        
    }


    public int getTotalSize() {
        int fileSum = getFiles().stream().mapToInt(f->f.getSize()).sum();
        int dirSum = getSubDirectories().stream().mapToInt(d->d.getTotalSize()).sum();
        return fileSum + dirSum + this.getSize();
    }

    public boolean isDirectory() {
        return true;
    }

    public boolean isFile() {
        return false;
    }
    public static void main(String args[]) {}
}
