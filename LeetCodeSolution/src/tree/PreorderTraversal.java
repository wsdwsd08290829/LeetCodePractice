package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class PreorderTraversal {
	public static List<Integer> list = new ArrayList<Integer>();

	public List<Integer> preorderTraversal(TreeNode root) {
		if (root == null)
			return list;
		list.add(root.val);
		if (root.left != null) {
			preorderTraversal(root.left);
		}
		if (root.right != null) {
			preorderTraversal(root.right);
		}
		return list;
	}

	public List<Integer> preorderTraversal1(TreeNode root) {
		if (root == null)
			return list;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		Stack<TreeNode> stack = new Stack<TreeNode>();
		queue.add(root);
		while (queue.size() > 0 || stack.size()>0){
//			System.out.println(queue);
//			System.out.println(stack);
//			System.out.println("---");
			if(queue.size()>0){
				TreeNode tn = queue.remove();
				list.add(tn.val);
				if (tn.left != null) {
					queue.add(tn.left);
				}
				if (tn.right != null) {
					stack.add(tn.right);
				}
			}else if(stack.size()>0){
				TreeNode tn = stack.pop();
				list.add(tn.val);
				if (tn.left != null) {
					queue.add(tn.left);
				}
				if (tn.right != null) {
					stack.add(tn.right);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n1 = new TreeNode(2);
		TreeNode n2 = new TreeNode(3);
		TreeNode n3 = new TreeNode(4);
		root.left = n1;
		root.right = n2;
		n1.left = n3;
		// System.out.println(preorderTraversal(root));
		PreorderTraversal pt = new PreorderTraversal();
		System.out.println(pt.preorderTraversal1(root));

	}
}
