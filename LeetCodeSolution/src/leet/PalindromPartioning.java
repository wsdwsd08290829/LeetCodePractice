package leet;

import java.util.ArrayList;
import java.util.List;

public class PalindromPartioning {
	/************ problem1: find all possible partitions **********/
	public static List<List<String>> partition(String s) {
		// final result
		ArrayList<List<String>> result = new ArrayList<List<String>>();
		if (s == null || s.length() == 0) {
			return result;
		}
		// temp result for one partition
		ArrayList<String> partition = new ArrayList<String>();
		addPalindrome(s, 0, partition, result);
		return result;
	}

	// for each first palindrome find, recursively solve rest, add(copy) to
	// result if success, other wise backtrack
	private static void addPalindrome(String s, int start,
			ArrayList<String> partition, ArrayList<List<String>> result) {
		// stop condition
		if (start == s.length()) {
			ArrayList<String> temp = new ArrayList<String>(partition);
			result.add(temp);
			return;
		}
		// !!!
		for (int i = start + 1; i <= s.length(); i++) {
			String str = s.substring(start, i);
			if (isPalindrome(str)) {
				partition.add(str);
				addPalindrome(s, i, partition, result);
				partition.remove(partition.size() - 1); // backtrack
			}
		}
	}

	private static boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}

			left++;
			right--;
		}
		return true;
	}

	/****************** Problem2: find min cut of string to get partition, above method slow *****************/
	/**
	 * cut[i] save min # of cuts from i to end of string; Dynamic programming:
	 * O(n^2)
	 * 
	 * @param result
	 * @return
	 */
	public int minCut(String s) {
		int[][] table = palindromPartitionTable(s);
		int[] cut = new int[s.length() + 1];
		cut[s.length()] = 0;
		// from right to left fill up cut[]
		for (int i = s.length() - 1; i >= 0; i--) {
			cut[i] = s.length() - i;
			// try all possible cut position(table[i][j] ==1) to get min cut[i]
			for (int j = i; j < s.length(); j++) {
				// if [i,j] is palindrome: update min val
				if (table[i][j] == 1) {
					cut[i] = Math.min(cut[i], cut[j + 1] + 1);
				}
			}
		}
		return cut[0] - 1;
	}

	/**
	 * O(n^2 + 2*n) dynamic programming; build up by length (TLE:time limit
	 * exceed?)
	 * 
	 * @param s
	 * @return
	 */
	public int[][] palindromPartitionTable(String s) {
		if (s == null)
			return null;
		int maxLen = 0;
		String longestStr = null;
		int length = s.length();
		int[][] table = new int[length][length];

		// every single letter is palindrome
		for (int i = 0; i < length; i++) {
			table[i][i] = 1;
		}

		// e.g. bcba
		// two consecutive same letters are palindrome
		for (int i = 0; i <= length - 2; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				table[i][i + 1] = 1;
				longestStr = s.substring(i, i + 2);
			}
		}
		// condition for calculate whole table
		for (int l = 3; l <= length; l++) {
			for (int i = 0; i <= length - l; i++) {
				int j = i + l - 1;
				if (s.charAt(i) == s.charAt(j)) {
					table[i][j] = table[i + 1][j - 1];
					if (table[i][j] == 1 && l > maxLen)
						longestStr = s.substring(i, j + 1);
				} else {
					table[i][j] = 0;
				}
			}
		}
		return table;
	}

	/**
	 * another way to create table O(N^2)
	 * 
	 * @param s
	 * @return
	 */
	public int[][] creatTable(String s) {
		int[][] T = new int[s.length()][s.length()];
		for (int i = 0; i < s.length(); i++) {
			T[i][i] = 1;
		}
		for (int i = 0; i < s.length(); i++) {
			// even
			int l = i - 1;
			int r = i;
			while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r))
				T[l--][r++] = 1;
			// odd
			l = i - 1;
			r = i + 1;
			while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r))
				T[l--][r++] = 1;
		}
		return T;
	}

	public static void main(String[] args) {
		System.out.println(partition("abab"));
		System.out.println(partition("ababbbabbababa"));
		System.out.println(new PalindromPartioning()
				.minCut("aba bbb abba b aba"));
	}
}
