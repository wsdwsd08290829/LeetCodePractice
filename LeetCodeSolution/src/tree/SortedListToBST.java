package tree;

import java.util.ArrayList;

import list.ListNode;

/**
 * learnt: 1. use inorder traversal to create tree; 2. use ArrayList or class
 * field as global
 * 
 * @author sidawang
 * 
 */
public class SortedListToBST {
	public TreeNode sortedListToBST(ListNode head) {
		if (head == null)
			return null;
		ListNode cur = head;
		// count length of list
		int count = 0;
		while (cur != null) {
			cur = cur.next;
			count++;
		}
		// !!set global var
		ArrayList<ListNode> list = new ArrayList<ListNode>();
		list.add(head); // !! as global

		return helper(list, 0, count - 1);
	}

	// listNode is sequenced as inorder visit the binary search tree;
	// inorder visit, inorder construct(bottom up)
	private TreeNode helper(ArrayList<ListNode> list, int l, int r) {
		if (l > r)
			return null;
		int m = (l + r) / 2;
		// construct tree from inorder list //like inorder traversal
		TreeNode left = helper(list, l, m - 1); // left
		// get(0) is the one changed below in upper stack;
		TreeNode root = new TreeNode(list.get(0).val); // root
		root.left = left;
		list.set(0, list.get(0).next); // !!! update global
		root.right = helper(list, m + 1, r); // right
		return root;
	}

	/************** method 2 *************/
	ListNode h;

	public TreeNode sortedListToBST1(ListNode listNode) {

		if (listNode == null)
			return null;
		int len = 0;
		h = listNode;
		// get length
		ListNode p = listNode;
		while (p != null) {
			p = p.next;
			len++;
		}

		return constructBSTFromOrderedList(0, len - 1);
	}

	private TreeNode constructBSTFromOrderedList(int start, int end) {
		if (start > end)
			return null;
		// if(listNode ==null)return null;
		int mid = (int) Math.ceil((double) (start + end) / (double) 2);
		// construct tree from inorder list //like inorder traversal
		TreeNode left = constructBSTFromOrderedList(start, mid - 1);
		TreeNode root = new TreeNode(h.val);
		h = h.next;
		TreeNode right = constructBSTFromOrderedList(mid + 1, end);
		root.left = left;
		root.right = right;
		return root;

	}
}
