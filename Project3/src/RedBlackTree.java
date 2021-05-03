
public class RedBlackTree {
	private Node root;
	
	// same as right rotate
	public static Node LLRotation(Node n) {
		Node p = n.leftChild;
		n.setLeftChild(p.rightChild);
		p.setRightChild(n);
		
		return p;
	};
	
	public static Node LRRotation(Node n) {
		Node p = n.leftChild.rightChild;
		n.leftChild.setRightChild(p.leftChild);
		p.setLeftChild(n.leftChild);
		
		n.setLeftChild(p.rightChild);
		p.setRightChild(n);
		
		return p;
	};
	
	public static Node RLRotation(Node n) {
		Node p = n.rightChild.leftChild;
		n.rightChild.setLeftChild(p.rightChild);
		p.setRightChild(n.rightChild);
		
		n.setRightChild(p.leftChild);
		p.setLeftChild(n);
		
		return p;
	};
	
	// same as a left rotate
	public static Node RRRotation(Node n) {
		Node p = n.rightChild;
		n.setRightChild(p.leftChild);
		p.setLeftChild(n);
		return p;
	};
	
	public void insert(int key) {
		this.root = RedBlackTree.insert(this.root, key);
		
		
	};
	
	public static Node insert(Node root, int key) {
		if (root == null) {
			root = new Node(key); // new nodes are initialized to red
			return root;
		};
		
		if (key < root.key()) {
			root.setLeftChild(RedBlackTree.insert(root.leftChild, key));
		} else if (key > root.key()) {
			root.setRightChild(RedBlackTree.insert(root.rightChild, key));
		} else {
			throw new Error("Duplicate keys are not allowed.");
		}
		
		return root;
	};
}
