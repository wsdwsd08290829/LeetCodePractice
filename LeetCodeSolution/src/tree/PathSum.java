package tree;

public class PathSum {
	public boolean hasPathSum(TreeNode root, int sum) {
		// special init case
		if (root == null)
			return false;
		// true when reach leaf and leaf == sum
		if (root.val == sum && root.left == null && root.right == null)
			return true;
		return hasPathSum(root.left, sum - root.val)
				|| hasPathSum(root.right, sum - root.val);
	}

	// wrong answer, cause if init root=null, sum == 0; below will return true;
	// expected false;
	public boolean hasPathSum1(TreeNode root, int sum) {
		if (root == null && sum == 0)
			return true;
		if (root == null || sum <= 0)
			return false;
		return hasPathSum(root.left, sum - root.val)
				|| hasPathSum(root.right, sum - root.val);
	}
}
