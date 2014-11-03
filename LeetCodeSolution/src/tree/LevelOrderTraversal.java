package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
	public List<List<Integer>> levelOrder(TreeNode root) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		if (root == null)
			return result;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		while (queue.size() > 0) {
			// save next level node to queue
			Queue<TreeNode> tempQ = new LinkedList<TreeNode>();

			List<Integer> tempList = new ArrayList<Integer>();
			// visit all node in current level, add child to next level
			while (queue.size() > 0) {
				TreeNode visiting = queue.remove();
				// add to list,
				tempList.add(visiting.val);
				// children to tempQ
				if (visiting.left != null) {
					tempQ.add(visiting.left);
				}
				if (visiting.right != null) {
					tempQ.add(visiting.right);
				}
			}
			queue = tempQ;

			if (tempList.size() > 0)
				result.add(tempList);
		}
		return result;
	}
}
