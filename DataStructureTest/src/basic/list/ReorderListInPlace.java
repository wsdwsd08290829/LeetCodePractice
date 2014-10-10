package basic.list;

public class ReorderListInPlace {
	public static void main(String[] args) {
		//ListNode n5 = new ListNode(5);
		ListNode n4 = new ListNode(4);
		ListNode n3 = new ListNode(n4, 3);
		ListNode n2 = new ListNode(n3, 2);
		ListNode firstNode = new ListNode(n2, 1);
		System.out.println(firstNode);
		ListNode result = reorderList(firstNode);
		System.out.println(result);
	}

	private static ListNode reorderList(ListNode firstNode) {
		//faster slower logic
		ListNode mid = findMid(firstNode);
		System.out.println("mid" + mid);
		//fix end of first half to null
		ListNode start = firstNode;
		while(start.next !=mid){
			start = start.next;
		}
		start.next = null;
		
		//pointing back
		ListNode reversed = reverse(mid);
		System.out.println(reversed);
		
		//merge list
		ListNode result = merge(firstNode, reversed);
		return result;
	}

	// 1->2 3<-4<-5
	private static ListNode merge(ListNode firstNode, ListNode reversed) {
		ListNode start = firstNode;
		while (firstNode != null && reversed != null) {
			ListNode temp1 = firstNode.next;
			ListNode temp2 = reversed.next;
			firstNode.next = reversed;
			reversed.next = temp1;
			firstNode = temp1;
			reversed = temp2;
		}
		return start;
	}

	private static ListNode reverse(ListNode start) {
		ListNode head = start;
		if (start.next == null)
			return start;
		ListNode second = start.next;
		while (second != null) {
			ListNode third = second.next;
			second.next = start;
			start = second;
			second = third;
		}
		head.next = null;
		return start;
	}

	private static ListNode findMid(ListNode firstNode) {

		// if(firstNode.next == null)return null;
		ListNode faster = firstNode;
		while (faster.next != null) {
			firstNode = firstNode.next;
			faster = faster.next;
			if (faster.next != null)
				faster = faster.next;
		}
		return firstNode;
	}
}
