package src;

public class Singleton {
	
	private static Singleton obj;
	private int a = 10;
	
	private Singleton() {};
	
	public static Singleton getInstance() {
		if (obj == null) {
			obj = new Singleton();
		}
		return obj;
	}
	
	public int getA() {
		return a;
	}
	
	public void setA(int a) {
		this.a = a;
	}

}
