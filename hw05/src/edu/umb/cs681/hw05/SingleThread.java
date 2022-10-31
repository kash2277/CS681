package edu.umb.cs681.hw05;



public class SingleThread {
    public static void main(String[] args) throws InterruptedException {
        long before = System.currentTimeMillis();

        RunnablePrimeGenerator g = new RunnablePrimeGenerator(1L,2000000L);
        Thread t = new Thread(g);

        t.start();
        t.join();
        // g.getPrimes().forEach((primes) -> System.out.println(primes)); 

        long after = System.currentTimeMillis();
        long time_elapsed = after - before;
        System.out.print("Total time for execution on Thread: " + time_elapsed + "ms");


    }
}