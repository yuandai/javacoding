package src;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
	
	private static final int MAX_T = 3;
	
		public static void main(String argv[]) {
		
		TaskThread t1 = new TaskThread("Task1");
		TaskThread t2 = new TaskThread("Task2");
		TaskThread t3 = new TaskThread("Task3");
		TaskThread t4 = new TaskThread("Task4");
		TaskThread t5 = new TaskThread("Task5");
		
		ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
		
		pool.execute(t1);
		pool.execute(t2);
		pool.execute(t3);
		pool.execute(t4);
		pool.execute(t5);
		
		pool.shutdown();
	
	}
	
	

}

class TaskThread implements Runnable {
	
	String name;
	
	public TaskThread(String name) {
		this.name = name;
	}
	
	public void run() {
		
		for (int i = 0; i < 5; i ++) {
			if (i == 0) {
				Date dt = new Date();
				SimpleDateFormat dtF = new SimpleDateFormat("MM/dd/YY HH/mm/ss");
				System.out.println(name + "started at " + dtF.format(dt));
			} else {
				Date dt = new Date();
				SimpleDateFormat dtF = new SimpleDateFormat("MM/dd/YY HH/mm/ss");
				System.out.println(name + "executed at " + dtF.format(dt));				
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				
			}
		}
		
		Date dt = new Date();
		SimpleDateFormat dtF = new SimpleDateFormat("MM/dd/YY HH/mm/ss");
		System.out.println(name + "completed at " + dtF.format(dt));				

	}
	
}
