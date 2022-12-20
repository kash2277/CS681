package edu.umb.cs681.hw16;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		AdmissionControl admissionC = new AdmissionControl();
		ArrayList<Thread> threads = new ArrayList<Thread>();
		 for (int i = 0; i < 3 ; i++) 
		 { 
			 Thread t1 = new Thread(admissionC.new EntranceHandler());
			 threads.add(t1);
			 t1.start();
		 }
		 Thread tm = new Thread(admissionC.new StatsHandler());
		 threads.add(tm);
		 tm.start();
		 try {
				Thread.sleep(1000);
		 } catch (InterruptedException e) {			
				e.printStackTrace();
		 }
		
		 for (int i = 0; i < 2 ; i++)
		 {
			 Thread te = new Thread(admissionC.new ExitHandler());
			 threads.add(te);
			 te.start();
			 
		 }
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {			
			e.printStackTrace();
		}	
		System.out.println("Going to make it interrupt..threads.get(i).interrupt()");
		 for (int i = 0; i < 2; i++) 
		 {
			 threads.get(i).interrupt();
		 }
		 
		System.out.println("Going to set done to true");
		admissionC.setDone();
		System.out.println("Set done finished..");
		 								
		 for (int i = 0; i < 2; i++) 
		 {
			 try {								
					 threads.get(i).join();
				 }
			catch (Exception e) {
				System.out.println(e);
			}
		}	
	}
}
