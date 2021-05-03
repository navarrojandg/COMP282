
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

}