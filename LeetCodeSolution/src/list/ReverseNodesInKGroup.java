package list;

public class ReverseNodesInKGroup {
	/****** method1 ********/
	// if can -> extract sub list, reverse it, assemble to original list
	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null)
			return head;
		boolean canMoveK = true;
		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;

		ListNode subHead = head; // head of current k node to be reversed
		ListNode prevTail = dummyHead; // last of already finished list
		ListNode nextHead = null;
		int count = k;
		ListNode subTail = subHead;
		while (canMoveK) { // subTail != null also correct
			// set all pointers
			while (subTail != null && count > 1) {
				count--;
				subTail = subTail.next;
			}
			if (subTail == null || count != 1) {
				canMoveK = false; // not necessary
				return dummyHead.next;
			}
			// find next head, set prev end to null,
			if (subTail != null) {
				nextHead = subTail.next;
				subTail.next = null;
			}
			// reverse list, subTail become new head, subHead become new tail
			reverseList(subHead);
			// link with prev and next
			prevTail.next = subTail;
			subHead.next = nextHead;
			// update count, preTail, subHead, subTail for next loop
			count = k;
			prevTail = subHead;
			subTail = subHead.next;
			subHead = subHead.next;

		}
		return dummyHead.next;
	}

	private ListNode reverseList(ListNode head) {
		if (head == null)
			return head;
		ListNode prev = null;
		ListNode cur = head;
		while (cur.next != null) {
			ListNode temp = cur.next;
			cur.next = prev;
			prev = cur;
			cur = temp;
		}
		cur.next = prev;
		return cur;
	}

	/********* method2 *******/
	// better because, it does not break sub list as metod1
	public ListNode reverseKGroup1(ListNode head, int k) {
		if (head == null) {
			return null;
		}
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		int count = 0;
		ListNode pre = dummy;
		ListNode cur = head;
		while (cur != null) {
			count++;
			ListNode next = cur.next;
			if (count == k) {
				pre = reverse(pre, next);
				count = 0;
			}
			cur = next;
		}
		return dummy.next;
	}

	// !!! sub list is after pre and before end (end could be null)
	// !!! reverse list in place
	private ListNode reverse(ListNode pre, ListNode end) {
		if (pre == null || pre.next == null)
			return pre;
		ListNode head = pre.next;
		ListNode cur = pre.next.next;
		while (cur != end) {
			ListNode next = cur.next;
			cur.next = pre.next;
			pre.next = cur;
			cur = next;
		}
		head.next = end;
		return head; // head is end of reversed list
	}

	public static void main(String[] args) {
		ReverseNodesInKGroup rk = new ReverseNodesInKGroup();
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		n1.next = n2;
		ListNode.printListNode(rk.reverseKGroup(n1, 2));
	}
}
