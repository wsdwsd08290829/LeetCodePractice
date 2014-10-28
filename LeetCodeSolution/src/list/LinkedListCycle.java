package list;

public class LinkedListCycle {
	/**
	 * faster will catch up slower if there is cycle
	 * 
	 * @param head
	 * @return
	 */
	public static boolean hasCycle(ListNode head) {
		ListNode slower = head, faster = head;
		while (faster != null && faster.next != null) {
			faster = faster.next.next;
			slower = slower.next;
			if (faster == slower) {
				return true;
			}
		}
		return false;
	}

	public static ListNode meetPoint(ListNode head) {
		ListNode slower = head, faster = head;
		while (faster != null && faster.next != null) {
			faster = faster.next.next;
			slower = slower.next;
			if (faster == slower) {
				break;
			}
		}
		return faster;
	}

	/**
	 * d is how far faster is away from cycle begin when slower arrive cycle
	 * begin. Then the distance from meet point back to cycle begin is d; if
	 * move faster and begin together, then they will meet at begin see prove
	 * at: http://stackoverflow.com/questions/2936213/explain-how-finding-cycle-
	 * start-node-in-cycle-linked-list-work
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode findCycleBegin(ListNode head) {
		if (hasCycle(head)) {
			ListNode start = head;
			ListNode meetNode = meetPoint(head);
			while (meetNode != start) {
				start = start.next;
				meetNode = meetNode.next;
			}
			return start;
		}
		return null;
	}

	public static void main(String[] args) {
		ListNode n4 = new ListNode(4);
		ListNode n3 = new ListNode(3);
		ListNode n2 = new ListNode(2);
		ListNode n1 = new ListNode(1);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n2;
		System.out.println(findCycleBegin(n1).val);
	}
}
