package list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MergeKLists {
	/**
	 * complexity lot(# of lists) * (all list length)
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(List<ListNode> lists) {
		if (lists == null || lists.size() == 0)
			return null;
		PriorityQueue<ListNode> pq = new PriorityQueue<ListNode>(lists.size(),
				new Comparator<ListNode>() {
					@Override
					public int compare(ListNode o1, ListNode o2) {
						return o1.val - o2.val;
					}
				});
		// save first elem of each node list to pq
		for (ListNode ln : lists) {
			// !!! lists and just save one ListNode that points to null;
			if (ln != null) {
				pq.add(ln);
			}
		}
		ListNode head = pq.peek();
		// remove -> supply new -> point next
		while (pq.size() > 0) {
			ListNode first = pq.remove();
			if (first.next != null) {
				pq.add(first.next);
			}
			first.next = pq.peek();
			first = first.next;
		}
		return head;
	}

	public static void main(String[] args) {
		List<ListNode> lists = new ArrayList<ListNode>();
		ListNode head = null;
		lists.add(head);
		System.out.println(new MergeKLists().mergeKLists(lists));
	}
}
