package edu.umb.cs681.hw07;
class RunnablePrimeGenerator extends PrimeGenerator implements Runnable {

    public RunnablePrimeGenerator(long from, long to) {
        super(from, to);
    }
    public void run(){
        generatePrimes();
    }



}
/*____________________________________________________________________________________________________________________*/
/*____________________________________________________________________________________________________________________*/