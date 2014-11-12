package tree;

public class BuildTreeFromInorderAndPostorderTraversal {
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if (inorder == null || postorder == null
				|| inorder.length != postorder.length)
			return null;
		return buildTreeHelper(postorder, 0, postorder.length - 1, inorder, 0,
				inorder.length - 1);
	}

	private TreeNode buildTreeHelper(int[] postorder, int postStart,
			int postEnd, int[] inorder, int inStart, int inEnd) {
		if (inStart > inEnd)
			return null;// !!

		int rootIndex = postEnd;
		int inIndex = -1;
		TreeNode root = new TreeNode(postorder[rootIndex]);

		// find inorder index
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == postorder[rootIndex])
				inIndex = i;
		}
		// !!! index calculation
		root.right = buildTreeHelper(postorder, rootIndex - (inEnd - inIndex),
				rootIndex - 1, inorder, inIndex + 1, inEnd);
		root.left = buildTreeHelper(postorder, rootIndex - (inEnd - inIndex)
				- (rootIndex - inStart), rootIndex - (inEnd - inIndex) - 1,
				inorder, inStart, inIndex - 1);
		return root;
	}
}
