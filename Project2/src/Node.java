public class Node {
	
	private int key;

	public Node leftChild;
	public Node rightChild;
	
	public Node(int key) {
		this.key = key;
	};
	
	public int key() {
		return this.key;
	};
	
	public void print() {
		System.out.println(this.key);
	};
	
	public int getHeight() {
		return Node.getHeight(this);
	};

	public static int getHeight(Node n) {
		if (n == null) { return -1; }
		
		int left = Node.getHeight(n.leftChild);
		int right = Node.getHeight(n.rightChild);
		
		if (left > right) {
			return left + 1;
		} else {
			return right + 1;
		}
	};
	
	public int balanceFactor() {
		return this.getHeightLeft() - this.getHeightRight();
	};
	
	public int getHeightLeft() {
		return Node.getHeightLeft(this);
	};
	
	public static int getHeightLeft(Node n) {
		if (n.leftChild == null) { return 0; }
		return Node.getHeight(n.leftChild) + 1;
	};
	
	public int getHeightRight() {
		return Node.getHeightRight(this);
	};
	
	public static int getHeightRight(Node n) {
		if (n.rightChild == null) { return 0; }
		return Node.getHeight(n.rightChild) + 1;
	};
	
	public void setLeftChild(Node n) {
		this.leftChild = n;
	};
	
	public void setRightChild(Node n) {
		this.rightChild = n;
	};
	
	public void updateKey(int key) {
		this.key = key;
	};
	
	
}
