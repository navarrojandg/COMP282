
public class Node {
	
	private int key;
	private COLOR color;

	public Node leftChild;
	public Node rightChild;
	public Node parent;
	
	public Node(int key) {
		this.key = key;
	};
	
	public int key() {
		return this.key;
	};
	
	public void setLeftChild(Node n) {
		this.leftChild = n;
		n.parent = this;
	};
	
	public void setRightChild(Node n) {
		this.rightChild = n;
		n.parent = this;
	};
	
	public void updateKey(int key) {
		this.key = key;
	};
	
	public void print() {
		System.out.println(this.key);
	};
	
	public void colorBlack() {
		this.color = COLOR.BLACK;
	};
	
	public void colorRed() {
		this.color = COLOR.RED;
	};
	
	public boolean isRoot() {
		return this.parent == null;
	};
}