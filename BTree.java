
public class BTree {
	private Node root;
	
	public BTree() {
		Node node1 = new Node(10);
		Node node2 = new Node(5);
		Node node3 = new Node(15);
		Node node4 = new Node(2);
		Node node5 = new Node(6);
		Node node6 = new Node(11);
		
		node1.setLeft(node2);
		node1.setRight(node3);
		node2.setLeft(node4);
		node2.setRight(node5);
		node3.setLeft(node6);
		
		root = node1;
	}
	
	public Node search(int n) {
		Node node = search(root, n);
		return node;
	}
	
	public Node search(Node root, int n) {
		
		if (root != null && root.getN() == n)
			return root;
		
		Node left = root.getLeft();
		Node right = root.getRight();
		Node node = null;
		
		if (left != null && n < root.getN())
			node = search(left, n);
		
		if (right != null && n > root.getN())
			node = search(right, n);
		
		return node;
	}

}
