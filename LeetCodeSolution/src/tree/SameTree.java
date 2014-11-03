package tree;

public class SameTree {
	public boolean isSameTree(TreeNode leftTree, TreeNode rightTree) {
		if (leftTree == null && rightTree == null)
			return true;
		if (leftTree == null || rightTree == null)
			return false;
		if (leftTree.val == rightTree.val) {
			if (isLeaf(leftTree) && isLeaf(rightTree)) {
				return true;
			} else {
				return isSameTree(leftTree.left, rightTree.left)
						&& isSameTree(leftTree.right, rightTree.right);
			}
		} else {
			return false;
		}
	}

	public boolean isLeaf(TreeNode n) {
		return n.left == null && n.right == null;
	}
}
