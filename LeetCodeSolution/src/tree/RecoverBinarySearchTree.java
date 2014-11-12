package tree;

import java.util.ArrayList;
import java.util.List;

public class RecoverBinarySearchTree {
	// 1. inorder traversal is in sequence
	// case1: swap neighbor, 123-> 132, one misplace
	// case2: 12345 -> 15342, two misplace
	public void recoverTree(TreeNode root) {
		if (root == null)
			return;
		List<TreeNode> inOrderList = new ArrayList<TreeNode>();
		recoverTreeHelper(root, inOrderList);
		TreeNode pre = null, next = null; // first misplace
		TreeNode pre2 = null, next2 = null; // second misplace
		for (int i = 1; i < inOrderList.size(); i++) {
			if (inOrderList.get(i).val < inOrderList.get(i - 1).val
					&& pre == null) {
				pre = inOrderList.get(i - 1);
				next = inOrderList.get(i);
			}
			if (inOrderList.get(i).val < inOrderList.get(i - 1).val
					&& pre != null) {
				pre2 = inOrderList.get(i - 1);
				next2 = inOrderList.get(i);
			}
		}
		// only one misplace
		if (next2 != null) {
			int temp = pre.val;
			pre.val = next.val;
			next.val = temp;
			return;
		}
		// two misplaces
		else {
			int temp = pre.val;
			pre.val = next2.val;
			next2.val = temp;
			return;
		}
	}

	// inorder traversal
	public void recoverTreeHelper(TreeNode root, List<TreeNode> inOrderList) {
		if (root == null)
			return;
		// if (root.left != null) { //!!no need
		recoverTreeHelper(root.left, inOrderList);
		inOrderList.add(root);
		recoverTreeHelper(root.right, inOrderList);
	}

	/********** method2 ********/
	// same idea, save more space
	public void recoverTree1(TreeNode root) {
		if (root == null)
			return;
		ArrayList<TreeNode> pre = new ArrayList<TreeNode>();
		pre.add(null);
		ArrayList<TreeNode> res = new ArrayList<TreeNode>();
		helper(root, pre, res);
		if (res.size() > 0) {
			int temp = res.get(0).val;
			res.get(0).val = res.get(1).val;
			res.get(1).val = temp;
		}
	}

	private void helper(TreeNode root, ArrayList<TreeNode> pre,
			ArrayList<TreeNode> res) {
		if (root == null) {
			return;
		}
		helper(root.left, pre, res);
		if (pre.get(0) != null && pre.get(0).val > root.val) {
			if (res.size() == 0) {
				// use previous saved node
				res.add(pre.get(0)); // pre
				res.add(root); // next
			} else {
				res.set(1, root); // next2
			}
		}
		pre.set(0, root);
		helper(root.right, pre, res);
	}

	public static void main(String[] args) {
		RecoverBinarySearchTree rbs = new RecoverBinarySearchTree();
	}
}
