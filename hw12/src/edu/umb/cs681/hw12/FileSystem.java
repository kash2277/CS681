package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

public class FileSystem{

    private static FileSystem theInstance;
    private LinkedList<Directory> rootDirectories;
    private static ReentrantLock lock = new ReentrantLock();
    private FileSystem() {
        this.rootDirectories = new LinkedList<Directory>();
    }; 

    public static FileSystem getFileSystem() {
        if (theInstance == null) {
            theInstance = new FileSystem();
        }
        return theInstance;
    }

    public LinkedList<Directory> getRootDirs() {
    	try {
			lock.lock();
			return this.rootDirectories; 
		} finally {
			lock.unlock();
		}
        
    }

    public void appendRootDirectory(Directory dir) {
        this.rootDirectories.add(dir);
        dir.parent = null;
    }
    
    
    public static void main(String args[]) {
    	FileSystem fs = FileSystem.getFileSystem();
    	Directory root = new Directory(null, "root", LocalDateTime.now());
    	Directory apps = new Directory(root, "Apps", LocalDateTime.now());
    	File x = new File(apps, "x", 20, LocalDateTime.now());
    	Directory bin = new Directory(root, "bin", LocalDateTime.now());
        File y = new File(bin, "y", 16, LocalDateTime.now());
    	Directory home = new Directory(root, "home", LocalDateTime.now());
    	Link z = new Link(home, "z", LocalDateTime.now(), bin);
    	File cFile = new File(home, "c", 25, LocalDateTime.now());
        Directory pictures = new Directory(home, "pictures", LocalDateTime.now());
        File a = new File(pictures, "apps", 8, LocalDateTime.now());
        File b = new File(pictures, "b", 11, LocalDateTime.now());
        Link c = new Link(home, "c", LocalDateTime.now(), y);
        
        fs.appendRootDirectory(root);
        
        
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            threads.add(new Thread(() -> {

                System.out.println("Root directory name: "+root.getName());
                System.out.println("Root directory size: "+root.getTotalSize());
                System.out.println("Home directory name: "+home.getTotalSize());

                System.out.println("File name: "+a.getName());
                System.out.println("Is file a directory?: "+a.isDirectory());
                System.out.println("File size: "+a.getSize());


                System.out.println("Pictures directory children: "+a.getParent().getParent().countChildren());
                System.out.println("Home directory children: "+cFile.getParent().getParent().countChildren());
                System.out.println("Bin directory parent name: "+bin.getParent().getName());
                System.out.println("------------------------------------------------------");
            }));
        }
        for (Thread t : threads) {
            try {
            	Thread.sleep(1000);
                t.start();
                t.join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        }
    }

	
}
