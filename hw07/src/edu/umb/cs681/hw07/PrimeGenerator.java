package edu.umb.cs681.hw07;

import java.util.LinkedList;

public class PrimeGenerator{
    protected long from, to;
    protected LinkedList<Long> primes = new LinkedList<Long>();

    public PrimeGenerator(long a, long b){
        from = a;
        to = b;
    }
    public void generatePrimes() {
        while (from < to) {
            if (isPrime(from))
                //  System.out.println(from + " ");
                primes.add(from);

            ++from;
        }
    }

    public LinkedList<Long> getPrimes(){
        return primes;
    }
    public static boolean isPrime(long num) {
        boolean flag = true;

        for (int i = 2; i <= num / 2 ; ++i) {

            if (num % i == 0) {
                flag = false;
                break;
            }
        }

        return flag;
    }


}