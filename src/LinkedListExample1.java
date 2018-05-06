package src;

public class LinkedListExample1 {
	
	public static void main(String[] args) {
		
		NodeSeq root = new NodeSeq(1);
		
		NodeSeq prev = root;
		for (int i = 1; i < 9; i ++) {
			NodeSeq n = new NodeSeq(i + 1);
			prev.next = n;
			prev = n;
		}
		
		NodeSeq prt = root;
		System.out.print("before ");
		while (prt != null) {
			System.out.print(prt.seq + ",");
			prt = prt.next;
		}
		System.out.println("");
		
		f(root, 6);
		
	}
	
	private static void f(NodeSeq root, int k) {
		
		if (root == null || k < 1)
			return;
		
		NodeSeq newRt = null;
		
		NodeSeq pt = root;
		for (int i = 0; i < k; i ++) {
			if (pt == null)
				break;
			NodeSeq tmp = pt;
			pt = tmp.next;
			tmp.next = newRt;
			newRt = tmp;
		}
		
		root.next = pt;
		
		NodeSeq prt = newRt;
		System.out.print("after  ");
		while (prt != null) {
			System.out.print(prt.seq + ",");
			prt = prt.next;
		}
		System.out.println("");
		
	}
	

}


class NodeSeq {
	int seq;
	NodeSeq next;
	
	public NodeSeq(int seq) {
		this.seq = seq;
	}
}

