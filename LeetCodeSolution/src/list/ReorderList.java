package list;

/**
 * Given a singly linked list L: L0→L1→…→Ln-1→Ln, reorder it to:
 * L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 
 * @author sidawang
 * 
 */
public class ReorderList {

	private static ListNode findMid(ListNode head) {
		ListNode faster = head, slower = head;
		while (faster != null) {
			faster = faster.next;
			// if(faster != null) ... return 3, instead of 2 as below
			if (faster != null && faster.next != null) { // if 1,2,3,4 return 2
				faster = faster.next;
				slower = slower.next;
			}
		}
		return slower;
	}

	/**
	 * @param head
	 * @return new head of the reversed List
	 */
	public static ListNode reverseList(ListNode head) {
		if (head == null || head.next == null)
			return head;
		ListNode cur = head.next;
		ListNode prev = head;
		head.next = null;
		while (cur != null) {
			ListNode nextNode = cur.next;
			cur.next = prev;
			prev = cur;
			cur = nextNode;
		}
		return prev;
	}

	/**
	 * merge two list by adding one of each till one list is finished
	 * 
	 * @param head
	 * @param second
	 */
	private static void merge(ListNode head, ListNode second) {
		ListNode first = head;
		while (head != null && second != null) {
			ListNode temp = first.next;
			first.next = second;
			second = temp;
			first = first.next;
		}
	}

	public static void reorderList(ListNode head) {
		if (head == null)
			return;
		ListNode mid = findMid(head);
		ListNode second = mid.next;
		mid.next = null;
		second = reverseList(second);
		merge(head, second);
	}

	public static void main(String[] args) {
		ListNode n4 = new ListNode(4);
		ListNode n3 = new ListNode(3);
		ListNode n2 = new ListNode(2);
		ListNode n1 = new ListNode(1);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		// ListNode.printListNode(findMid(n1));
		// System.out.println(reverseList(n1));
		reorderList(n1);
		ListNode.printListNode(n1);
	}
}
