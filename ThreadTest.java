
public class ThreadTest {
	
	private RunTest rt;
	private Thread t1;
	private Thread t2;

	
	public ThreadTest() {
		rt = new RunTest();
		t1 = new Thread(rt);
		t2 = new Thread(rt);
	}
	
	public void startRun() {
		t1.start();
		t2.start();
	}

}
