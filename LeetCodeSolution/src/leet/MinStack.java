package leet;

import java.util.Stack;

public class MinStack {
	private Stack<Integer> stack = new Stack<Integer>();
	private Stack<Integer> minStack = new Stack<Integer>();

	public void push(int x) {
		stack.push(x);
		if (minStack.isEmpty() || x <= minStack.peek()) {
			minStack.push(x);
		}
	}

	public void pop() {
		// peek throws EmptyStackException donot need check for leet code(donot
		// process exception check),need for real world

		if (stack.peek().equals(minStack.peek()))
			minStack.pop();
		stack.pop();
	}

	public int top() {
		if (!stack.isEmpty()) {
			return stack.peek();
		} else {
			return Integer.MAX_VALUE;
		}
	}

	public int getMin() {
		if (!stack.isEmpty()) {
			return minStack.peek();
		} else {
			return Integer.MAX_VALUE;
		}
	}

	/****** method2 ********/
	// cread structure Node which contain min and val
	class MinStack1 {
		Node top = null;

		public void push(int x) {
			if (top == null) {
				top = new Node(x);
				top.min = x;
			} else {
				Node temp = new Node(x);
				temp.next = top;
				top = temp;
				top.min = Math.min(top.next.min, x);
			}
		}

		public void pop() {
			top = top.next;
			return;
		}

		public int top() {
			return top == null ? 0 : top.val;
		}

		public int getMin() {
			return top == null ? 0 : top.min;
		}
	}

	class Node {
		int val;
		int min;
		Node next;

		public Node(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) {
		MinStack ms = new MinStack();
		ms.push(2147483646);
		ms.push(2147483646);
		ms.push(2147483647);
		System.out.println(ms.top());
		ms.pop();
		System.out.println(ms.getMin());
		ms.pop();
		System.out.println(ms.getMin());
		ms.pop();
		ms.push(2147483647);
		ms.top();
		ms.getMin();
		ms.push(-2147483648);
		ms.top();
		System.out.println(ms.getMin());
		ms.pop();
		ms.getMin();
	}
}
