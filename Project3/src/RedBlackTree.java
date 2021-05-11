
public class RedBlackTree {
	public Node root;
	public Node nil;
	
	public RedBlackTree() {
		this.nil = new Node();
		this.root = this.nil;
	};
	
	public static void InOrderTreeWalk(RedBlackTree T, Node x) {
		if (x != T.nil) {
			RedBlackTree.InOrderTreeWalk(T, x.left);
			System.out.printf("[%d][%s] ", x.key, x.color == COLOR.BLACK ? "B" : "R");
			RedBlackTree.InOrderTreeWalk(T, x.right);
		}
		
	};
	
	public static Node Search(RedBlackTree T, Node x, int key) {
		if (x == T.nil || key == x.key) {
			return x;
		}
		
		if (key < x.key) {
			return RedBlackTree.Search(T, x.left, key);
		} else {
			return RedBlackTree.Search(T, x.right, key);
		}
		
	};
	
	public static RedBlackTree LeftRotate(RedBlackTree T, Node x) {
		Node y = x.right; // set y
		x.right = y.left; // turn y's left subtree into x's right subtree
		if (y.left != T.nil) { y.left.parent = x; }
		y.parent = x.parent; // link x's parent to y
		if (x.parent == T.nil) {
			T.root = y;
		} else if (x == x.parent.left) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}
		y.left = x; // put x on y's left
		x.parent = y;
		
		return T;
	};
	
	public static RedBlackTree RightRotate(RedBlackTree T, Node y) {
		Node x = y.left; // set x
		y.left = x.right; // turn x's right subtree into y's left subtree
		if (x.right != T.nil) { x.right.parent = y; }
		x.parent = y.parent; // link y's parent to x
		if (y.parent == T.nil) {
			T.root = x;
		} else if(y == y.parent.right) {
			y.parent.right = x;
		} else {
			y.parent.left = x;
		}
		x.right = y; // put y on x's left
		y.parent = x;
		
		return T;
	};
	
	public static RedBlackTree Insert(RedBlackTree T, Node z) {
		Node y = T.nil;
		Node x = T.root;
		
		while (x != T.nil) {
			y = x;
			if (z.key < x.key) {
				x = x.left;
			} else {
				x = x.right;
			}
		}
		z.parent = y;
		if (y == T.nil) {
			T.root = z;
		} else if(z.key < y.key) {
			y.left = z;
		} else {
			y.right = z;
		}
		z.left = T.nil;
		z.right = T.nil;
		z.color = COLOR.RED;
		return RedBlackTree.InsertFixup(T, z);
	}
	
	public static RedBlackTree InsertFixup(RedBlackTree T, Node z) {
		Node y;
		
		while (z.parent.color == COLOR.RED) {
			if (z.parent == z.parent.parent.left) {
				y = z.parent.parent.right;
				if (y.color == COLOR.RED) {
					z.parent.color = COLOR.BLACK;
					y.color = COLOR.BLACK;
					z.parent.parent.color = COLOR.RED;
					z = z.parent.parent;
				} else {
					if (z == z.parent.right) {
						z = z.parent;
						// LEFT ROTATE
						RedBlackTree.LeftRotate(T, z);
					}
					z.parent.color = COLOR.BLACK;
					z.parent.parent.color = COLOR.RED;
					// RIGHT ROTATE
					RedBlackTree.RightRotate(T, z.parent.parent);
				};
			} else {
				// same as if then clause with "right" and "left" exchanged
				y = z.parent.parent.left;
				if (y.color == COLOR.RED) {
					z.parent.color = COLOR.BLACK;
					y.color = COLOR.BLACK;
					z.parent.parent.color = COLOR.RED;
					z = z.parent.parent;
				} else {
					if (z == z.parent.left) {
						z = z.parent;
						// RIGHT ROTATE
						RedBlackTree.RightRotate(T, z);
						
					}
					z.parent.color = COLOR.BLACK;
					z.parent.parent.color = COLOR.RED;
					// LEFT ROTATE
					RedBlackTree.LeftRotate(T, z.parent.parent);
				};
			};
		}
		T.root.color = COLOR.BLACK;
		
		return T;
	};
	
	public static RedBlackTree Transplant(RedBlackTree T, Node u, Node v) {
		if (u.parent == T.nil) {
			T.root = v;
		} else if(u == u.parent.left) {
			u.parent.left = v;
		} else {
			u.parent.right = v;
		}
		v.parent = u.parent;
		
		return T;
	};
	
	public static RedBlackTree Delete(RedBlackTree T, Node z) {
		Node x;
		Node y = z;
		COLOR initYColor = y.color;
		if (z.left == T.nil) {
			x = z.right;
			T = RedBlackTree.Transplant(T, z, z.right);
		} else if(z.right == T.nil) {
			x = z.left;
			T = RedBlackTree.Transplant(T, z, z.left);
		} else {
			y = RedBlackTree.Minimum(T, z.right);
			initYColor = y.color;
			x = y.right;
			if (y.parent == z) {
				x.parent = y;
			} else {
				T = RedBlackTree.Transplant(T, y, y.right);
				y.right = z.right;
				y.right.parent = y;
			}
			T = RedBlackTree.Transplant(T, z, y);
			y.left = z.left;
			y.left.parent = y;
			y.color = z.color;
		}
		
		if (initYColor == COLOR.BLACK) {
			T = RedBlackTree.DeleteFixup(T, x);
		}
		
		return T;
	};
	
	public static Node Minimum(RedBlackTree T, Node x) {
		while(x.left != T.nil) { x = x.left; }
		return x;
	}
	
	public static RedBlackTree DeleteFixup(RedBlackTree T, Node x) {
		Node w;
		
		while (x != T.root && x.color == COLOR.BLACK) {
			if (x == x.parent.left) {
				w = x.parent.right;
				if (w.color == COLOR.RED) {
					w.color = COLOR.BLACK;
					x.parent.color = COLOR.RED;
					T = RedBlackTree.LeftRotate(T, x.parent);
					w = x.parent.right;
				}
				if (w.left.color == COLOR.BLACK && w.right.color == COLOR.BLACK) {
					w.color = COLOR.RED;
					x = x.parent;
				} else {
					if (w.right.color == COLOR.BLACK) {
						w.left.color = COLOR.BLACK;
						w.color = COLOR.RED;
						T = RedBlackTree.RightRotate(T, w);
						w = x.parent.right;
					}
					w.color = x.parent.color;
					x.parent.color = COLOR.BLACK;
					w.right.color = COLOR.BLACK;
					T = RedBlackTree.LeftRotate(T, x.parent);
					x = T.root;
				};
			} else {
				// same as then clause with right and left exchanged
				w = x.parent.left;
				if (w.color == COLOR.RED) {
					w.color = COLOR.BLACK;
					x.parent.color = COLOR.RED;
					T = RedBlackTree.RightRotate(T, x.parent);
					w = x.parent.left;
				}
				if (w.right.color == COLOR.BLACK && w.left.color == COLOR.BLACK) {
					w.color = COLOR.RED;
					x = x.parent;
				} else {
					if (w.left.color == COLOR.BLACK) {
						w.right.color = COLOR.BLACK;
						w.color = COLOR.RED;
						T = RedBlackTree.LeftRotate(T, w);
						w = x.parent.left;
					}
					w.color = x.parent.color;
					x.parent.color = COLOR.BLACK;
					w.left.color = COLOR.BLACK;
					T = RedBlackTree.RightRotate(T, x.parent);
					x = T.root;
				};
			}
		}
		x.color = COLOR.BLACK;
		
		return T;
	};
}
