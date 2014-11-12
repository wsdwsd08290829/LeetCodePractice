package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PreorderTraversal {
	public static List<Integer> list = new ArrayList<Integer>();

	/**
	 * recursive
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal(TreeNode root) {
		if (root == null)
			return list;
		list.add(root.val);
		if (root.left != null) {
			preorderTraversal(root.left);
		}
		if (root.right != null) {
			preorderTraversal(root.right);
		}
		return list;
	}

	/**
	 * iteration visit node first(add to list) process left using queue process
	 * right using stack
	 * 
	 * @param root
	 * @return
	 */
	public List<Integer> preorderTraversal1(TreeNode root) {
		if (root == null)
			return list;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		queue.add(root);
		while (queue.size() > 0 || stack.size() > 0) {
			// System.out.println(queue);
			// System.out.println(stack);
			// System.out.println("---");
			if (queue.size() > 0) {
				TreeNode tn = queue.remove();
				list.add(tn.val);
				if (tn.left != null) {
					queue.add(tn.left);
				}
				if (tn.right != null) {
					stack.add(tn.right);
				}
			} else if (stack.size() > 0) {
				TreeNode tn = stack.pop();
				list.add(tn.val);
				if (tn.left != null) {
					queue.add(tn.left);
				}
				if (tn.right != null) {
					stack.add(tn.right);
				}
			}
		}
		return list;
	}

	/******** method3 ********/
	// http://blog.csdn.net/linhuanmars/article/details/21428647
	// add when push, do not in pop
	public ArrayList<Integer> preorderTraversal2(TreeNode root) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (root == null)
			return res;
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		while (root != null || !stack.isEmpty()) {
			if (root != null) {
				stack.push(root);
				res.add(root.val);
				root = root.left;
			} else {
				root = stack.pop();
				root = root.right;
			}
		}
		return res;
	}

	/********* method4 **********/
	// http://blog.csdn.net/linhuanmars/article/details/21428647
	// constance space, Morris Traversal
	// better:
	// http://www.cnblogs.com/AnnieKim/archive/2013/06/15/MorrisTraversal.html
	public ArrayList<Integer> preorderTraversal3(TreeNode root) {
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
					res.add(cur.val); // only difference
					cur = cur.left;
				} else {
					temp.right = null;
					cur = cur.right;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		TreeNode n3 = new TreeNode(4);
		root.left = n1;
		root.right = n2;
		n1.left = n3;
		// System.out.println(preorderTraversal(root));
		PreorderTraversal pt = new PreorderTraversal();
		System.out.println(pt.preorderTraversal1(root));

	}
}
