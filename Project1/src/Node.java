import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
		
public class Node {
	private List<Integer> items = new ArrayList<Integer>();
	
	Node leftChild;
	Node leftMidChild;
	Node rightMidChild;
	Node rightChild;
	
	Node parent;
	
	public Node() { };
	
	public Node(List<Integer> items) {
		for (Integer i: items) {
			this.insertItem(i);
		};
	};
	
	public Node(int[] items) {
		for (int i: items) {
			this.insertItem(i);
		};
	};
	
	public Node(int item) {
		this.insertItem(item);
	};
	
	public Integer smallItem() {
		if (this.isEmpty()) return null;
		return this.items.get(0);
	};
	public Integer middleItem() {
		if (this.isEmpty()) return null;
		return this.items.get(1);
	};
	public Integer largeItem() {
		if (this.isEmpty()) return null;
		return this.items.get(this.size() - 1);
	};
	
	public List<Integer> getItems() {
		return this.items;
	};
	
	public void print() {
		for (Integer i: this.items) {
			System.out.printf(" %d", i);
		};
		System.out.println("");
	};
	
	public void printChildren() {
		System.out.printf("LC %s", this.leftChild);
		this.leftChild.print();
		
		System.out.printf("LMC %s", this.leftMidChild);
		this.leftMidChild.print();
		
		System.out.printf("RMC %s", this.rightMidChild);
		this.rightMidChild.print();
		
		System.out.printf("RC %s", this.rightChild);
		this.rightChild.print();
	};
	
	public boolean isLeaf() {
		return this.leftChild == null &&
				this.leftMidChild == null &&
				this.rightMidChild == null &&
				this.rightChild == null;
	};
	
	public boolean isRoot() {
		return this.parent == null;
	};
	
	public boolean isEmpty() {
		return this.items.isEmpty();
	};
	
	public boolean isFull() {
		return this.size() >= 3;
	};
	
	public int size() {
		return this.items.size();
	};
	
	public int childrenSize() {
		int count = 0;
		if (this.leftChild != null) count++;
		if (this.leftMidChild != null) count++;
		if (this.rightMidChild != null) count++;
		if (this.rightChild != null) count++;
		return count;
	};
	
	public boolean hasItem(Integer key) {
		if (key == null) return false;
		return this.items.contains(key);
	};
	
	public Integer getItem(Integer key) {
		if (!this.hasItem(key)) return null;
		if (this.smallItem().equals(key)) return key;
		if (this.middleItem().equals(key)) return key;
		if (this.largeItem().equals(key)) return key;
		return null;
	};
	
	public int getItemIdx(Integer key) {
		if (!this.hasItem(key)) return -1;
		if (this.smallItem().equals(key)) return 0;
		if (this.middleItem().equals(key)) return 1;
		if (this.largeItem().equals(key)) return 2;
		return -1;
	};
	
	public boolean insertItem(int item) {
		if (this.isFull()) return false;
		this.items.add(item);
		Collections.sort(this.items);
		return true;
	};
	
	public boolean removeItem(Integer key) {
		int idx = this.getItemIdx(key);
		if (idx < 0) return false;
		this.items.remove(idx);
		Collections.sort(this.items);
		return true;
	};
	
	public void addLeftChild(Node node) {
		this.leftChild = node;
		if (node != null) {
			node.parent = this;
		}
	};
	
	public void addLeftMidChild(Node node) {
		this.leftMidChild = node;
		if (node != null) {
			node.parent = this;
		}
	};
	
	public void addRightMidChild(Node node) {
		this.rightMidChild = node;
		if (node != null) {
			node.parent = this;
		}
	};
	
	public void addRightChild(Node node) {
		this.rightChild = node;
		if (node != null) {
			node.parent = this;
		}
	};
	
	
	
}
