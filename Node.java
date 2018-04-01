
public class Node {
	
	private int n;
	private Node left;
	private Node right;
	
	public Node(int n) {
		this.n= n;
	}
	
	public int getN() {
		return n;
	}
	
	public void setN(int n) {
		this.n= n;
	}
	
	public Node getLeft() {
		return left;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
		
	public Node getRight() {
		return right;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}

}
