package list;


/**
 * O(nlog(n))
 * merge sort for singly linked list
 * @author sidawang
 */
public class MergeSortSList {
	public static ListNode mergeSort(ListNode head) {
		if (head == null)
			return null;
		if (head.next == null)
			return head;
		ListNode mid = findMid(head);
		ListNode second = mid.next;
		mid.next = null;
		ListNode firstHalf = mergeSort(head);
		ListNode secondHalf = mergeSort(second);
		return merge(firstHalf, secondHalf);
	}

	public static ListNode findMid(ListNode ln) {
		ListNode slower = ln;
		ListNode faster = ln;
		while (faster.next != null) {
			faster = faster.next;
			if (faster.next != null) {
				faster = faster.next;
				slower = slower.next;
			}
		}
		return slower;
	}

	private static ListNode merge(ListNode l1, ListNode l2) {
		ListNode head = null;
		// points to the current scanned position of two list
		// l1p always points to the current min
		ListNode l1p = null;
		ListNode l2p = null;
		if (l1 == null && l2 != null)
			return l2;
		if (l1 != null && l2 == null)
			return l1;

		if (l1.val < l2.val) {
			head = l1;
			l1p = l1; // always start from l1p
			l2p = l2;
		} else {
			head = l2;
			l1p = l2; // always start from l1p
			l2p = l1;
		}
		while (l1p != null && l2p != null) {
			ListNode tempNode = l1p.next;
			if (tempNode != null) {
				if (tempNode.val < l2p.val) {
					l1p = l1p.next;
				} else {
					l1p.next = l2p;
					l1p = l2p;
					l2p = tempNode;
				}
			} else {
				l1p.next = l2p;
				l2p = tempNode;
			}
		}
		return head;
	}

	// if too much items-> stack overflow, too much memory
	private static ListNode mergeRecursive(ListNode l1, ListNode l2) {
		ListNode head = null;
		if (l1 == null && l2 != null)
			return l2;
		if (l1 != null && l2 == null)
			return l1;
		if (l1.val < l2.val) {
			head = l1;
			head.next = mergeRecursive(l1.next, l2);
		} else {
			head = l2;
			head.next = mergeRecursive(l1, l2.next);
		}
		return head;
	}

	public static void main(String[] args) {
		int[] arr = { 4, 19, 14, 5, -3, 1, 8, 5, 11, 15 };
		// int[] arr = {4,3,2,1};
		ListNode start = createTestList(arr);
		printListNode(start);
		ListNode result = mergeSort(start);
		printListNode(result);
	}

	// helper functions for testing
	private static ListNode createTestList(int[] arr) {
		ListNode[] lnArr = new ListNode[arr.length];
		for (int i = 0; i < arr.length; i++) {
			lnArr[i] = new ListNode(arr[i]);
		}
		for (int i = 0; i < arr.length - 1; i++) {
			lnArr[i].next = lnArr[i + 1];
		}
		lnArr[lnArr.length - 1].next = null;
		return lnArr[0];
	}

	public static void printListNode(ListNode n) {
		while (n != null) {
			System.out.print(n.val + " ");
			n = n.next;
		}
		System.out.println();
	}
}
