package basic.tree;

import basic.list.ListNode;

public class ConstructTree {
	public static TreeNode constructTree(int[] inorder, int[] postorder){
		return constructTree(inorder, 0, inorder.length-1, 
					postorder, 0, postorder.length-1);			
	
	}
	private static TreeNode constructTree(int[] inorder, int inorderStart, int inorderEnd,
			int[] postorder, int postorderStart, int postorderEnd) {
		if(inorderStart>inorderEnd || postorderStart >postorderEnd)return null;
			int rootVal = postorder[postorderEnd];
			int rootPos = inorderStart;
			while(inorder[rootPos] != rootVal){
				rootPos++;
			}
			
			TreeNode root = new TreeNode(rootVal);
		root.left = constructTree(inorder, inorderStart, rootPos-1, 
									postorder, postorderStart, postorderStart+((rootPos-1)-inorderStart+1)-1);
			root.right = constructTree(inorder, rootPos+1, inorderEnd, 
					postorder,  postorderStart+((rootPos-1)-inorderStart+1)-1+1, postorderEnd-1);
		
		
			return root;
	}
	
	public static TreeNode constructBSTFromOrderedArray(int[] arr){
		int start = 0, end = arr.length-1;
		return constructFromOrderedArray(arr, start, end);
	}
	private static TreeNode constructFromOrderedArray(int[] arr, int start,
			int end) {
		if(start>end)return null;
		int mid = (int) Math.ceil((double)(start+end)/(double)2);
		TreeNode root = new TreeNode(arr[mid]);
		root.left = constructFromOrderedArray(arr, start, mid-1);
		root.right = constructFromOrderedArray(arr, mid+1, end);
		return root;
	}
	static ListNode h;

	public static TreeNode constructBSTFromOrderedList(ListNode listNode){
		if (listNode == null)
			return null;
		int len = 0;
		h = listNode;
		ListNode p = listNode;
		while(p!=null){
			p=p.next; len++;
		}
	
		return constructBSTFromOrderedList(0, len-1);
		
	}
	
	//bottom up 
	private static TreeNode constructBSTFromOrderedList(int start,
			int end) {
		if(start>end)return null;
		//if(listNode ==null)return null;
		int mid = (int) Math.ceil((double)(start+end)/(double)2);
	
		TreeNode left = constructBSTFromOrderedList(start, mid-1);
		TreeNode root = new TreeNode(h.val);
		h = h.next;
		TreeNode right = constructBSTFromOrderedList(mid+1, end);
		root.left = left;
		root.right = right;
		return root;
		
	}
	public static void main(String[] args) {
		//test1
		int[] inorder= { 4, 2, 5, 1,  6, 7, 3, 8};
		int[] postorder ={ 4, 5, 2 , 6, 7, 8, 3 ,1};
		OrderTraversal ot = new OrderTraversal();
		ot.inorder(constructTree(inorder, postorder));
		System.out.println(ot.list);
		
		ot = new OrderTraversal();
		ot.bfs(constructTree(inorder, postorder));
		System.out.println(ot.list);
		 //test2
		int[] arr = { 1,2,3,4,5,6};
		OrderTraversal.treeStructure(constructBSTFromOrderedArray(arr));
		 //test3
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		ListNode n5 = new ListNode(5);
		n1.next=n2; n2.next=n3;n3.next=n4;n4.next=n5;
		System.out.println(n1);
		OrderTraversal.treeStructure(constructBSTFromOrderedList(n1));
	
	
		//here h is null because pointer moved to last in constrcutBST..
		System.out.println(ConstructTree.h);
		
	}
}
