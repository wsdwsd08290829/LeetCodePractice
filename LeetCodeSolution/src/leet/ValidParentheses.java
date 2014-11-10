package leet;

import java.util.Stack;

/**
 * '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * 
 * @author sidawang
 * 
 */
public class ValidParentheses {
	public boolean isValid(String s) {
		if (s == null)
			return false;
		String left = "([{";
		String right = ")]}";// could use map
		Stack<Character> stack = new Stack<Character>();
		// 1 if left push to stack
		for (int i = 0; i < s.length(); i++) {
			if (left.indexOf(s.charAt(i), 0) >= 0) { // !=0
				stack.push(s.charAt(i));
			} else {
				// 2 if right pop from stack(if has val, else false)
				if (!stack.isEmpty()) {
					// ! indexOf usage
					if (left.indexOf(stack.pop()) != right.indexOf(s.charAt(i))) {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		// 3 reach last, if stack not null -> false
		return stack.isEmpty() ? true : false;
	}
}
