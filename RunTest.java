
public class RunTest implements Runnable {
	
	public void run() {
		System.out.println("thread run ..." + Thread.currentThread().getId());
		sync();
		syncBlock();
		System.out.println("thread leave ..." + Thread.currentThread().getId());
	}
	
	
	public synchronized void sync() {
		System.out.println("sync run" + Thread.currentThread().getId());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		}
		
		System.out.println("sync leave" + Thread.currentThread().getId());
		
	}
	
	public void syncBlock() {
		
		synchronized(this) {
			System.out.println("sync block run" + Thread.currentThread().getId());
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				
			}
			
			System.out.println("sync block leave" + Thread.currentThread().getId());
		}
	
	}

}
