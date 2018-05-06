package src;

import java.util.LinkedList;

public class LinkedListExample {
	
	public static void main(String[] args) {
		
		LinkedList<String> lklst = new LinkedList<String>();
		
		lklst.add("1");
		lklst.add("2");
		lklst.add("3");
		lklst.add("4");
		lklst.add("5");
		lklst.add("6");
		lklst.add("7");
		lklst.add("8");
		lklst.add("9");
		
		f(lklst, 3);
		
		LinkedList<String> lklst1 = new LinkedList<String>();
		
		lklst1.add("1");
		lklst1.add("2");
		lklst1.add("3");
		lklst1.add("4");
		lklst1.add("5");
		lklst1.add("6");
		lklst1.add("7");
		lklst1.add("8");
		lklst1.add("9");
				
		f(lklst1, 4);
		
		LinkedList<String> lklst2 = new LinkedList<String>();
		
		lklst2.add("1");
		lklst2.add("2");
		lklst2.add("3");
		lklst2.add("4");
		lklst2.add("5");
		lklst2.add("6");
		lklst2.add("7");
		lklst2.add("8");
		lklst2.add("9");
				
		f(lklst2, 9);

		
	}
	
	private static void f(LinkedList<String> lklst, int k) {
		
		if (lklst == null || lklst.size() == 0)
			return;
		if (k < 0 || k > lklst.size())
			return;
				
		for (int i = 0; i < k/2; i ++) {
			String tmp = lklst.get(i);			
			lklst.set(i, lklst.get(k - 1 - i));
			lklst.set(k - 1 - i, tmp);
		}
				
		System.out.print("k=" + k + ": ");
		for (int i = 0; i < lklst.size(); i ++) {
			System.out.print(lklst.get(i) + ",");
		}
		
		System.out.println("");
		
	}

}
