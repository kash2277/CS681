package edu.umb.cs681.hw05;


public class TwoThreads {
    public static void main(String[] args) throws InterruptedException {
        long before = System.currentTimeMillis();

        RunnablePrimeGenerator g = new RunnablePrimeGenerator(1L,1000000L);
    
        Thread t = new Thread(g);

        RunnablePrimeGenerator g1 = new RunnablePrimeGenerator(1000000L,2000000L);

        Thread t1 = new Thread(g);

        t.start(); 
        t1.start();
        
        t.join(); 
        t1.join();

        // g.getPrimes().forEach((primes) -> System.out.println(primes));
        // g1.getPrimes().forEach((primes) -> System.out.println(primes));

        long after = System.currentTimeMillis();
        long time_elapsed = after - before;
        System.out.print("Total time for execution Two Threads is: " + time_elapsed + "ms");


    }
}