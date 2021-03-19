
public class TwoThreeFourTree {
	
	public Node root;
	
	public TwoThreeFourTree() {
		this.root = new Node();
	};
	
	public void printInOrder() {
		TwoThreeFourTree.printInOrder(this.root);
	};
	
	public static void printInOrder(Node root) {
		if (root == null) return;
		if (root.isLeaf()) {
			// if node is a leaf we just print the data items
			root.print();
		} else if(root.size() == 3) {
			// has 3 data items
			TwoThreeFourTree.printInOrder(root.leftChild);
			
			System.out.printf(" %d", root.smallItem());
			
			TwoThreeFourTree.printInOrder(root.leftMidChild);
			
			System.out.printf(" %d", root.middleItem());
			
			TwoThreeFourTree.printInOrder(root.rightMidChild);
			
			System.out.printf(" %d", root.largeItem());
			
			TwoThreeFourTree.printInOrder(root.rightChild);
		} else if(root.size() == 2) {
			// has 2 data items
			TwoThreeFourTree.printInOrder(root.leftChild);
			
			System.out.printf(" %d", root.smallItem());
			
			TwoThreeFourTree.printInOrder(root.leftMidChild);
			
			System.out.printf(" %d", root.largeItem());
			
			TwoThreeFourTree.printInOrder(root.rightChild);
		} else if(root.size() == 1) {
			// has 1 data items
			TwoThreeFourTree.printInOrder(root.leftChild);
			
			System.out.printf(" %d", root.smallItem());
			
			TwoThreeFourTree.printInOrder(root.rightChild);
		} else {
			System.out.println("uhh how did you get here?");
		};
	};
	
	// INSERTION METHODS
	
	public void insert(int item) {
		System.out.printf("Insert - %d\n", item);
		this.root = TwoThreeFourTree.insert(this.root, item);
	};
	
	public static Node insert(Node root, int item) {
		Node curr = root;
		
		// the only time the root would be full is if
		// the root node is full
		
		do {
			switch (curr.size()) {
				case 3: {
					if (curr.isRoot()) {
						curr = TwoThreeFourTree.splitFourRoot(curr);
					} else {
						curr = TwoThreeFourTree.splitFour(curr);
					};
					break;
				}
				
				case 2: {
					// check which branch the item belongs in
					if (curr.isLeaf()) {
						break;
					} else if (item < curr.smallItem()) {
						// 13-30 a
						if (curr.leftChild.isFull()) { // HERE
							curr = TwoThreeFourTree.splitThreeLeft(curr.leftChild);
						} else {
							curr = curr.leftChild;
						};
					} else if (item < curr.largeItem()) {
						// 13-30 b
						if (curr.leftMidChild.isFull()) {
							curr = TwoThreeFourTree.splitThreeMid(curr.leftMidChild);
						} else {
							curr = curr.leftMidChild;
						};
					} else {
						// 13-30 c
						if (curr.rightChild.isFull()) {
							curr = TwoThreeFourTree.splitThreeRight(curr.rightChild);
						} else {
							curr = curr.rightChild;
						};
					};
					break;
				}
				
				case 1: {
					if (curr.isLeaf()) {
						break;
					} else if (item < curr.smallItem()) {
						// 13-29 a
						if (curr.leftChild.isFull()) {
							curr = TwoThreeFourTree.splitTwoLeft(curr.leftChild);
						} else {
							curr = curr.leftChild;
						};
					} else {
						// 13-29 b
						if (curr.rightChild.isFull()) {
							curr = TwoThreeFourTree.splitTwoRight(curr.rightChild);
						} else {
							curr = curr.rightChild;
						};
					};
					break;
				}
				default: {
					break;
				}
			};
		} while (!curr.isLeaf());
		
		// safe insert here 
		curr.insertItem(item);
		System.out.print("I");
		curr.print();

		
		if (!curr.isRoot()) {
			System.out.print("P");
			curr.parent.print();
		};
		
		// back up to the parent
		while (!curr.isRoot()) {
			curr = curr.parent;
		};
		
		System.out.print("R");
		curr.print();
		System.out.println();
		
		return curr;
	};
	
