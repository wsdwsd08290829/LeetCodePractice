package tree;

import java.util.ArrayList;
import java.util.List;

public class PathSum {
	// lazy check
	public boolean hasPathSum(TreeNode root, int sum) {
		// special init case
		if (root == null)
			return false;
		// true when reach leaf and leaf == sum
		if (root.val == sum && root.left == null && root.right == null)
			return true;
		return hasPathSum(root.left, sum - root.val)
				|| hasPathSum(root.right, sum - root.val);
	}

	// wrong answer, cause if init root=null, sum == 0; below will return true;
	// expected false;
	public boolean hasPathSum1(TreeNode root, int sum) {
		if (root == null && sum == 0)
			return true;
		if (root == null || sum <= 0)
			return false;
		return hasPathSum(root.left, sum - root.val)
				|| hasPathSum(root.right, sum - root.val);
	}

	/************** problem 2: return all possible solutions **************/
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null)
			return result;

		ArrayList<Integer> temp = new ArrayList<Integer>();
		pathSumHelper(root, sum, result, temp);
		return result;
	}

	// return check for leaf=> eager check
	public void pathSumHelper(TreeNode root, int sum,
			ArrayList<List<Integer>> result, ArrayList<Integer> temp) {
		// special init case
		// true when reach leaf and leaf == sum
		if (root.val == sum && root.left == null && root.right == null) {
			temp.add(root.val);
			ArrayList<Integer> list = new ArrayList<Integer>(temp);
			result.add(list);
			temp.remove(temp.size() - 1);
			return;
		}
		// if check leaf(not null below leaf), need to check left/right first
		if (root.left != null) {
			temp.add(root.val);
			pathSumHelper(root.left, sum - root.val, result, temp);
			temp.remove(temp.size() - 1);
		}
		if (root.right != null) {
			temp.add(root.val);
			pathSumHelper(root.right, sum - root.val, result, temp);
			temp.remove(temp.size() - 1);
		}
	}

	// wrong answer: duplicate result for each leaf
	// Input: {1}, 1
	// Output: [[1],[1]]
	// Expected: [[1]]
	public void pathSumHelper1(TreeNode root, int sum,
			ArrayList<List<Integer>> result, ArrayList<Integer> temp) {
		// special init case
		// true when reach leaf and leaf == sum
		// cannot check null
		if (root == null && sum == 0) {
			ArrayList<Integer> list = new ArrayList<Integer>(temp);
			result.add(list);
		}
		if (root == null)
			return;
		temp.add(root.val);
		pathSumHelper(root.left, sum - root.val, result, temp);
		temp.remove(temp.size() - 1);

		temp.add(root.val);
		pathSumHelper(root.right, sum - root.val, result, temp);
		temp.remove(temp.size() - 1);

	}
}
