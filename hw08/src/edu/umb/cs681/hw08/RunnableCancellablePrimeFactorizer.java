package edu.umb.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer
  extends RunnablePrimeFactorizer {

  private ReentrantLock locker = new ReentrantLock();
  private boolean done = false;

  public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
    super(dividend, from, to);
  }

  public void setDone() {
    locker.lock();
    try {
      done = true;
    } finally {
      locker.unlock();
    }
  }

  protected boolean isEven(long n) {
    if (n % 2 == 0) {
      return true;
    } else {
      return false;
    }
  }

  public void generatePrimeFactors() {
    long divisor = from;

    while (dividend != 1 && divisor <= to) {
      locker.lock();
      try {
        if (done) {
          System.out.println("Prime factor generation has stopped!");
          break;
        }
        if (divisor > 2 && isEven(divisor)) {
          divisor++;
          continue;
        }
        if (dividend % divisor == 0) {
          factors.add(divisor);
          dividend /= divisor;
        } else {
          if (divisor == 2) {
            divisor++;
          } else {
            divisor += 2;
          }
        }
      } finally {
        locker.unlock();
      }
    }
  }

  public void run() {
    generatePrimeFactors();

    System.out.println(
      "Thread #" + Thread.currentThread() + " generated " + factors
    );
  }

  public static void main(String[] args) {
    RunnableCancellablePrimeFactorizer gen = new RunnableCancellablePrimeFactorizer(
      3000,
      2,
      24
    );
    Thread thread = new Thread(gen);
    thread.start();
    gen.setDone();

    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    gen
      .getPrimeFactors()
      .forEach((Long prime) -> System.out.print(prime + ", "));
    System.out.println(
      "\n" + "Early flag based termination, hence the output:"
    );
    System.out.println(
      "\n" + gen.getPrimeFactors().size() + " prime numbers are found."
    );
  }
}