	public static Node splitFour(Node root) {
		Node smallNode = new Node(root.smallItem());
		Node largeNode = new Node(root.largeItem());
		
		smallNode.addLeftChild(root.leftChild);
		smallNode.addRightChild(root.leftMidChild);
		
		largeNode.addLeftChild(root.rightMidChild);
		largeNode.addRightChild(root.rightChild);
		
		if (root.parent.size() == 1) {
			// parent is two node
			if (root == root.parent.leftChild) {
				// left
//				System.out.println("Two Node - Split Four Left");
				root.parent.addLeftChild(smallNode);
				root.parent.addLeftMidChild(largeNode);
			} else {
				// right
//				System.out.println("Two Node - Split Four Right");
				root.parent.addLeftMidChild(smallNode);
				root.parent.addRightChild(largeNode);
			};
		} else {
			// parent is three node
			if (root == root.parent.leftChild) {
//				System.out.println("Three Node - Split Four Left");
				// left
				root.parent.addLeftChild(smallNode);
				root.parent.addLeftMidChild(largeNode);
			} else if (root == root.parent.rightChild) {
//				System.out.println("Three Node - Split Four Right");
				// right
				root.parent.addRightMidChild(smallNode);
				root.parent.addRightChild(largeNode);
			} else {
				// left mid
//				System.out.println("Three Node - Split Four Mid");
				root.parent.addLeftMidChild(smallNode);
				root.parent.addRightMidChild(largeNode);
			};
		};
		
		root.parent.insertItem(root.middleItem());
		
		return root.parent;
	};
	
	public static Node splitFourRoot(Node root) {
		// case 13-28
		
		Node smallNode = new Node(root.smallItem());
		Node middleNode = new Node(root.middleItem());
		Node largeNode = new Node(root.largeItem());
		
		middleNode.addLeftChild(smallNode);
		middleNode.addRightChild(largeNode);
		
		smallNode.addLeftChild(root.leftChild);
		smallNode.addRightChild(root.leftMidChild);
		
		largeNode.addLeftChild(root.rightMidChild);
		largeNode.addRightChild(root.rightChild);
		
		return middleNode;
	};
	
	public static Node splitTwoLeft(Node root) {
		// case 13-29 a
		
		// init small and large nodes
		Node smallNode = new Node(root.smallItem());
		Node largeNode = new Node(root.largeItem());
		
		smallNode.addLeftChild(root.leftChild);
		smallNode.addRightChild(root.leftMidChild);
		
		largeNode.addLeftChild(root.rightMidChild);
		largeNode.addRightChild(root.rightChild);
		
		// move mid item to parent
		root.parent.insertItem(root.middleItem());
		
		root.parent.addLeftChild(smallNode);
		root.parent.addLeftMidChild(largeNode);
		root.parent.addRightChild(root.parent.rightChild);
		
		return root.parent;
	};
	
	public static Node splitTwoRight(Node root) {
		// case 13-29 b
		
		// init small and large nodes
		Node smallNode = new Node(root.smallItem());
		Node largeNode = new Node(root.largeItem());
		
		smallNode.addLeftChild(root.leftChild);
		smallNode.addRightChild(root.leftMidChild);
		
		largeNode.addLeftChild(root.rightMidChild);
		largeNode.addRightChild(root.rightChild);
		
		// move mid item to parent
		root.parent.insertItem(root.middleItem());
		
		root.parent.addLeftChild(root.parent.leftChild);
		root.parent.addLeftMidChild(smallNode);
		root.parent.addRightChild(largeNode);
		
		return root.parent;
	};
	
	public static Node splitThreeLeft(Node root) {
		// case 13-30 a
		
		// init small and large nodes
		Node smallNode = new Node(root.smallItem());
		Node largeNode = new Node(root.largeItem());
		
		smallNode.addLeftChild(root.leftChild);
		smallNode.addRightChild(root.leftMidChild);
		
		largeNode.addLeftChild(root.rightMidChild);
		largeNode.addRightChild(root.rightChild);
		
		// move mid item to parent
		root.parent.insertItem(root.middleItem());
		
		// move left mid child to right
		// we do this beacuse the leftmid child acts as the middle branch
		root.parent.addRightMidChild(root.parent.leftMidChild);
		root.parent.addRightChild(root.parent.rightChild);
		
		root.parent.addLeftChild(smallNode);
		root.parent.addLeftMidChild(largeNode);
		
		return root.parent;
	};
	
