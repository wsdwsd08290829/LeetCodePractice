package basic.tree;
/*
 * methods:  
 * preorder, inorder, postorder
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderTraversal {
	int pathsum = 0;
	public List<TreeNode> list;
	public OrderTraversal(){
		list  = new ArrayList<TreeNode>();
	}
	public void inorder(TreeNode root){
		if(root==null)return;
		inorder(root.left);
		list.add(root);
		inorder(root.right);
	}
	public void preorder(TreeNode root){
		if(root==null)return;
		list.add(root);
		preorder(root.left);
		preorder(root.right);
	}
	public void postorder(TreeNode root){
		if(root==null)return;
		postorder(root.left);
		postorder(root.right);
		list.add(root);
	}
	public void bfs(TreeNode root){
		LinkedList<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		while(q.size()>0){
			TreeNode n =  q.remove();
			//visit node here(callback function)
			list.add(n);
			//end visiting
			if(n.left!=null)q.add(n.left);
			if(n.right!=null)q.add(n.right);
		}
	}
	public void dfs(TreeNode root){
		LinkedList<TreeNode> stack = new LinkedList<TreeNode>();
		stack.add(root);
		while(stack.size()>0){
			TreeNode n =  stack.removeLast();
			//visit node here(callback function)
			list.add(n);
			//end visiting
			if(n.left!=null)stack.add(n.left);
			if(n.right!=null)stack.add(n.right);
		}
	}
	public void dfsRecursive(TreeNode root){
		
			if(root==null)return;
			//visit node here(callback function)
			list.add(root);
			//end visiting
			if(root.left!=null)dfsRecursive(root.left);
			if(root.right!=null)dfsRecursive(root.right);
		
	}
	public static void treeStructure(TreeNode root){
		if(root==null)return;
		//list.add(root);
		System.out.print(root);
		if(root.left == null && root.right==null)return ;
		System.out.print("(");
		treeStructure(root.left);
		System.out.print(",");
		treeStructure(root.right);
		System.out.print(")");
	}
	public static void main(String[] args) {
		OrderTraversal ot = new OrderTraversal();
		ot.inorder(TreeNode.getTestTree());
		System.out.println("inorder: " + ot.list);
		
		ot = new OrderTraversal();
		ot.preorder(TreeNode.getTestTree());
		System.out.println("preorder: " + ot.list);
		
		ot = new OrderTraversal();
		ot.postorder(TreeNode.getTestTree());
		System.out.println("postorder: " + ot.list);
		
		ot = new OrderTraversal();
		ot.bfs(TreeNode.getTestTree());
		System.out.println("bfs: " + ot.list);
		
		ot = new OrderTraversal();
		ot.dfs(TreeNode.getTestTree());
		System.out.println("dfs: " + ot.list);
		
		ot = new OrderTraversal();
		ot.dfsRecursive(TreeNode.getTestTree());
		System.out.println("dfsRecursive: " + ot.list);
		
		ot = new OrderTraversal();
		ot.treeStructure(TreeNode.getTestTree());
		//System.out.println("dfsRecursive: " + ot.list);
		/**
		 * 	[2, 3, 5, 9, 10] in
		 *	[5, 3, 2, 9, 10] pre
		 *	[2, 3, 10, 9, 5] post
		 */
	}
}
