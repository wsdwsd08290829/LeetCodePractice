package leet;

import java.util.HashMap;
import java.util.Map;
/**
 * map key to node(double linked) containing key and value
 * get: remove node, add to first, return val  or -1
 * set: remove node, add to first, update val ( remove tail if full )
 * @author sidawang
 * or use LinkedHashMap
 * entrySet().iterator().next() to get first Entry
 */
public class LRUCache {
	int capacity;
	int size;
	Node head, tail;
	Map<Integer, Node> cache = new HashMap<Integer, Node>();

	static class Node {
		Node prev, next;
		int key, val;

		public Node(int key, int val) {
			this.key = key;
			this.val = val;
		}

		@Override
		public String toString() {
			return " " + val;
		}

	}

	public Node removeNode(Node n) {
		if (n == null)
			return null;
		Node prev = n.prev;
		Node next = n.next;
		// System.out.println("next" + next.val);
		if (prev == null) {
			head = next;
			if (head != null)
				head.prev = null;
		} else {
			prev.next = next;
		}
		if (next == null) {
			tail = prev;
			if (tail != null)
				tail.next = null;

		} else {
			next.prev = prev;
		}
		n.next = null;
		n.prev = null;
		size--;
		return n;
	}

	public void setHead(Node n) {
		if (head == null) {
			head = n;
			tail = n;
		} else {
			head.prev = n;
			n.next = head;
			head = n;
		}
		size++;
	}

	public LRUCache(int capacity) {
		this.capacity = capacity;
	}

	public int get(int key) {
		if (cache.containsKey(key)) {
			Node n = cache.get(key);
			removeNode(n);
			setHead(n);
			return n.val;
		} else {
			return -1;
		}
	}
/**
 * remove node, add to first, update val ( remove tail if full )
 * @param key
 * @param value
 */
	public void set(int key, int value) {
		if (cache.containsKey(key)) {
			Node n = cache.get(key);
			n.val = value;
			removeNode(n);
			setHead(n);
		} else {
			if (capacity == 0)
				return;
			if (capacity == cache.size()) {
				cache.remove(tail.key);
				removeNode(tail);
			}
			Node n = new Node(key, value);
			cache.put(key, n);
			setHead(n);
		}
	}

	public void printLRU(Node head) {
		while (head != null) {
			System.out.print(" " + head);
			head = head.next;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		LRUCache lcf = new LRUCache(1);
		lcf.set(2, 1);
		lcf.get(2);
		lcf.set(3, 2);

		lcf.printLRU(lcf.head);
		System.out.println(lcf.get(2));
		lcf.get(3);

		/*
		 * lcf.set(1, 11); lcf.printLRU(lcf.head); lcf.set(2, 22);
		 * lcf.printLRU(lcf.head); lcf.get(1); lcf.printLRU(lcf.head);
		 * lcf.set(3, 33); lcf.printLRU(lcf.head); lcf.set(1, 111);
		 * lcf.printLRU(lcf.head);
		 */
	
	}
}
