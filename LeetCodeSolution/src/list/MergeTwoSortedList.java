package list;

public class MergeTwoSortedList {
	/********** method1 iteration *********/
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null)
			return null;
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;

		// l1 always point to smaller node to be fix next
		ListNode temp = l2;
		if (l2.val < l1.val) {
			l2 = l1;
			l1 = temp;
		}

		ListNode head = l1;
		while (l1 != null && l2 != null) {
			if (l1.next != null && l1.next.val <= l2.val) {
				l1 = l1.next; // use l1 right
			} else { // point to the other list
				ListNode templ1 = l1.next;
				l1.next = l2;
				l1 = l2;
				l2 = templ1;
			}
		}
		return head;
	}

	/************* method 2: recursion ***********/
	public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null)
			return null;
		if (l1 == null)
			return l2;
		if (l2 == null)
			return l1;
		if (l1.val < l2.val) {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
	}

	/********** method3: maintain one list l1, insert l2 if necessary *********/
	// copy from: http://blog.csdn.net/linhuanmars/article/details/19712593
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		ListNode helper = new ListNode(0);
		ListNode pre = helper;
		helper.next = l1;
		while (l1 != null && l2 != null) {
			// insert l2 before l1, pre point to inserted node
			if (l1.val > l2.val) {
				ListNode next = l2.next;
				l2.next = pre.next;
				pre.next = l2;
				l2 = next;
			} else {
				l1 = l1.next;
			}
			// go to next to be fix
			pre = pre.next;

		}
		if (l2 != null) {
			pre.next = l2;
		}
		return helper.next;
	}
}
