
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
		
		tree.delete(68);
		tree.delete(59);
		tree.delete(26);
		tree.delete(50);
		tree.delete(64);
		tree.delete(93);
		
		tree.printPreOrder();
		System.out.println("END");
		
		tree = new AVLTree();
		
		tree.insert(58); 
		tree.insert(70); 
		tree.insert(46); 
		tree.insert(48); 
		tree.insert(96); 
		tree.insert(98); 
		tree.insert(22); 
		tree.insert(24); 
		tree.insert(10); 
		tree.insert(63); 
		tree.insert(68); 
		tree.insert(94); 
		tree.insert(44); 
		tree.insert(87); 
		tree.insert(84);
		
		tree.printPreOrder();
		
		tree.delete(96);
		tree.delete(68);
		tree.delete(44);
		tree.delete(87);
		tree.delete(84);
		tree.delete(22);
		
		tree.printPreOrder();
		System.out.println("END");
		
	}
}
