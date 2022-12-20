package edu.umb.cs681.hw14;

public class WithdrawRunnable implements Runnable {
	
    public boolean done = false;
    private ThreadSafeBankAccount2 account = null;

    public WithdrawRunnable(ThreadSafeBankAccount2 account) {
        this.account = account;
    }

    public void setDone() {
        done = true;
    }
    
    @Override
    public void run() {
        while (true) {
            if (done) {
                System.out.println(Thread.currentThread().getName() + " Money withdrawn!");
                break;
            }
            System.out.println(Thread.currentThread().getName() + " Money is being withdrawn...");
            account.withdraw(300);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
            	//exception.printStackTrace();
            }
        }
    }
}
