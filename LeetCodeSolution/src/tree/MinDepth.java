package tree;

public class MinDepth {
	public int minDepth(TreeNode root) {
		if (root == null)
			return 0;
		if (root.left == null && root.right == null)
			return 1;
		if (root.left == null)
			return minDepth(root.right) + 1;
		if (root.right == null)
			return minDepth(root.left) + 1;
		return Math.min(minDepth(root.left) + 1, minDepth(root.right) + 1);
	}

	// wrong for main test for leet code, expected 2, not 1(if use method below)
	public int minDepth1(TreeNode root) {
		if (root == null)
			return 0;
		return Math.min(minDepth(root.left) + 1, minDepth(root.right) + 1);
	}

	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		n1.left = n2;
		System.out.println(new MinDepth().minDepth(n1));
	}
}
