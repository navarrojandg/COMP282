public class AVLTree {
	private Node root;
	
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
	
	public static Node RRRotation(Node n) {
		Node p = n.rightChild;
		n.setRightChild(p.leftChild);
		p.setLeftChild(n);
		return p;
	};
	
	public void insert(int key) {
		this.root = AVLTree.insert(this.root, key);
	};
	
	public static Node insert(Node root, int key) {
		if (root == null) {
			root = new Node(key);
			return root;
		}
		
		if (key < root.key()) {
			root.setLeftChild(AVLTree.insert(root.leftChild, key));
		} else if (key > root.key()) {
			root.setRightChild(AVLTree.insert(root.rightChild, key));
		} else {
			throw new Error("Duplicate keys are not allowed.");
		}
		
		root = AVLTree.balance(root, key);
		
		return root;
	};
	
	public static Node balance(Node root, int key) {
		if (root.balanceFactor() > 1 && key < root.leftChild.key()) {
			// LL imbalance
			return AVLTree.LLRotation(root);
		};
		
		if (root.balanceFactor() > 1 && key > root.leftChild.key()) {
			// LR imbalance
			return AVLTree.LRRotation(root);
		}
		
		if (root.balanceFactor() < -1 && key < root.rightChild.key()) {
			// RL imbalance
			return AVLTree.RLRotation(root);
		}
		
		if (root.balanceFactor() < -1 && key > root.rightChild.key()) {
			// RR imbalance
			return AVLTree.RRRotation(root);
		}
		
		
		return root;
	};
	
	public void printInOrder() {
		AVLTree.printInOrder(this.root);
	};
	
	public static void printInOrder(Node root) {
		if (root != null) {
			AVLTree.printInOrder(root.leftChild);
			root.print();
			AVLTree.printInOrder(root.rightChild);
		}
	};
	
	public void printPreOrder() {
		AVLTree.printPreOrder(this.root);
		System.out.println();
	};
	
	public static void printPreOrder(Node root) {
		if (root != null) {
			System.out.printf("%d ", root.key());
			AVLTree.printPreOrder(root.leftChild);
			AVLTree.printPreOrder(root.rightChild);
		};
	};
}
