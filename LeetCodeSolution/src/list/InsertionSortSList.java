package list;

public class InsertionSortSList {
	static int count = 0;

	/**
	 * best, copy online remain to list(sorted, unsorted) remove from unsorted,
	 * insert to sorted;
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode insertionSortList2(ListNode head) {
		if (head == null)
			return head;

		ListNode sorted_head = head;
		head = head.next;
		sorted_head.next = null;
		while (head != null) {
			ListNode next = head.next;

			// If head.val < sorted_head.val
			if (head.val < sorted_head.val) {
				head.next = sorted_head;
				sorted_head = head;
			} else {
				ListNode temp = sorted_head;
				// iterate entil the place to insert current node
				while (temp.next != null && temp.next.val < head.val)
					temp = temp.next;
				ListNode tail = temp.next;
				temp.next = head;
				head.next = tail;
			}

			head = next;
		}

		return sorted_head;
	}

	/**
	 * O(n^2) slow because of moveRightNStep spend time without doing to much
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode insertionSortList1(ListNode head) {
		int length = getListLength(head);
		// check from right to left
		for (int i = length - 2; i >= 0; i--) {
			ListNode node = moveRightNStep(i, head);
			ListNode nextNode = node.next;
			System.out.println(head.val);
			// move the node to right until right place
			while (nextNode != null) {
				// swap if not right order
				if (node.val > nextNode.val) {
					swapVal(node, nextNode);
					// move right one step
					node = nextNode;
					nextNode = node.next;
				} else {
					break;
				}

			}
		}
		return head;
	}

	/**
	 * check node from left to right, if checkNode < prev, move checkNode to
	 * head, choose right place for checkNode(checkHead(..)) O(n) for reverse
	 * ordered list and in order list O(n^2) worst case
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode insertionSortList(ListNode head) {
		// cannot figure out why slow(10^7 nanoSecond), compare to
		// insertionSortList2 ( 10^6 nanoSecond)
		//3 times faster if change checkHead: use if .. else{while..},  instead of while  ... break logic
		if (head == null)
			return head;
		ListNode checkNode = head.next;
		ListNode prev = head;
		while (checkNode != null) {
			ListNode nextCheckNode = checkNode.next;
			if (checkNode.val < prev.val) {
				prev.next = null;
				checkNode.next = head;
				head = checkNode;
				// checkHead(head, prev);
				prev.next = nextCheckNode;
			} else {
				prev = prev.next;
			}
			checkNode = nextCheckNode;
		}
		return head;
	}

	public static void checkHead(ListNode head, ListNode tillNode) {
		if (head.val < head.next.val) {
			return;
		} else {
			while (head != tillNode) { // while break is much slower than if
										// statement !!!!!
				// System.out.println(count++);
				ListNode nextNode = head.next;
				if (head.val > nextNode.val) {
					swapVal(head, nextNode);
					head = head.next;

				} else {
					break;
				}
			}
		}
	}

	public static void swapVal(ListNode n1, ListNode n2) {
		int temp = n1.val;
		n1.val = n2.val;
		n2.val = temp;
	}

	public static ListNode moveRightNStep(int i, ListNode head) {
		while (i > 0) {
			head = head.next;
			i--;
		}
		return head;
	}

	public static int getListLength(ListNode head) {
		int count = 0;
		while (head != null) {
			count++;
			head = head.next;
		}
		return count;
	}

	public static void main(String[] args) {
		int[] arr = { 4, 19, 14, 5, -3, 1, 8, 5, 11, 15 };
		// int[] arr = {4,3,2,1};
		ListNode start = ListNode.createTestList(arr);
		// ListNode.printListNode(start);
		System.out.println(start.val);
		ListNode result = insertionSortList(start);
		ListNode.printListNode(result);

		int[] arr1 = new int[10000];
		for (int i = 0; i < 10000; i++) {
			arr1[i] = 10000 - i;
		}

		start = ListNode.createTestList(arr1);
		System.out.println(start.val);
		long startTime = System.nanoTime();
		result = insertionSortList(start);
		System.out.println(System.nanoTime() - startTime);
		ListNode.printListNode(result);

	}
}
