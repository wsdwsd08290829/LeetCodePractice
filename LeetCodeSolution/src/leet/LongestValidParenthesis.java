package leet;

import java.util.LinkedList;
import java.util.Stack;

/*********************first method **********************/
/**
 * find the length of the longest valid (well-formed) parentheses substring
 * ")()())", longest valid parentheses substring is "()()" => 4;"()(())" => 6
 * !!"()(()" => 2 best: logic complex
 * 
 * @author sidawang
 * 
 */
public class LongestValidParenthesis {
	public static int longestValidParentheses2(String s) {
		if (s == null)
			return 0;
		Stack<Integer> stack = new Stack<Integer>();
		int lastExtraRight = -1;
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else { // meet ")"
				if (stack.isEmpty()) { // extra ")", next max starts here
					lastExtraRight = i;
				} else { // find match
					stack.pop();
					// update max for this match
					if (stack.isEmpty()) {
						max = Math.max(max, i - lastExtraRight); // extra ")" as
																	// start
					} else {
						max = Math.max(max, i - stack.peek()); // extra "(" as
																// start
					}
				}

			}
		}
		return max;
	}

	/********************* second method **********************/
	/**
	 * start, end pair that make valid parenthesis string
	 * 
	 * @author sidawang
	 * 
	 */
	public static class Pair {
		int start;
		int end;

		Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public String toString() {
			return "(" + start + ",  " + end + ")";
		}
	}

	/**
	 * liked list save a list of pair(indicate start, end pos of string) each
	 * pair is Longest continuous valid parenthesis start, end index
	 * 
	 * @param s
	 * @return
	 */
	public static int longestValidParentheses(String s) {
		if (s == null)
			return 0;
		Stack<Integer> stack = new Stack<Integer>();
		LinkedList<Pair> ll = new LinkedList<Pair>();
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else { // meet ")"
				if (!stack.isEmpty()) { // find match
					int start = stack.pop();
					int end = i;
					if (ll.isEmpty()) {
						ll.add(new Pair(start, end));
					} else {
						// if wrapping parenthesis then replace
						// then check parallel parenthesis(need merge)
						Pair last = ll.getLast();
						// if outer pair: change inner pair to outer pair "(())"
						if (last.start > start && last.end < end) {
							ll.removeLast();
							ll.add(new Pair(start, end));
						} else {
							ll.add(new Pair(start, end));
						}
						// merge last two if is parallel: ()(())
						if (ll.size() >= 2) {
							Pair secToLast = ll.get(ll.size() - 2);
							Pair newLast = ll.getLast();
							// is parallel
							if (secToLast.end + 1 == newLast.start) {
								ll.removeLast();
								secToLast.end = newLast.end;
							}
						}
					}
				}
			}
		}
		for (Pair p : ll) {
			max = Math.max(max, p.end - p.start + 1);
		}
		return max;
	}

	/********************* third method(brute force), bad **********************/
	// slow O(n^3)
	public static int longestValidParentheses1(String s) {
		if (s == null)
			return 0;
		int max = 0;
		for (int i = 0; i < s.length() - 1; i++) {
			for (int j = i + 1; j <= s.length(); j++) {
				if (isValidParentheses(s.substring(i, j))) {
					max = Math.max(max, j - i);
				}
			}
		}
		return max;
	}

	public static boolean isValidParentheses(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push('(');
			} else {
				if (stack.size() > 0) {
					stack.pop();
				} else {
					return false;
				}
			}
		}
		if (stack.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(longestValidParentheses("(()"));
		System.out.println(longestValidParentheses(")()())"));
		System.out.println(longestValidParentheses("()(())"));
		System.out.println(longestValidParentheses("()(()"));
	}
}
