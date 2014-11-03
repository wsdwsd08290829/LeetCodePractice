package leet;

public class LongestPalindrom {
	/**
	 * O(n^2 + 2*n) dynamic programming; build up by length (TLE:time limit
	 * exceed?)
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		if (s == null)
			return null;
		if (s.length() <= 1)
			return s;
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
		return longestStr;
	}

	/**
	 * O(n^2)
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome1(String s) {
		String max = "";
		for (int center = 0; center < s.length(); center++) {
			// find longest centered at center
			int left = center - 1;
			int right = center + 1;
			// skip all that same as center, abba, abbba
			while (left >= 0 && s.charAt(left) == s.charAt(center)) {
				left--;
			}
			while (right < s.length() && s.charAt(right) == s.charAt(center)) {
				right++;
			}
			// check left most and right most until end
			while (left >= 0 && right < s.length()) {
				if (s.charAt(left) == s.charAt(right)) {
					left--;
					right++;
				} else {
					break;
				}
			}
			if (right - left - 1 > max.length())
				max = s.substring(left + 1, right);
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(new LongestPalindrom().longestPalindrome("adam"));
	}
}
