package leet;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tree.TreeNode;

public class BTLevelOrderTraversalSerializedOJ {
	public static List<List<Integer>> levelOrderBottom(TreeNode root) {

		LinkedList<List<Integer>> all = new LinkedList<List<Integer>>();
		LinkedList<Integer> level = new LinkedList<Integer>();

		if (root == null)
			return all;

		// queue holds all TreeNodes in one level, tempQueue holds Nodes next
		// level
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Queue<TreeNode> tempQueue = new LinkedList<TreeNode>();

		queue.add(root);

		// traverse queue level by level
		while (!queue.isEmpty()) {
			TreeNode tn = queue.remove();
			level.add(tn.val);
			scanTreeNode(tn, tempQueue);
			// when current level is finished, save level to all,
			// create new level to hold new treeNodes
			// renew queue to next level, new tempQueue to new next level
			if (queue.isEmpty()) {
				all.addFirst(level);
				level = new LinkedList<Integer>();
				queue = tempQueue;
				tempQueue = new LinkedList<TreeNode>();
			}
		}
		return all;
	}

	private static void scanTreeNode(TreeNode tn, Queue<TreeNode> tempQueue) {
		if (tn.left != null) {
			tempQueue.add(tn.left);
		}
		if (tn.right != null) {
			tempQueue.add(tn.right);
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode l11 = new TreeNode(2);
		TreeNode l12 = new TreeNode(3);
		TreeNode l21 = new TreeNode(4);
		TreeNode l22 = new TreeNode(5);
		root.left = l11;
		root.right = l12;
		l11.left = l21;
		l11.right = l22;
		System.out.println(levelOrderBottom(root)); //[[4, 5], [2, 3], [1]]
	}
}
