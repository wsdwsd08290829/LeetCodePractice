package tree;

public class BalancedBinaryTree {
	/**
	 * definition by LeetCode: height-balanced binary tree is defined as a
	 * binary tree in which the depth of the two subtrees of every node never
	 * differ by more than 1.
	 * 
	 * @param root
	 * @return
	 */
	public boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;
		if (Math.abs(maxDepth(root.left) - maxDepth(root.right)) > 1)
			return false;
		return isBalanced(root.left) && isBalanced(root.right);
	}

	// another def: maxDep-minDep>=2;
	public boolean isBalanced1(TreeNode root) {
		if (maxDepth(root) - minDepth(root) >= 2)
			return false;
		return true;
	}

	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		return Math.min(minDepth(root.left) + 1, minDepth(root.right) + 1);
	}

	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
	}
}
