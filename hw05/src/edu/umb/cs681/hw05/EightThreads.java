package edu.umb.cs681.hw05;

import java.util.LinkedList;

public class EightThreads {

  public static void main(String[] args) throws InterruptedException {
    long before = System.currentTimeMillis();

    int thread_count = 8;

    LinkedList<Thread> threads = new LinkedList<>();
    LinkedList<RunnablePrimeGenerator> generators = new LinkedList<>();

    long start = 1L;
    long end = 2000000L;

    long step = end / thread_count;


    for (int x = 0; x < thread_count; x++) {
      System.out.println(
        "Starting thread:" + x + ", Start:" + start + ", end:" + (start+step)
      );
      RunnablePrimeGenerator g = new RunnablePrimeGenerator(start, start+step);
      Thread t = new Thread(g);

      generators.add(g);
      threads.add(t);

      start += step;
      t.start();
    }

    int n = 0;
    for (int x = 0; x < thread_count; x++) {
    //   System.out.println("Joining thread:" + x);
      threads.get(x).join();
      n += generators.get(x).getPrimes().size();
    }

    long after = System.currentTimeMillis();
    long time_elapsed = after - before;
    System.out.print(
      "Total time for execution for "+thread_count+" Threads is: " + time_elapsed + "ms"
    );
    System.out.println(", Count: " + n);
  }
}
