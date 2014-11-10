package list;

public class RemoveNthNodeFromEnd {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (n <= 0 || head == null)
			return head;
		// 1.add dummyhead, from dummyhead, move n time to endNode;(if n too
		// large)
		ListNode dummyHead = new ListNode(0);
		dummyHead.next = head;
		ListNode endNode = dummyHead;
		ListNode startNode = dummyHead;
		while (n > 0) {
			endNode = endNode.next;
			n--;
			// if n is too large
			if (endNode == null)
				return dummyHead.next;
		}
		// 2. move startNode, endNode both till endNode last
		while (endNode.next != null) {
			startNode = startNode.next;
			endNode = endNode.next;
		}
		// 3 remove next node of startNode
		startNode.next = startNode.next.next;
		// 4 return head;
		return dummyHead.next;
	}
}
