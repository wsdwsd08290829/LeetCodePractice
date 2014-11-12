package tree;

import java.util.ArrayList;
import java.util.List;

public class UniqueBinarySearchTree {
	/****** problem1 **********/
	// O(n)
	// dynamic programming
	// katalan number, see GenerateParenthses
	// http://zh.wikipedia.org/wiki/%E5%8D%A1%E5%A1%94%E5%85%B0%E6%95%B0
	public int numTrees(int n) {
		if (n <= 0)
			return 0;
		// # of trees of node n
		int[] res = new int[n + 1];
		res[0] = 1;
		res[1] = 1;
		for (int i = 2; i <= n; i++) { // i nodes
			for (int j = 0; j < i; j++) {
				// j is # of nodes in left sub tree,
				// i-j-1 is # of nodes in right sub tree
				res[i] += res[j] * res[i - j - 1];
			}
		}
		return res[n];
	}

	/********* problem2 find all such trees ***********/
	// for each node as root -> find left trees and right trees (recursive in
	// loop)
	// connect them together, (# left) * (# of right)
	public List<TreeNode> generateTrees(int n) {
		// return list of trees(root) use value from left to right as root
		return generateTreesHelper(1, n);
	}

	private List<TreeNode> generateTreesHelper(int left, int right) {
		ArrayList<TreeNode> res = new ArrayList<TreeNode>();
		if (left > right) {
			res.add(null);
			return res;
		}
		for (int i = left; i <= right; i++) {
			// !! left tree < i (implicitly satisfy binary search tree require)
			List<TreeNode> leftTrees = generateTreesHelper(left, i - 1);
			// !!right tree > i
			List<TreeNode> rightTrees = generateTreesHelper(i + 1, right);
			for (int j = 0; j < leftTrees.size(); j++) {
				for (int k = 0; k < rightTrees.size(); k++) {
					TreeNode root = new TreeNode(i);
					root.left = leftTrees.get(j);
					root.right = rightTrees.get(k);
					res.add(root); // each time a !!new node is added
				}
			}
		}
		return res;
	}
}
