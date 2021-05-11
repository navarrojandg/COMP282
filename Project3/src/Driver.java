
public class Driver {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RedBlackTree T = new RedBlackTree();
		
		insert(T, 49);
		insert(T, 85);
		insert(T, 51);
		insert(T, 90);
		insert(T, 28);
		insert(T, 37);
		insert(T, 61);
		insert(T, 10);
		insert(T, 32);
		insert(T, 40);
		insert(T, 59);
		insert(T, 19);
		insert(T, 63);
		insert(T, 26);
		insert(T, 55);
		
		RedBlackTree.InOrderTreeWalk(T, T.root);
		System.out.println();
		
		delete(T, 49);
		delete(T, 59);
		delete(T, 51);
		delete(T, 19);
		delete(T, 26);
		delete(T, 10);
		
		RedBlackTree.InOrderTreeWalk(T, T.root);
		System.out.println();
		
//		 NEW TREE
		T = new RedBlackTree();
		
		insert(T, 14);
		insert(T, 95);
		insert(T, 71);
		insert(T, 36);
		insert(T, 25);
		insert(T, 88);
		insert(T, 27);
		insert(T, 33);
		insert(T, 79);
		insert(T, 81);
		insert(T, 63);
		insert(T, 26);
		insert(T, 30);
		insert(T, 44);
		insert(T, 62);
		
		RedBlackTree.InOrderTreeWalk(T, T.root);
		System.out.println();
		
		delete(T, 88);
		delete(T, 30);
		delete(T, 27);
		delete(T, 79);
		delete(T, 62);
		delete(T, 44);
		
		RedBlackTree.InOrderTreeWalk(T, T.root);
		System.out.println();
		
		
	}

	public static void insert(RedBlackTree T, int key) {
		Node z = new Node(T, key);
		RedBlackTree.Insert(T, z);
	}
	
	public static void delete(RedBlackTree T, int key) {
		Node z = RedBlackTree.Search(T, T.root, key);
		RedBlackTree.Delete(T, z);
	}
	
	
}
