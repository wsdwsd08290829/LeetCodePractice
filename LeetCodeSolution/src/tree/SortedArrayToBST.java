package tree;

public class SortedArrayToBST {
	public TreeNode sortedArrayToBST(int[] num) {
		if (num == null)
			return null;
		return sortedArrayToBSTHelper(num, 0, num.length - 1);
	}

	private TreeNode sortedArrayToBSTHelper(int[] num, int start, int end) {
		if (start > end)
			return null;
		int mid = start + (end - start) / 2;
		TreeNode root = new TreeNode(num[mid]);
		root.left = sortedArrayToBSTHelper(num, start, mid - 1);
		root.right = sortedArrayToBSTHelper(num, mid + 1, end);
		return root;
	}
}