	public static Node splitThreeMid(Node root) {
		// case 13-30 b
		
		// init small and large nodes
		Node smallNode = new Node(root.smallItem());
		Node largeNode = new Node(root.largeItem());
		
		smallNode.addLeftChild(root.leftChild);
		smallNode.addRightChild(root.leftMidChild);
		
		largeNode.addLeftChild(root.rightMidChild);
		largeNode.addRightChild(root.rightChild);
		
		// move mid item to parent
		root.parent.insertItem(root.middleItem());
		
		root.parent.addLeftChild(root.parent.leftChild);
		root.parent.addRightChild(root.parent.rightChild);
		
		root.parent.addLeftMidChild(smallNode);
		root.parent.addRightMidChild(largeNode);
		
		return root.parent;
	};
	
	public static Node splitThreeRight(Node root) {
		// case 13-30 c
		
		// init small and large nodes
		Node smallNode = new Node(root.smallItem());
		Node largeNode = new Node(root.largeItem());
		
		smallNode.addLeftChild(root.leftChild);
		smallNode.addRightChild(root.leftMidChild);
		
		largeNode.addLeftChild(root.rightMidChild);
		largeNode.addRightChild(root.rightChild);
		
		// move mid item to parent
		root.parent.insertItem(root.middleItem());
		
		root.parent.addLeftChild(root.parent.leftChild);
		root.parent.addLeftMidChild(root.parent.leftMidChild);
		
		root.parent.addRightMidChild(smallNode);
		root.parent.addRightChild(largeNode);
		
		return root.parent;
	};
	//
	
	// DELETION METHODS
	public void delete(int item) {
		System.out.printf("Delete - %d\n", item);
		this.root = TwoThreeFourTree.delete(this.root, item);
	};
	
	public static Node delete(Node root, int item) {
		// reference 
		// https://azrael.digipen.edu/~mmead/www/Courses/CS280/Trees-2-3-4-delete.html
		
		Node curr = root;
		
		// we need to search for the node that contains the item
		
		while (!curr.hasItem(item)) {
			// curr does not have the item
			
			switch (curr.size()) {
				case 1: {
					
					
					break;
				}
				
				case 2: {
					break;
				}
				
				case 3: {
					break;
				}
				
				default: {
					break;
				}
			}
		};
		
		return curr;
	};
	
	public static Node mergeSingleTwo(Node root) {
		Node temp = new Node();
		temp.insertItem(root.smallItem());
		temp.insertItem(root.leftChild.smallItem());
		temp.insertItem(root.rightChild.smallItem());
		
		temp.addLeftChild(root.leftChild.leftChild);
		temp.addLeftMidChild(root.leftChild.rightChild);
		temp.addRightMidChild(root.rightChild.leftChild);
		temp.addRightChild(root.rightChild.rightChild);
		
		return temp;
	};
	
	public Integer retreive(int item) {
		return TwoThreeFourTree.retrieve(this.root, item);
	};
	
	public static Integer retrieve(Node root, int item) {
		Node temp = TwoThreeFourTree.search(root, item);
		if (temp == null) return null;
		if (temp.hasItem(item)) {
			return temp.getItem(item);
		} else {
			return null;
		}
	};
	
	public Node search(int item) {
		return TwoThreeFourTree.search(this.root, item);
	};
	
	public static Node search(Node root, int item) {
		Node temp = null;
		// if (root == null) return temp;
		if (root.hasItem(item) || root.isLeaf()) {
			temp = root;
		} else if (root.size() == 3) {
			if (item < root.smallItem()) {
				temp = TwoThreeFourTree.search(root.leftChild, item);
			} else if (item < root.middleItem()) {
				temp = TwoThreeFourTree.search(root.leftMidChild, item);
			} else if (item < root.largeItem()) {
				temp = TwoThreeFourTree.search(root.rightMidChild, item);
			} else {
				temp = TwoThreeFourTree.search(root.rightChild, item);
			};
		} else if (root.size() == 2) {
			if (item < root.smallItem()) {
				temp = TwoThreeFourTree.search(root.leftChild, item);
			} else if (item < root.largeItem()) {
				temp = TwoThreeFourTree.search(root.leftMidChild, item);
			} else {
				temp = TwoThreeFourTree.search(root.rightChild, item);
			};
		} else if (root.size() == 1) {
			if (item < root.smallItem()) {
				temp = TwoThreeFourTree.search(root.leftChild, item);
			} else {
				temp = TwoThreeFourTree.search(root.rightChild, item);
			};
		};
		return temp;
	};
	
	
}
