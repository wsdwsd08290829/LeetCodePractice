package leet;

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

public class addTwoNumber {
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1 == null && l2 == null)
			return null;
		int carry = 0;
		int result = 0;
		ListNode resultPointer, head = new ListNode(0); // dummy head
		resultPointer = head; // point used to add next
		//handle common part
		while (l1 != null && l2 != null) {
			result = (l1.val + l2.val + carry) % 10;
			carry = (l1.val + l2.val + carry) / 10;
			l1 = l1.next;
			l2 = l2.next;
			ListNode node = new ListNode(result);
			resultPointer.next = node;
			resultPointer = resultPointer.next;
		}
		//l2 is longer
		if (l2 != null) {
			while (l2 != null) {
				result = (l2.val + carry) % 10;
				carry = (l2.val + carry) / 10;
				resultPointer.next = new ListNode(result);
				resultPointer = resultPointer.next;
				l2 = l2.next;
			}
		}
		//l1 is longer
		if (l1 != null) {
			while (l1 != null) {
				result = (l1.val + carry) % 10;
				carry = (l1.val + carry) / 10;
				resultPointer.next = new ListNode(result);
				resultPointer = resultPointer.next;
				l1 = l1.next;
			}
		}
		//handle last carry
		if (carry != 0) {
			resultPointer.next = new ListNode(carry);
		}
		return head.next;
	}

	public static void main(String[] args) {
		ListNode l11 = new ListNode(2);
		ListNode l12 = new ListNode(4);
		ListNode l13 = new ListNode(6);
		ListNode l21 = new ListNode(5);
		ListNode l22 = new ListNode(6);
		ListNode l23 = new ListNode(4);
		l11.next = l12;
		l12.next = l13;
		l21.next = l22;
		l22.next = l23;
		ListNode node = addTwoNumbers(l11, l21);
		System.out.println(node.val);
		System.out.println(node.next.val);
		System.out.println(node.next.next.val);
		System.out.println(node.next.next.next.val);
	}
}
