import java.util.HashMap;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("This is a test.");
		
		Singleton s = Singleton.getInstance();
		
		System.out.println("\n\nSingleton Test: " + s.getA());
		
		BTree bt = new BTree();
		Node node1 = bt.search(1);
		if (node1 != null)
			System.out.println("\n\nBTree Test: " + node1.getN());
		else
			System.out.println("\n\nBTree Test: " + "not found");
		Node node2 = bt.search(2);
		if (node2 != null)
			System.out.println("\n\nBTree Test: " + node2.getN());
		else
			System.out.println("\n\nBTree Test: " + "not found");
		Node node3 = bt.search(3);
		if (node3 != null)
			System.out.println("\n\nBTree Test: " + node3.getN());
		else
			System.out.println("\n\nBTree Test: " + "not found");
		Node node4 = bt.search(4);
		if (node4 != null)
			System.out.println("\n\nBTree Test: " + node4.getN());
		else
			System.out.println("\n\nBTree Test: " + "not found");

		MapTest map = new MapTest();
		HashMap hm = map.getMap();
		System.out.println("\n\nHashMap Test: " + hm.get("a"));
		System.out.println("\n\nHashMap Test: " + hm.get("b"));
		System.out.println("\n\nHashMap Test: " + hm.get("c"));
		System.out.println("\n\nHashMap Test: " + hm.get("d"));
		map.printMap();
		
		ThreadTest tt = new ThreadTest();
		tt.startRun();
		
		CallBack cb = new CallBack();
		Listner listner = new ListnerImpl();
		cb.register(listner);
		cb.callback();
		
		




		

	}

}
