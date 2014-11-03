package tree;

public class isValidBinarySearchTree {
	/**
	 * recursion; The left subtree of a node contains only nodes with keys less
	 * than the node's key. The right subtree of a node contains only nodes with
	 * keys greater than the node's key.Both the left and right subtrees must
	 * also be binary search trees.
	 * 
	 * @param root
	 * @return
	 */
	public boolean isValidBST(TreeNode root) {
		return isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	public boolean isBST(TreeNode root, int min, int max) {
		if (root == null)
			return true;
		if (root.val <= min || root.val >= max)
			return false;
		// left tree: lower the max to current root value
		// right tree: lower the max to current root value
		return isBST(root.left, min, root.val)
				&& isBST(root.right, root.val, max);
	}
}
