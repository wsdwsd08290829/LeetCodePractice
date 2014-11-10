package list;

//compare to RemoveDuplicatesFromSortedArray 
public class RemoveDuplicatesFromSortedList {
	/************ problem1 ************/
	// move both if different, move fast if same
	public ListNode deleteDuplicates(ListNode head) {
		if (head == null)
			return head;
		ListNode slow = head;
		ListNode fast = head.next;

		while (fast != null) {
			// link all different val together
			if (fast.val != slow.val) {
				slow.next = fast;
				slow = fast;
			}
			fast = fast.next;
		}

		slow.next = null;
		return head;
	}

	/************ problem2: delete all nodes that have duplicate numbers ************/
	// 1->2->3->3->4->4->5, return 1->2->5
	// usually need dummyHead when head of return list is changed
	public ListNode deleteDuplicates21(ListNode head) {
		if (head == null)
			return head;
		// !! first may not be head eg 1->1->2
		// create dummy head
		ListNode dummyHead = new ListNode(-1);
		dummyHead.next = head;
		ListNode slow = dummyHead;
		ListNode fastPrev = dummyHead;
		ListNode fast = head;

		while (fast.next != null) {
			// !! (fastPrev == dummyHead || fastPrev.val != fast.val)
			if ((fastPrev == dummyHead || fastPrev.val != fast.val)
					&& fast.val != fast.next.val) {
				slow.next = fast;
				slow = fast;
			}
			fastPrev = fast;
			fast = fast.next;
		}
		// check boundary case(start and end) if fast.next = null;
		if (fast.val != fastPrev.val) {
			slow.next = fast;
			slow = fast;
		}
		slow.next = null;
		return dummyHead.next;
	}

	/************ method2 *************/
	// copy from:http://blog.csdn.net/linhuanmars/article/details/24389429
	// more concise but harder to understand
	public ListNode deleteDuplicates22(ListNode head) {
		if (head == null)
			return head;
		ListNode helper = new ListNode(0);
		helper.next = head;
		ListNode pre = helper;
		ListNode cur = head;
		while (cur != null) {
			while (cur.next != null && pre.next.val == cur.next.val) {
				cur = cur.next;
			}
			// dummy-> 1->1->2->2->3;
			if (pre.next == cur) {
				pre = pre.next; // fix next: prev.next is not dup with is next
			} else {
				// fix prev: prev.next is not dup with its previous
				pre.next = cur.next;
			}
			cur = cur.next;
		}

		return helper.next;
	}
}
