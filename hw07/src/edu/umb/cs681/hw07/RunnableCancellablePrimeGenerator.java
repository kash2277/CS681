package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {

  private boolean done = false;
  private ReentrantLock locker = new ReentrantLock();

  public RunnableCancellablePrimeGenerator(long from, long to) {
    super(from, to);
  }

  //guarded with lock
  public void setDone() {
    locker.lock();
    try {
      done = true;
    } finally {
      locker.unlock();
    }
  }

  public void generatePrimes() {
    for (long n = from; n <= to; n++) {
      locker.lock();
      try {
        if (done) {
          System.out.println("Stopped generating prime numbers.");
          this.primes.clear();
          break;
        }
        if (isPrime(n)) {
          this.primes.add(n);
        }
      } finally {
        locker.unlock();
      }
    }
  }

  public static void main(String[] args) {
    RunnableCancellablePrimeGenerator generator = new RunnableCancellablePrimeGenerator(
      1,
      500
    );
    Thread thread = new Thread(generator);
    thread.start();
    generator.setDone();
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    generator.setDone();
    generator
      .getPrimes()
      .forEach((Long prime) -> System.out.print(prime + ", "));
    System.out.println(
      "\n" + generator.getPrimes().size() + " prime numbers are found."
    );
  }
}
