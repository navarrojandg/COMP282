
public class Driver {

	public static void main(String[] args) {
		AVLTree tree = new AVLTree();
		
		tree.insert(93);
		tree.insert(59);
		tree.insert(27);
		tree.insert(12);
		tree.insert(99);
		tree.insert(40);
		tree.insert(68);
		tree.insert(36);
		tree.insert(43);
		tree.insert(76);
		tree.insert(35);
		tree.insert(26);
		tree.insert(50);
		tree.insert(24);
		tree.insert(64);
		
		tree.printPreOrder();
		
		
	}
}
