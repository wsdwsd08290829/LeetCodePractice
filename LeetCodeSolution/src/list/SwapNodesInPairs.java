package list;

public class SwapNodesInPairs {
	public ListNode swapPairs(ListNode head) {
		if (head == null)
			return null;
		ListNode helper = new ListNode(0);
		helper.next = head;
		ListNode pre = helper;
		ListNode cur = head;
		while (cur != null && cur.next != null) { // !! pair exist
			ListNode temp = cur.next.next;
			pre.next = cur.next;
			cur.next.next = cur;
			cur.next = temp;
			pre = cur;
			cur = temp;
		}
		return helper.next;
	}
}
