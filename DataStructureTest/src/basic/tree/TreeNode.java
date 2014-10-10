package basic.tree;

public class TreeNode {
	TreeNode left, right;
	TreeNode parent;
	int value;
	public TreeNode(int value) {
		super();
		this.value = value;
	}
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	public static TreeNode getTestTree(){
		TreeNode root = new TreeNode(-5);
		TreeNode n10 = new TreeNode(3);
		TreeNode n11 = new TreeNode(-9);
		TreeNode n20 = new TreeNode(2);
		TreeNode n23 = new TreeNode(10);
		TreeNode n21 = new TreeNode(44);
		TreeNode n22 = new TreeNode(6);
		//TreeNode n30 = new TreeNode(0);
		root.left=n10;root.right = n11;
		n10.left = n20;
		n11.right = n23;
		//n10.right=n30;
		n10.right=n21;
		n11.left=n22;
		return root;
	}
}
