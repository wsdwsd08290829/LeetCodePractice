package list;

/**
 * Reverse a linked list from position m to n. Do it in-place and in one-pass. 1
 * ¡Ü m ¡Ü n ¡Ü length of list
 * 
 * @author sidawang
 * 
 */
public class ReverseLinkedListII {
	// get prevM, subHead, subTail, afterN; reverse [subHead to subTail];fix
	// list
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head == null || m == n)
			return head;

		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;

		ListNode subHead = dummyHead;
		int count = m;
		// move to before M;
		while (count > 1) {
			subHead = subHead.next;
			count--;
		}
		ListNode prevM = subHead;
		if (subHead != null) {
			subHead = subHead.next;
		} else { // m too large
			return head;
		}
		ListNode subTail = subHead;
		count = n - m;

		// move to subTail
		while (count > 0) {
			subTail = subTail.next;
			count--;

		}
		ListNode afterN = subTail.next;

		// reverse list from subHead to subTail
		ListNode currNode = subHead.next;
		ListNode prevNode = subHead;
		count = n - m;
		while (currNode != null && count > 0) {
			ListNode temp = currNode.next;
			currNode.next = prevNode;

			prevNode = currNode;
			currNode = temp;
			count--;
		}
		// fix list
		prevM.next = subTail;
		subHead.next = afterN;

		return dummyHead.next;
	}

	/************** method2 ************/
	// more concise:http://blog.csdn.net/linhuanmars/article/details/24613781
	public ListNode reverseBetween1(ListNode head, int m, int n) {
		if (head == null || m == n)
			return head;
		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;
		ListNode preNode = dummyHead;
		// move to before m
		int i = 1;
		while (preNode.next != null && i < m) {
			i++;
			preNode = preNode.next;
		} // now prev points to previous of m, i==m
		if (i < m)
			return head;
		ListNode mNode = preNode.next;
		ListNode cur = mNode.next;
		while (cur != null && i < n) {
			ListNode next = cur.next;
			cur.next = preNode.next;
			preNode.next = cur;
			mNode.next = next;
			cur = next;
			i++;
		}
		return dummyHead.next;

	}

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		n1.next = n2;
		n2.next = n3;
		ReverseLinkedListII rll = new ReverseLinkedListII();
		ListNode.printListNode(n1);
		ListNode.printListNode(rll.reverseBetween(n1, 1, 2));
	}
}
