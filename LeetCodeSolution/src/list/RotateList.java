package list;

public class RotateList {
	public ListNode rotateRight(ListNode head, int n) {
		ListNode newHead = head;
		if (head == null)
			return head;
		int len = 0;
		// O(n) find length
		ListNode temp = head;
		while (temp != null) {
			len++;
			temp = temp.next;
		}
		// remove loops
		n = n % len; // n alway < len
		if (n == 0)
			return head;// loop back
		// left is tail of new list, right is tail of old list
		ListNode left = head, right = head;
		// distance of left to right is n
		for (int i = 0; i < n; i++) {
			right = right.next;
		}
		// move to the end
		while (right.next != null) {
			right = right.next;
			left = left.next;
		}
		// adjust pointers for new list
		if (left.next != null) {
			newHead = left.next;
			left.next = null;
			right.next = head;
		}
		// when left.next == null=> n=0(already returned)
		return newHead;
	}

	public static void main(String[] args) {
		System.out.println(5 % 1);
	}
}
