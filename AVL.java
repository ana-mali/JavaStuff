package cp213;

//import java.util.ArrayList;

/**
 * Implements an AVL (Adelson-Velsky Landis) tree. Extends BST.
 *
 * @author Anastasia Malinovski
 * @author David Brown
 * @version 2021-07-05
 */
public class AVL<T extends Comparable<T>> extends BST<T> {

	/**
	 * Returns the balance value of node. If greater than 1, then left heavy, if
	 * less than -1, then right heavy. If in the range -1 to 1 inclusive, the node
	 * is balanced. Used to determine whether to rotate a node upon insertion.
	 *
	 * @param node The TreeNode to analyze for balance.
	 * @return A balance number.
	 */
	private int balance(final TreeNode<T> node) {
//		int balance = 0;
//		if (node!=null) {
//			TreeNode<T> left=node.getLeft();
//			TreeNode<T> right=node.getRight();
//			if (left!=null) {
//				balance+=1;
//				balance+=balance(left);
//			}
//			if (right!=null) {
//				balance-=1;
//				balance-=balance(right);
//			}
//		}
		return this.nodeHeight(node.getLeft()) - this.nodeHeight(node.getRight());
	}

	/**
	 * Performs a left rotation around node.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateLeft(final TreeNode<T> node) {
		TreeNode<T> x = node.getRight();
		TreeNode<T> z = x.getLeft();
		node.setRight(z);
		x.setLeft(node);

		node.updateHeight();
		x.updateHeight();
		return x;
	}

	/**
	 * Performs a right rotation around {@code node}.
	 *
	 * @param node The subtree to rotate.
	 * @return The new root of the subtree.
	 */
	private TreeNode<T> rotateRight(final TreeNode<T> node) {
		TreeNode<T> x = node.getLeft();
		TreeNode<T> z = x.getRight();
		node.setLeft(z);
		x.setRight(node);

		node.updateHeight();
		x.updateHeight();
		return x;
	}

	/**
	 * Auxiliary method for {@code insert}. Inserts data into this AVL.
	 *
	 * @param node the current node (TreeNode)
	 * @param data Data to be inserted into the node
	 * @return The inserted node.
	 */
	@Override
	protected TreeNode<T> insertAux(TreeNode<T> node, final CountedValue<T> data) {
		int balance = 0;

		if (node == null) {
			node = new TreeNode<T>(data);
			node.getValue().incrementCount();
		} else {
			final int cmp = node.getValue().compareTo(data);

			if (cmp > 0) {
				node.setLeft(this.insertAux(node.getLeft(), data));
				node.updateHeight();
				balance = this.balance(node);

				if (balance > 1 && this.balance(node.getLeft()) >= 0) {
					node = this.rotateRight(node);
				} else if (balance > 1 && this.balance(node.getLeft()) < 0) {
					node.setLeft(this.rotateLeft(node.getLeft()));
					node = this.rotateRight(node);
				}
			} else if (cmp < 0) {
				node.setRight(this.insertAux(node.getRight(), data));
				node.updateHeight();
				balance = this.balance(node);

				if (balance < -1 && this.balance(node.getRight()) <= 0) {
					node = this.rotateLeft(node);
				} else if (balance < -1 && this.balance(node.getRight()) > 0) {
					node.setRight(this.rotateRight(node.getRight()));
					node = this.rotateLeft(node);
				}
			} else {
				node.getValue().incrementCount();

			}
		}
//		ArrayList<TreeNode<T>> a_list=new ArrayList<TreeNode<T>>();
//		a_list.add(this.root);
//		TreeNode<T> new_node=new TreeNode<T>(data);
//		int old_size=this.size;
//		while(node!=null) {
//			a_list.add(node);
//			TreeNode<T> left=node.getLeft();
//			TreeNode<T> right=node.getRight();
//			int cmp=node.getValue().compareTo(data);
//			if (cmp==0) break;
//			if (left==null && cmp<0) {
//				node.setLeft(new_node);
//				this.size+=1;
//				break;
//			}
//			if (right==null && cmp>0) {
//				node.setRight(new_node);
//				this.size+=1;
//				break;
//			}
//			
//			if (left!=null && cmp<0) {
//				node=left;
//			}
//			else if(right!=null && cmp>0) {
//				node=right;
//			}
//		}
//		if (old_size!=this.size) {
//			for (int x=0; x<a_list.size(); x++) {
//				TreeNode<T> nod=a_list.get(a_list.size()-x-1);
//				int num=this.balance(nod);
//				if (Math.abs(num)>=2) {
//					if (num>0) {
//						this.rotateRight(nod);
//						break;
//					}
//					else {
//						this.rotateLeft(nod);
//						break;
//					}
//				}
//			}
//			
//		}
//-------------------------
//
//				
//		if (node==null) {
//			node=new TreeNode<T>(data);
//		}
//		else if (data.compareTo(node.getValue())<0) {
//			node=this.insertAux(node.getLeft(), data);
//			if (node.getLeft().getHeight()-node.getRight().getHeight()==2) {
//				if (data.compareTo(node.getLeft().getValue())<0){
//					this.rotateLeft(node);
//				}
//				else {
//					TreeNode<T> x=this.rotateLeft(node);
//					this.rotateLeft(x);
//				}
//			}
//		}
//		else if (data.compareTo(node.getValue())>0) {
//			node=this.insertAux(node.getRight(), data);
//			if (node.getRight().getHeight()-node.getLeft().getHeight()==2) {
//				if (data.compareTo(node.getRight().getValue())>0) {
//					this.rotateRight(node);
//				}
//				else {
//					TreeNode<T> y=this.rotateRight(node);
//					this.rotateRight(y);
//				}
//			}
//		}
//
//		
//		
//		

		return node;
	}

	/**
	 * Auxiliary method for {@code valid}. Determines if a subtree based on node is
	 * a valid subtree. An AVL must meet the BST validation conditions, and
	 * additionally be balanced in all its subtrees - i.e. the difference in height
	 * between any two children must be no greater than 1.
	 *
	 * @param node The root of the subtree to test for validity.
	 * @return true if the subtree base on node is valid, false otherwise.
	 */
	@Override
	protected boolean isValidAux(final TreeNode<T> node, boolean valid, TreeNode<T> min_node, TreeNode<T> max_node) {
		boolean validity = false;
		if (node.getValue().compareTo(node.getLeft().getValue()) < 0) {
			if (node.getValue().compareTo(node.getRight().getValue()) > 0) {
				validity = true;
			}
		}
		return validity;
	}

	protected boolean isValid(final TreeNode<T> node) {
		TreeNode<T> min_node = null;
		TreeNode<T> max_node = null;
		TreeNode<T> current = this.root;
		while (current != null) {
			if (current.getLeft().getValue() != null) {
				current = current.getLeft();
			} else {
				min_node = current;
				break;
			}
		}
		current = this.root;
		while (current != null) {
			if (current.getRight().getValue() != null) {
				current = current.getRight();
			} else {
				max_node = current;
				break;
			}
		}
		boolean valid = false;
		boolean validity = this.isValidAux(this.root, valid, min_node, max_node);
		return validity;
	}

	/**
	 * Determines whether two AVLs are identical.
	 *
	 * @param target The AVL to compare this AVL against.
	 * @return true if this AVL and target contain nodes that match in position,
	 *         value, count, and height, false otherwise.
	 */
	public boolean equals(final AVL<T> target) {
		return super.equals(target);
	}

}
