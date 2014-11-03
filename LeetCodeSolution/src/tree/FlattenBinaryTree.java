package tree;

import java.util.ArrayList;
import java.util.List;

public class FlattenBinaryTree {
	public List<TreeNode> list;

	public FlattenBinaryTree() {
		list = new ArrayList<TreeNode>();
	}

	/************* method1 ************/
	/**
	 * add preorder to list of TreeNode, link each node as right, set left to
	 * null; cheating: not in place
	 * 
	 * @param root
	 */
	public void flatten(TreeNode root) {
		list = new ArrayList<TreeNode>();
		preorderTraversal(root);
		System.out.println(list);
		if (list.size() == 0)
			return;
		root.val = list.get(0).val;
		TreeNode pointer = root;
		for (int i = 1; i < list.size(); i++) {
			if (list.get(i) != null) {
				pointer.left = null;
				pointer.right = list.get(i);
			}
		}
		pointer.left = null;
		pointer.right = null;
	}

	public List<TreeNode> preorderTraversal(TreeNode root) {
		if (root == null)
			return list;
		list.add(root);
		if (root.left != null) {
			preorderTraversal(root.left);
		}
		if (root.right != null) {
			preorderTraversal(root.right);
		}
		return list;
	}

	/**************** method2 **************/

	private TreeNode prevNode = null;

	/**
	 * better, save space, in place; each flatten of root link prevNode to
	 * current Node
	 * 
	 * @param args
	 */
	public void flatten1(TreeNode root) {
		if (root == null)
			return;
		if (prevNode != null) {
			prevNode.right = root;
			prevNode.left = null;
		}

		prevNode = root;
		TreeNode savedRight = root.right;

		flatten(root.left);
		// above call will change current root's right;
		// so use savedRight instead of root.right
		flatten(savedRight);
	}

	/*************** method3 **************/
	// 1 put right tree to right most of left tree (result same in preorder, and
	// now right is null);
	// 2.change the left root, as right of root(now left is null)
	// 3. iterate root.right, until it's null
	public void flatten3(TreeNode root) {
		if (root == null)
			return;
		while (root != null) {
			TreeNode rightMostOfLeft = root.left;
			if (rightMostOfLeft != null) {
				while (rightMostOfLeft.right != null) {
					rightMostOfLeft = rightMostOfLeft.right;
				}
				rightMostOfLeft.right = root.right;
				root.right = root.left;
				root.left = null;
			}
			root = root.right;
		}
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
		FlattenBinaryTree pt = new FlattenBinaryTree();
		pt.flatten(root);

	}
}
