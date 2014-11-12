package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InorderTraversal {
	/****** method1:recursion ******/
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<Integer>();
		inorderTraversalHelper(root, res);
		return res;
	}

	private void inorderTraversalHelper(TreeNode root, List<Integer> res) {
		if (root == null)
			return;
		inorderTraversalHelper(root.left, res);
		res.add(root.val);
		inorderTraversalHelper(root.right, res);
	}

	/*********** method2: iteration ***********/
	// http://blog.csdn.net/linhuanmars/article/details/20187257
	// add left as much as possible, other wise pop change to right
	// add when pop, do not in push <-> vs preorder
	public List<Integer> inorderTraversal1(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		while (root != null || !stack.isEmpty()) {
			if (root != null) {
				stack.push(root);
				root = root.left;
			} else {
				root = stack.pop();
				res.add(root.val);
				root = root.right;
			}
		}
		return res;
	}

	/********** method3 iteration ********/
	// http://blog.csdn.net/linhuanmars/article/details/20187257
	// constance space, Morris Traversal
	// better:
	// http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
	public ArrayList<Integer> inorderTraversal2(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		TreeNode cur = root;
		TreeNode temp = null;
		while (cur != null) {
			if (cur.left == null) {
				res.add(cur.val);
				cur = cur.right;
			} else {
				temp = cur.left;
				while (temp.right != null && temp.right != cur) {
					temp = temp.right;
				}
				if (temp.right == null) {
					temp.right = cur;
					cur = cur.left;
				} else {
					res.add(cur.val);
					temp.right = null;
					cur = cur.right;
				}
			}
		}
		return res;
	}
}
