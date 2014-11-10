package leet;

import java.util.Stack;

public class MinStack {
	Stack<Integer> stack;
	Stack<Integer> minStack;
	int min;

	MinStack() {
		stack = new Stack<Integer>();
		minStack = new Stack<Integer>();
		min = Integer.MAX_VALUE;
	}

	public void push(int x) {
		stack.push(x);
		min = Math.min(min, x);
		minStack.push(min);
	}

	public void pop() {
		if (!stack.isEmpty()) {
			stack.pop();
			minStack.pop();
			min = minStack.peek();
		} else {
			min = Integer.MAX_VALUE;
		}
	}

	public int top() {
		if (!stack.isEmpty()) {
			return stack.peek();
		} else {
			return 0;
		}
	}

	public int getMin() {
		return min;
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
