package basic.tree;
/*
 * methods:  
 * isBalance, maxHeight/minHeight
 * isBST
 */
public class ValidateBST {
	public static boolean isBST(TreeNode root){
		int max = Integer.MAX_VALUE;
		int min = Integer.MIN_VALUE;
		return isBST(root, min, max);
	}
	private static boolean isBST(TreeNode root, int min, int max) {
		if(root==null) return true;
		if(root.value<min || root.value> max) return false;
		return isBST(root.left, min, root.value) && isBST(root.right, root.value, max);
	}
	public static boolean isBalanced(TreeNode root){
		System.out.println("maxHeight: " + maxHeight(root));
		System.out.println("minHeight: " + minHeight(root));
		return maxHeight(root)-minHeight(root)>1?false:true;
	}
	private static int minHeight(TreeNode root) {
		if(root==null)return -1;
		//if(root.left==null || root.right==null)return 0;
		return 1 + Math.min(minHeight(root.left), minHeight(root.right));
	}
	private static int maxHeight(TreeNode root) {
		if(root==null)return -1;
		//if(root.left==null && root.right==null)return 0;
		return Math.max(1+maxHeight(root.left),1+maxHeight(root.right));
	}
	public static void main(String[] args) {
		TreeNode root = TreeNode.getTestTree();
		System.out.println(isBST(root));
		System.out.println(root);
		
		System.out.println(isBalanced(root));
	}
}
