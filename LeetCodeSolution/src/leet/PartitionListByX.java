package leet;

public class PartitionListByX {
	/**
	 * 2*O(n)
	 * 
	 * @param head
	 * @param x
	 * @return
	 */

	/******* method1 ********/
	// move larger to movingEnd
	public ListNode partition(ListNode head, int x) {
		if (head == null)
			return head;
		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;
		int len = 1;
		ListNode end = head;
		while (end.next != null) {
			len++;
			end = end.next;
		}// now end points to end;
		ListNode movingEnd = end;
		ListNode prev = dummyHead;
		ListNode cur = head;
		ListNode next = cur.next;
		// check by length, so prev, cur, next never be null
		// last elem(in original list) always in right position
		// but still need to move to end if its not the movingEnd
		while (len > 0) {
			len--;
			// no move when reach original last
			if (len == 0 && cur == movingEnd)
				break;
			// otherwise move larger to end including last elem to maintain
			// correct sequence
			if (cur.val >= x) {
				movingEnd.next = cur;
				cur.next = null;
				prev.next = next;
				cur = next;
				if (cur != null) {
					next = cur.next;
				}
				movingEnd = movingEnd.next;
			} else {
				prev = cur;
				cur = next;
				if (cur != null) {
					next = cur.next;
				}
			}
		}
		return dummyHead.next;
	}

	/******** method2 *********/
	/**
	 * O(n)
	 * 
	 * @param head
	 * @param x
	 * @return
	 */
	// better, classic two pointer problem
	// two pointer, one point to last of smaller part, one move ahead;
	// http://blog.csdn.net/linhuanmars/article/details/24446871
	// insert smaller after walker pointer
	public ListNode partition1(ListNode head, int x) {
		if (head == null)
			return null;
		ListNode helper = new ListNode(0);
		helper.next = head;
		ListNode walker = helper;
		ListNode runner = helper;
		// keep fixing r.next till r.next is null
		while (runner.next != null) {
			if (runner.next.val < x) {
				// special case: correct place from begin, move both next;
				if (walker == runner) {
					walker = walker.next;
					runner = runner.next;
					continue;
				}
				// insert smaller after runner to after walker
				ListNode temp1 = walker.next;
				ListNode temp2 = runner.next.next;
				walker.next = runner.next;
				runner.next.next = temp1;
				// update runner.next and walker
				runner.next = temp2;
				walker = walker.next;
			} else {
				runner = runner.next;
			}
		}
		return helper.next;

	}
}
