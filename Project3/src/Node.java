
public class Node {
	
	public int key;
	public COLOR color;

	public Node left;
	public Node right;
	public Node parent;
	
	public Node() {
		// should only be used for the nil node
		this.color = COLOR.BLACK;
	};
	
	public Node(int key) {
		this.key = key;
	};
	
	public Node(RedBlackTree T, int key) {
		this.key = key;
		this.left = T.nil;
		this.right = T.nil;
		this.parent = T.nil;
	};

}