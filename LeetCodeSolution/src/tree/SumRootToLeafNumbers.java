package tree;

public class SumRootToLeafNumbers {
	int total = 0;

	public int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;
		int pathSum = 0;
		sumNumbersHelper(root, pathSum);
		return total;
	}

	// recursive calculate path sum of each path, add to total when reach leaf
	public void sumNumbersHelper(TreeNode root, int pathSum) {
		pathSum = pathSum * 10 + root.val;
		// if reach leaf add to total;
		if (root.left == null && root.right == null) {
			total += pathSum;
		}
		if (root.left != null) {
			sumNumbersHelper(root.left, pathSum);
		}
		if (root.right != null) {
			sumNumbersHelper(root.right, pathSum);
		}
	}

	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(0);
		n1.left = n2;
		SumRootToLeafNumbers sn = new SumRootToLeafNumbers();
		System.out.println(sn.sumNumbers(n1));
	}
}
