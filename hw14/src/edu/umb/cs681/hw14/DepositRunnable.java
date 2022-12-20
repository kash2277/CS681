package edu.umb.cs681.hw14;

public class DepositRunnable implements Runnable {
    public boolean done = false;
    private ThreadSafeBankAccount2 account = null;

    public DepositRunnable(ThreadSafeBankAccount2 account) {
        this.account = account;
    }

    public void setDone() {
        this.done = true;
    }
    
    @Override
    public void run() {
        while (true) {
            if (done) {
                System.out.println(Thread.currentThread().getName() + " Money deposited!");
                break;
            }
            System.out.println(Thread.currentThread().getName() + " Money is being deposited...");
            account.deposit(300);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
            	//exception.printStackTrace();
            }
        }
    }
}
