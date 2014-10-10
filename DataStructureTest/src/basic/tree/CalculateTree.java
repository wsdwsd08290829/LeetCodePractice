package basic.tree;

import java.util.LinkedList;

public class CalculateTree {
	public static boolean hasPathSum(TreeNode root, int sum){
		if(root == null)return false;
		if(sum == root.value && root.left==null && root.right == null){
			return true;
		}
		return hasPathSum(root.left, sum-root.value) ||
				hasPathSum(root.right, sum-root.value);
	}	
	public static boolean hasPathSumBFS(TreeNode root, int sum){
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		LinkedList<Integer> sumVal = new LinkedList<Integer>();
		q.add(root);
		sumVal.add(sum);
		
		while(q.size()>0){
			TreeNode n =  q.remove();
			sum = sumVal.remove();
			//visit node here(callback function)
			if(0 == sum - n.value && n.left == null && n.right == null)return true;
			//end visiting
			if(n.left!=null){
				q.add(n.left);
				sumVal.add(sum-n.value);
			}
			if(n.right!=null){
				q.add(n.right);
				sumVal.add(sum-n.value);
			}
		}
		return false;
	}

	public static void main(String[] args) {
		TreeNode root = TreeNode.getTestTree();
		/*TreeNode root = new TreeNode(9);
		TreeNode n1 = new TreeNode(1);
		root.right = n1;*/
		System.out.println(hasPathSum(root, 10));
		System.out.println(hasPathSumBFS(root, 9));
		System.out.println(hasPathSumBFS(root, 24));
	}
}
