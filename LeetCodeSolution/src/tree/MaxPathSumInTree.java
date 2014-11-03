package tree;

import java.util.ArrayList;

/**
 * max path of binary tree !!!!The path may start and end at any node in the
 * tree.
 * 
 * @author sidawang
 * 
 */
public class MaxPathSumInTree {
	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;
		// save max path sum for each node in path as root
		ArrayList<Integer> maxes = new ArrayList<Integer>();
		// fill maxes by visit each node and get its max down path
		maxPathHelper(root, maxes);
		int maxpathsum = Integer.MIN_VALUE;
		for (int temp : maxes) {
			if (temp > maxpathsum) {
				maxpathsum = temp;
			}
		}
		return maxpathsum;
	}

	/**
	 * do two things: 1.get max left down path, max right down path. 2. get max
	 * path that root is as root in path, save to maxes. !!! learn: common prob
	 * as template, do modification to it: maxPathHelper can use find max down
	 * path recursively as template + create max path sum and add to maxes along
	 * the way
	 * 
	 * @param root
	 * @param maxes
	 *            : max val of path pass each node as root
	 * @return max down path sum of this root
	 */
	private int maxPathHelper(TreeNode root, ArrayList<Integer> maxes) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			maxes.add(root.val);
			return root.val;
		}
		int maxLeftDown = maxPathHelper(root.left, maxes);
		int maxRightDown = maxPathHelper(root.right, maxes);

		// max down path; //root itself or root + maxLeft or root+ maxRight
		int tempMax = Math.max(root.val + maxLeftDown, root.val + maxRightDown);
		tempMax = Math.max(root.val, tempMax);
		// max down path or down->up(to root)->down
		int max = Math.max(tempMax, root.val + maxLeftDown + maxRightDown);
		// max now or just root it self

		maxes.add(max);
		return tempMax;
	}

	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(-1);
		TreeNode n3 = new TreeNode(-2);
		n1.left = n2;
		n1.right = n3;
		System.out.println(new MaxPathSumInTree().maxPathSum(n1));
	}
}
