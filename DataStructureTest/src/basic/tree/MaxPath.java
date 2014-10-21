package basic.tree;

public class MaxPath {
	
/*		public int maxPathSum(TreeNode root) {
			int max[] = new int[1]; 
			max[0] = Integer.MIN_VALUE;
			calculateSum(root, max);
			return max[0];
		}
	 
		public int calculateSum(TreeNode root, int[] max) {
			if (root == null)
				return 0;
	 
			int left = calculateSum(root.left, max);
			int right = calculateSum(root.right, max);
	 
			int current = Math.max(root.value, Math.max(root.value + left, root.value + right));
	 
			max[0] = Math.max(max[0], Math.max(current, left + root.value + right));
	 
			return current;
		}*/
	int max; 
	 
	public int maxPathSum(TreeNode root) {
		max = (root == null) ? 0 : root.value;
		System.out.println(max);
		findMax(root);
		return max;
	}
 
	public int findMax(TreeNode node) {
		if (node == null)
			return 0;
 
		// recursively get sum of left and right path
		int left = (findMax(node.left));
		int right = (findMax(node.right));
 
		//update maximum here
		max = Math.max(node.value + left + right, max);
		// return sum of largest path of current node
		return node.value + Math.max(left, right);
	}
		public static void main(String[] args) {
			MaxPath mp = new MaxPath();
			OrderTraversal.treeStructure(TreeNode.getTestTree());
			System.out.println(mp.maxPathSum(TreeNode.getTestTree()));
		}
}
