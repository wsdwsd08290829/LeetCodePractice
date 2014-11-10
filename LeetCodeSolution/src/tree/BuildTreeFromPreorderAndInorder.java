package tree;

/**
 * may assume that duplicates do not exist in the tree.
 * 
 * @author sidawang
 * 
 */
public class BuildTreeFromPreorderAndInorder {
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (inorder == null || preorder == null
				|| inorder.length != preorder.length)
			return null;
		return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0,
				inorder.length - 1);
	}

	// pre: 124537; in 425137; level 12345#7
	private TreeNode buildTreeHelper(int[] preorder, int preStart, int preEnd,
			int[] inorder, int inStart, int inEnd) {
		if (inStart > inEnd)
			return null;// !!

		int rootIndex = preStart;
		int inIndex = -1;
		TreeNode root = new TreeNode(preorder[rootIndex]);

		// find inorder index
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == preorder[rootIndex])
				inIndex = i;
		}
		// !!! index calculation
		root.left = buildTreeHelper(preorder, rootIndex + 1, rootIndex + 1
				+ inIndex - inStart, inorder, inStart, inIndex - 1);
		root.right = buildTreeHelper(preorder, rootIndex + inIndex - inStart
				+ 1, rootIndex + inIndex - inStart + (inEnd - inIndex),
				inorder, inIndex + 1, inEnd);
		return root;
	}
}
