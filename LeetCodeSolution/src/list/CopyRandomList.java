package list;

import java.util.HashMap;
import java.util.Map;

/**
 * deep copy list whose node has extra random pointer
 * 
 * @author sidawang
 */
public class CopyRandomList {
	public RandomListNode copyRandomList(RandomListNode head) {
		// step1 create new List(just have next pointer)
		if (head == null)
			return null;
		RandomListNode newHead = copyList(head); // no rendom Pointer
		// cross next pointer: old -> corresponding new, new -> oldNext ...
		RandomListNode crossOldHead = createCrossList(head, newHead);
		// copy RandomPoint one by one, restore next point of two list
		RandomListNode finalNewHead = fixRandomPointer(crossOldHead);
		return finalNewHead;
	}

	public static RandomListNode fixRandomPointer(RandomListNode crossOldHead) {
		RandomListNode finalNewHead = crossOldHead.next;
		RandomListNode newCurr = crossOldHead.next;
		RandomListNode oldCurr = crossOldHead;
		while (newCurr != null) {
			RandomListNode oldNext = newCurr.next;
			RandomListNode newNext = null;
			if (oldNext != null) {
				newNext = oldNext.next;
			}

			// fix curr new node's random pointer
			if (oldCurr.random == null) {
				newCurr.random = null;
			} else {
				RandomListNode newRandTarget = oldCurr.random.next;
				newCurr.random = newRandTarget;
			}

			newCurr = newNext;
			oldCurr = oldNext;
		}
		// restore newCurr, oldCurr's next pointer
		newCurr = crossOldHead.next;
		oldCurr = crossOldHead;
		while (newCurr != null) {
			RandomListNode oldNext = newCurr.next;
			RandomListNode newNext = null;
			if (oldNext != null) {
				newNext = oldNext.next;
			}
			// restore newCurr, oldCurr's next pointer
			newCurr.next = newNext;
			oldCurr.next = oldNext;

			newCurr = newNext;
			oldCurr = oldNext;
		}
		return finalNewHead;
	}

	public static RandomListNode createCrossList(RandomListNode oldHead,
			RandomListNode newHead) {
		RandomListNode newNext = newHead, newCurr = newHead;
		RandomListNode oldNext = oldHead, oldCurr = oldHead;

		while (newNext.next != null) {
			newNext = newNext.next;
			oldNext = oldNext.next;

			oldCurr.next = newCurr;
			newCurr.next = oldNext;
			oldCurr = oldNext;
			newCurr = newNext;
		}
		oldCurr.next = newCurr;
		return oldHead;
	}

	public static RandomListNode copyList(RandomListNode head) {
		RandomListNode newHead = new RandomListNode(head.label);
		RandomListNode tempHead = newHead;
		while (head.next != null) {
			head = head.next;
			RandomListNode temp = new RandomListNode(head.label);
			tempHead.next = temp;
			tempHead = temp;
		}
		return newHead;
	}

	public static void printRL(RandomListNode head) {
		while (head != null) {
			System.out.print(head.label + " ");
			head = head.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
		RandomListNode n1 = new RandomListNode(-1);
		RandomListNode n2 = new RandomListNode(1);
		// RandomListNode n3 = new RandomListNode(3);
		n1.next = n2;
		n1.random = n2;
		n2.random = n1;
		// n2.next = n3;
		// test copylist
		RandomListNode newHead = copyList(n1);
		printRL(newHead);
		System.out.println("n1");
		printRL(n1);

		// test crossList
		System.out.println("cross");
		RandomListNode crossOldHead = createCrossList(n1, newHead);
		printRL(crossOldHead);
		System.out.println("n1");
		printRL(n1);

		// test fixRandom
		RandomListNode finalHead = fixRandomPointer(crossOldHead);
		System.out.println("final: ");
		printRL(finalHead);
		System.out.println("n1");
		printRL(n1);

		CopyRandomList crl = new CopyRandomList();
		finalHead = crl.copyRandomList(n1);
		System.out.println(finalHead.random.label);
		System.out.println(finalHead.next.random.label);
	}
}
