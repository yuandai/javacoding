package src;

public class CallBack {
	
	Listner listner;
	
	public void register(Listner listner) {
		this.listner = listner;
	}
	
	public void callback() {
		listner.perform();
	}

}
