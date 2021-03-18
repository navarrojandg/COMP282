
public class Driver {

	public static void main(String[] args) {
		TwoThreeFourTree tree = new TwoThreeFourTree();
		
		// Insertions
		printInsertionLegend();
		tree.insert(47);
		tree.insert(43);
		tree.insert(23);
		tree.insert(90);
		tree.insert(95);
		tree.insert(27);
		tree.insert(67);
		tree.insert(80);
		tree.insert(88);
		tree.insert(29);
		tree.insert(59);
		tree.insert(24);
		tree.insert(69);
		tree.insert(44);
		tree.insert(71);
		tree.insert(61);
		tree.insert(99);
		tree.insert(42);
		tree.insert(38);
		
		
		// Deletions
//		tree.delete(27);
//		tree.delete(38);
//		tree.delete(44);
//		tree.delete(95);
//		tree.delete(88);
//		tree.delete(59);
		
		
		
		// new tree
		tree = new TwoThreeFourTree();
		
		
		// Insertions
		printInsertionLegend();
		tree.insert(17);
		tree.insert(15);
		tree.insert(49);
		tree.insert(34);
		tree.insert(76);
		tree.insert(59);
		tree.insert(97);
		tree.insert(69);
		tree.insert(46);
		tree.insert(86);
		tree.insert(20);
		tree.insert(99);
		tree.insert(22);
		tree.insert(52);
		tree.insert(89);
		tree.insert(57);
		tree.insert(10);
		tree.insert(41);
		tree.insert(75);
		tree.insert(37);
		
		// Deletions
//		tree.delete(99);
//		tree.delete(22);
//		tree.delete(69);
//		tree.delete(15);
//		tree.delete(10);
//		tree.delete(75);
	
	}
	
	public static void printInsertionLegend() {
		System.out.println("\nLegend:\n I: Node where item was inserted\n P: Parent of insertion node\n R: Root of the tree\n");
	};

}
