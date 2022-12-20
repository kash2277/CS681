package edu.umb.cs681.hw11;

public class ACRunnable extends Aircraft implements Runnable{
	public ACRunnable(Position position) {
		super(position);
	}

	public void run() {
		Aircraft aircraft = new Aircraft(new Position(74.5, 72.4, 82.4));
		System.out.println("Current position: " + aircraft.getPosition().toString());
		aircraft.setPosition(76.2,69.96,80.34);
		System.out.println("New position: "+ aircraft.getPosition());
	}

	public static void main(String[] args) {
		Thread T1 = new Thread(new ACRunnable(new Position(74.5, 72.4, 82.4)));
		Thread T2 = new Thread(new ACRunnable(new Position(83.15, 43.6, 12.5)));
		T1.start();
		T2.start();
		try {
			T1.join();
			T2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
