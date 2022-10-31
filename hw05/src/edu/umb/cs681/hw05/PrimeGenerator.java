package edu.umb.cs681.hw05;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.LinkedList;
import java.util.List;

public class PrimeGenerator {
	protected long from, to;
	protected LinkedList<Long> primes = new LinkedList<Long>();

	public PrimeGenerator(long from, long to){
		if(from >= 1 && to > from){
			this.from = from;
			this.to = to;
		}else{
			throw new RuntimeException("Wrong values: from=" + from + " to=" + to);
		}	
	}
	
	public LinkedList<Long> getPrimes(){ return primes; };
	
	protected boolean isEven(long n){
		if(n%2 == 0){ return true; }
		else{ return false; }
	}
	
	protected boolean isPrime(long n){
		
		if(n <= 1){ return false; }
		  
		if( n > 2 && isEven(n) ){ return false; }
		long i;
		 
        for (i = (long) Math.sqrt(n); n%i != 0 && i >= 1; i--){}
         
        if (i == 1){ return true; }
        else{ return false; }
	}

	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			if( isPrime(n) ){ primes.add(n); }
        }
	}
	
	public static void main(String[] args) {
		
		PrimeGenerator g1 = new PrimeGenerator(1, 100);
		g1.generatePrimes();
		// g1.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + g1.getPrimes().size() + " prime numbers are found.");

		
		PrimeGenerator g2 = new PrimeGenerator(1, 100);
		List<Long> primes = LongStream.rangeClosed(g2.from, g2.to)
								.filter( (long n)->g2.isPrime(n) )
								.boxed()
								.collect(Collectors.toList());
		// primes.forEach( (Long prime)-> System.out.print(prime + ", ") );
		System.out.println("\n" + primes.size() + " prime numbers are found.");
								
	}
}