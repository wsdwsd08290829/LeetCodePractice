package list;

public class ListNode {
	public int val;
	public ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}

	public static ListNode createTestList(int[] arr) {
		ListNode[] lnArr = new ListNode[arr.length];
		for (int i = 0; i < arr.length; i++) {
			lnArr[i] = new ListNode(arr[i]);
		}
		for (int i = 0; i < arr.length - 1; i++) {
			lnArr[i].next = lnArr[i + 1];
		}
		lnArr[lnArr.length - 1].next = null;
		return lnArr[0];
	}

	public static void printListNode(ListNode n) {
		while (n != null) {
			System.out.print(n.val + " ");
			n = n.next;
		}
		System.out.println();
	}
}
