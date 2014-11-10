package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeZigzagLevelOrderTraversal {
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null)
			return result;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		int level = 0;

		stack.push(root);
		while (!stack.isEmpty()) {
			// each level: scan stack, add child to tempStack, assign stack;
			List<Integer> sList = new ArrayList<Integer>();
			Stack<TreeNode> tempStack = new Stack<TreeNode>();
			while (!stack.isEmpty()) {
				TreeNode sNode = stack.pop();
				// odd level push right first
				// when visiting add to list from right to left
				if (level % 2 == 1) {
					if (sNode.right != null) {
						tempStack.push(sNode.right);
					}
					if (sNode.left != null) {
						tempStack.push(sNode.left);
					}
				} else {
					if (sNode.left != null) {
						tempStack.push(sNode.left);
					}
					if (sNode.right != null) {
						tempStack.push(sNode.right);
					}
				}
				sList.add(sNode.val);
			}
			result.add(sList);
			stack = tempStack;
			level++;
		}
		return result;
	}

	public static void main(String[] args) {
		BinaryTreeZigzagLevelOrderTraversal bz = new BinaryTreeZigzagLevelOrderTraversal();
		TreeNode root = new TreeNode(1);
		System.out.println(bz.zigzagLevelOrder(root));
	}
}
