package tree;

public class MaxDepth {
	/**
	 * just change all 'min' in MinDepth to 'max'
	 * 
	 * @param root
	 * @return
	 */
	public int maxDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;
		if (root.left == null)
			return maxDepth(root.right) + 1;
		if (root.right == null)
			return maxDepth(root.left) + 1;
		return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
	}
	//correct, better
	public int maxDepth1(TreeNode root) {
		if (root == null)
			return 0;
		return Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
	}
}
