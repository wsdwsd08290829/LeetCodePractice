package leet;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
	public String minWindow(String S, String T) {
		if (S == null || T == null || S.length() < T.length())
			return "";
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		Map<Character, Integer> temp = new HashMap<Character, Integer>();
		int count = 0; // count # of char in T added to temp, not more
		int left = 0; // point to first occur of char in T
		String result = "";
		int length = Integer.MAX_VALUE;
		for (int i = 0; i < T.length(); i++) {
			if (map.containsKey(T.charAt(i))) {
				// could be duplicates
				map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
			} else {
				map.put(T.charAt(i), 1);
			}
		}
		// left points to first occur in T
		while (left < S.length() && !map.containsKey(S.charAt(left))) {
			left++;
		}
		// no found
		if (left == S.length())
			return result;
		if (T.length() == 1)
			return T;
		temp.put(S.charAt(left), 1);
		count++;

		for (int i = left + 1; i < S.length(); i++) {
			char c = S.charAt(i);
			// add to temp;
			if (temp.containsKey(c)) {
				temp.put(c, temp.get(c) + 1);
			} else {
				temp.put(c, 1);
			}
			if (map.containsKey(c)) {
				// count unduplicated char added
				if (map.get(c) >= temp.get(c)) {
					count++;
				}
				// move left if contains dup
				// till shortest,find substring from [left to i]
				if (map.get(c) == temp.get(c) && count == T.length()) {

					// move left till min string with out dup
					while (!map.containsKey(S.charAt(left))
							|| map.get(S.charAt(left)) < temp.get(S
									.charAt(left))) {
						temp.put(S.charAt(left), temp.get(S.charAt(left)) - 1);
						left++;
					}
					// find & save substring
					if (i - left + 1 < length) {
						result = S.substring(left, i + 1);
						length = i + 1 - left;
					}
				}
				// if already found result && duplicate with left,
				if (count == T.length() && S.charAt(left) == c
						&& map.get(c) < temp.get(c)) {
					// move left till not duplicate
					while (!map.containsKey(S.charAt(left))
							|| map.get(S.charAt(left)) < temp.get(S
									.charAt(left))) {
						temp.put(S.charAt(left), temp.get(S.charAt(left)) - 1);
						left++;
					}
					// find & save substring
					if (i - left + 1 < length) {
						result = S.substring(left, i + 1);
						length = i + 1 - left;
					}
				}
				// other wise do nothing
			}
		}
		return result;
	}

	/******** method2 ********/
	// maintain one map, if dup --, if >0 count ++;
	// while count == T.length => dup++ till >0;
	public String minWindow1(String S, String T) {
		if (S == null || S.length() == 0)
			return "";
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < T.length(); i++) {
			if (map.containsKey(T.charAt(i))) {
				map.put(T.charAt(i), map.get(T.charAt(i)) + 1);
			} else {
				map.put(T.charAt(i), 1);
			}
		}
		int left = 0;
		int count = 0;
		int minLen = S.length() + 1;
		int minStart = 0;
		for (int right = 0; right < S.length(); right++) {
			if (map.containsKey(S.charAt(right))) {
				map.put(S.charAt(right), map.get(S.charAt(right)) - 1);
				if (map.get(S.charAt(right)) >= 0) {
					count++;
				}
				while (count == T.length()) {
					// use start end length, instead of assign string every time
					if (right - left + 1 < minLen) {
						minLen = right - left + 1;
						minStart = left;
					}
					if (map.containsKey(S.charAt(left))) {
						map.put(S.charAt(left), map.get(S.charAt(left)) + 1);
						if (map.get(S.charAt(left)) > 0) {
							count--;
						}
					}
					left++;
				}
			}
		}
		if (minLen > S.length()) {
			return "";
		}
		return S.substring(minStart, minStart + minLen);
	}

	public static void main(String[] args) {
		MinimumWindowSubstring mw = new MinimumWindowSubstring();
		System.out.println(mw.minWindow("bba", "ab"));
		// System.out.println(mw.minWindow("bdab", "ab"));
	}
}
