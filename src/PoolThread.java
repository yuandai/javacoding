package src;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolThread {
	
	private static final int MAX_T = 3;
	
	public static void main(String argv[]) {
		
		Task t1 = new Task("Task1");
		Task t2 = new Task("Task2");
		Task t3 = new Task("Task3");
		Task t4 = new Task("Task4");
		Task t5 = new Task("Task5");
		
		ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
		
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		
		pool.shutdown();
	
	}
	
	

}

class Task implements Runnable {
	
	String name;
	
	public Task(String name) {
		this.name = name;
	}
	
	public void run() {
		
		for (int i = 0; i < 5; i ++) {
			if (i == 0) {
				Date dt = new Date();
				SimpleDateFormat dtF = new SimpleDateFormat("MM/DD/YY HH/mm/ss");
				System.out.println(name + " started at " + dtF.format(dt));
			} else {
				Date dt = new Date();
				SimpleDateFormat dtF = new SimpleDateFormat("MM/DD/YY HH/mm/ss");
				System.out.println(name + " executed at " + dtF.format(dt));				
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
		
		Date dt = new Date();
		SimpleDateFormat dtF = new SimpleDateFormat("MM/DD/YY HH/mm/ss");
		System.out.println(name + " completed at " + dtF.format(dt));				

	}
	
}
