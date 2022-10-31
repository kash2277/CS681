package edu.umb.cs681.hw08;

import java.util.LinkedList;

public class PrimeFactorizer {

  protected long dividend, from, to;
  protected LinkedList<Long> factors = new LinkedList<Long>();

  public PrimeFactorizer(long dividend) {
    if (dividend >= 2) {
      this.dividend = dividend;
      this.from = 2;
      this.to = dividend;
    } else {
      throw new RuntimeException(
        "dividend must be >= 2. dividend==" + dividend
      );
    }
  }

  public long getDividend() {
    return dividend;
  }

  public long getFrom() {
    return from;
  }

  public long getTo() {
    return to;
  }

  public LinkedList<Long> getPrimeFactors() {
    return factors;
  }

  public void generatePrimeFactors() {
    long divisor = 2;

    while (dividend != 1 && divisor <= to) {
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
    }
  }

  public static void main(String[] args) {
    System.out.print("Prime factors of 8: ");
    var fac = new PrimeFactorizer(8);
    fac.generatePrimeFactors();
    System.out.println(fac.getPrimeFactors());

    System.out.print("Prime factors of 23: ");
    fac = new PrimeFactorizer(23);
    fac.generatePrimeFactors();
    System.out.println(fac.getPrimeFactors());

    System.out.print("Prime factors of 99: ");
    fac = new PrimeFactorizer(99);
    fac.generatePrimeFactors();
    System.out.println(fac.getPrimeFactors());

    System.out.print("Prime factors of 120: ");
    fac = new PrimeFactorizer(120);
    fac.generatePrimeFactors();
    System.out.println(fac.getPrimeFactors());

    System.out.print("Prime factors of 225: ");
    fac = new PrimeFactorizer(225);
    fac.generatePrimeFactors();
    System.out.println(fac.getPrimeFactors());

    System.out.print("Prime factors of 1001: ");
    fac = new PrimeFactorizer(1001);
    fac.generatePrimeFactors();
    System.out.println(fac.getPrimeFactors());

    System.out.print("Prime factors of 6561: ");
    fac = new PrimeFactorizer(6561);
    fac.generatePrimeFactors();
    System.out.println(fac.getPrimeFactors());
  }
}
