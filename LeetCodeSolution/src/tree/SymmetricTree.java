package tree;

public class SymmetricTree {
	public boolean isSymmetric(TreeNode root) {
		return isTwoTreeSymmetric(root, root);
	}

	public boolean isTwoTreeSymmetric(TreeNode leftTree, TreeNode rightTree) {
		if (leftTree == null && rightTree == null)
			return true;
		if (leftTree == null || rightTree == null)
			return false;
		if (leftTree.val == rightTree.val) {
			if (isLeaf(leftTree) && isLeaf(rightTree)) {
				return true;
			} else {
				return isTwoTreeSymmetric(leftTree.left, rightTree.right)
						&& isTwoTreeSymmetric(leftTree.right, rightTree.left);
			}
		} else {
			return false;
		}
	}

	public boolean isLeaf(TreeNode n) {
		return n.left == null && n.right == null;
	}
}
