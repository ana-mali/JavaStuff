package cp213;

/**
 * Implements a Popularity Tree. Extends BST.
 *
 * @author Anastasia Malinovski
 * @author David Brown
 * @version 2021-07-05
 */
public class PopularityTree<T extends Comparable<T>> extends BST<T> {

    /**
     * Auxiliary method for {@code valid}. May force node rotation if the retrieval
     * count of the located node value is incremented.
     *
     * @param node The node to examine for key.
     * @param key  The value to search for. Count is updated to count in matching
     *             node value if key is found.
     * @return the updated node.
     */
    private TreeNode<T> retrieveAux(TreeNode<T> node, final CountedValue<T> key) {
    	
		CountedValue<T> value = null;

		while (node != null && value == null) {
			if (node.getValue().compareTo(key) > 0) {
				node = node.getLeft();
			} else if (node.getValue().compareTo(key) < 0) {
				node = node.getRight();
			} else {
				value = node.getValue();
				node.getValue().incrementCount();
			}
		}

	return node;
    }

    /**
     * Performs a left rotation around node.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateLeft(final TreeNode<T> parent) {

    	TreeNode<T> y = parent.getRight();
    	TreeNode<T> T2 = y.getLeft();

    	y.setLeft(parent);
    	parent.setRight(T2);

    	parent.updateHeight();
    	y.updateHeight();

	return y;
    }

    /**
     * Performs a right rotation around {@code node}.
     *
     * @param parent The subtree to rotate.
     * @return The new root of the subtree.
     */
    private TreeNode<T> rotateRight(final TreeNode<T> parent) {

    	TreeNode<T> x = parent.getLeft();
    	TreeNode<T> T2 = x.getRight();

    	x.setRight(parent);
    	parent.setLeft(T2);

    	parent.updateHeight();
    	x.updateHeight();

	return x;
    }

    /**
     * Replaces BST {@code insertAux} - does not increment count on repeated
     * insertion. Counts are incremented only on retrieve.
     */
    @Override
    protected TreeNode<T> insertAux(TreeNode<T> node, final CountedValue<T> data) {
    	if (node == null) {
			this.size += 1;
			node = new TreeNode<T>(data);
			node.updateHeight();
		} else if (node.getValue().compareTo(data) > 0) {
			TreeNode<T> new_node = this.insertAux(node.getLeft(), data);
			node.setLeft(new_node);
			node.updateHeight();
		} else if (node.getValue().compareTo(data) < 0) {
			TreeNode<T> new_node = this.insertAux(node.getRight(), data);
			node.setRight(new_node);
			node.updateHeight();
		}
	return node;
    }

    /**
     * Auxiliary method for {@code valid}. Determines if a subtree based on node is
     * a valid subtree. An Popularity Tree must meet the BST validation conditions,
     * and additionally the counts of any node data must be greater than or equal to
     * the counts of its children.
     *
     * @param node The root of the subtree to test for validity.
     * @return true if the subtree base on node is valid, false otherwise.
     */
    @Override
    protected boolean isValidAux(final TreeNode<T> node, boolean valid, TreeNode<T> min_node, TreeNode<T> max_node) {
    	if (node.getValue() == null) {
			valid = true;
		} else if (node.getValue().compareTo(min_node.getValue()) <= 0) {
			valid = false;
		} else if (node.getValue().compareTo(max_node.getValue()) >= 0) {
			valid = false;
		} else {
			this.isValidAux(node.getLeft(), valid, min_node, max_node);
			this.isValidAux(node.getRight(), valid, min_node, max_node);
		}

	return valid;
    }

    /**
     * Very similar to the BST retrieve, but increments the character count here
     * instead of in the insertion.
     *
     * @param key The key to search for.
     */
    @Override
    public CountedValue<T> retrieve(CountedValue<T> key) {
    	TreeNode<T> node = this.root;
    	node=this.retrieveAux(node, key);
    	CountedValue<T> value =null;
    	if (node!=null) {
    		value=node.getValue();
    	}
	return value;
    }

    /**
     * Determines whether two PopularityTrees are identical.
     *
     * @param target The PopularityTree to compare this PopularityTree against.
     * @return true if this PopularityTree and target contain nodes that match in
     *         position, value, count, and height, false otherwise.
     */
    public boolean equals(final PopularityTree<T> target) {
	return super.equals(target);
    }

}
