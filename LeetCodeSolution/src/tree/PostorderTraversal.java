package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
/**
 * post order visit a tree
 * implement 3 methods to acommplish the task. See each method
 * @author sidawang
 *
 */
public class PostorderTraversal {
	public static List<Integer> valList = new ArrayList<Integer>();

	/**
	 *  use class field
	 * @param root
	 * @return
	 */
	public static List<Integer> postorderTraversal1(TreeNode root) {
		if (root == null)
			return valList;
		if (root.left != null) {
			postorderTraversal1(root.left);
		}
		if (root.right != null) {
			postorderTraversal1(root.right);
		}
		valList.add(root.val);
		return valList;
	}

	/**
	 *  use local field
	 */
	public static List<Integer> postorderTraversal2(TreeNode root) {
		List<Integer> valList = new ArrayList<Integer>();
		if (root == null)
			return valList;
		if (root.left != null) {
			valList.addAll(postorderTraversal2(root.left));
		}
		if (root.right != null) {
			valList.addAll(postorderTraversal2(root.right));
		}
		valList.add(root.val);
		return valList;
	}

	/**
	 *  use stack, iteration
	 *  better draw on draft for simple example
	 * @param root
	 * @return
	 */
	
	public static List<Integer> postorderTraversal3(TreeNode root) {

		List<Integer> list = new ArrayList<Integer>();
		if (root == null)
			return list;
		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		// save all internal node popped/peeked from stack1 for the first time
		Stack<TreeNode> stack2 = new Stack<TreeNode>();
		stack1.push(root);
		while (stack1.size() > 0) {
			// only remove if all its child is visited, so just peek
			TreeNode node = stack1.peek();
			// if is leaf, just add to list. and pop();
			if (node.left == null && node.right == null) {
				list.add(node.val);
				stack1.pop();
			} else {
				// first time visit the node, need to visit all children first,
				// save this node to stack2 to remember it for second visit
				if (stack2.size() == 0
						|| (stack2.size() > 0 && stack2.peek() != node)) {
					stack2.push(node);
					// visit node first time
					if (node.right != null) {
						stack1.push(node.right);
					}
					if (node.left != null) {
						stack1.push(node.left);
					}
				}
				// visit node second time, all its child are already visited
				else {
					list.add(node.val);
					stack2.pop();
					stack1.pop();
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		root.left = n1;
		n1.right = n2;
		System.out.println(postorderTraversal1(root));
		System.out.println(postorderTraversal3(root));
	}
}
