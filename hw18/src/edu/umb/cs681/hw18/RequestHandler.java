package edu.umb.cs681.hw18;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable {

    private static ArrayList<Path> filePaths = new ArrayList<>();
    private ReentrantLock lock;
    private boolean done = false;

    public RequestHandler() {
        lock = new ReentrantLock();
    }

    public void setDone() {
        lock.lock();
        try {
            done = true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        AccessCounter accessCounter = AccessCounter.getInstance();
        while (true) {
            lock.lock();
            try {
                if (done) {
                	break;
                }
                int randInt = new Random().nextInt(filePaths.size());
                Path path = filePaths.get(randInt);
                accessCounter.increment(path);
                System.out.println(Thread.currentThread().getName() + "\t " + path + " count " + accessCounter.getCount(path));
            } finally {
                lock.unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                continue;
            }
        }
    }

    public static void main(String[] args) {
        filePaths.add(Paths.get("a.html"));
        filePaths.add(Paths.get("b.html"));

        ArrayList<Thread> threads = new ArrayList<>();
        RequestHandler requestHandler = new RequestHandler();

        for (int i = 0; i < 12; i++) {
            Thread thread = new Thread(requestHandler);
            threads.add(thread);
            thread.start();
        }
        threads.forEach(t -> {
            try {
                t.interrupt();
                requestHandler.setDone();
                t.join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
