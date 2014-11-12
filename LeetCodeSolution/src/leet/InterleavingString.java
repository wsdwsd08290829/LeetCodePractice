package leet;

public class InterleavingString {
	/**
	 * test if(s3 contains s1 + s2); recursively remove first char (time limit
	 * exceed); Worst Case: T(n) = 2(T(n-1)) -> O(2^n)
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	public static boolean isInterleave(String s1, String s2, String s3) {
		if (s1 == null || s2 == null || s3 == null)
			return false;
		if (s1.length() + s2.length() != s3.length())
			return false;
		if (s1.length() == 0 || s2.length() == 0 || s3.length() == 0) {
			if (s1.length() == 0)
				return s2.equals(s3);
			if (s2.length() == 0)
				return s1.equals(s3);
			return true;
		}
		if (s1.charAt(0) == s3.charAt(0) && s2.charAt(0) != s3.charAt(0)) {

			return isInterleave(s1.substring(1), s2, s3.substring(1));
		} else if (s1.charAt(0) != s3.charAt(0) && s2.charAt(0) == s3.charAt(0)) {

			return isInterleave(s1, s2.substring(1), s3.substring(1));
		} else if (s1.charAt(0) == s3.charAt(0) && s2.charAt(0) == s3.charAt(0)) {

			return isInterleave(s1, s2.substring(1), s3.substring(1))
					|| isInterleave(s1.substring(1), s2, s3.substring(1));
		} else {// s1.charAt(0) != s3.charAt(0) && s2.charAt(0) != s3.charAt(0)
			return false;
		}
	}

	/**
	 * dynamic programming !!!!
	 * http://blog.csdn.net/u011095253/article/details/9248073
	 * t[i][j]表示s1取前i位，s2取前j位
	 * ，是否能组成s3的前i+j位t[i][j]表示s1取前i位，s2取前j位，是否能组成s3的前i+j位; t(i,j) = t(i-1,j) &&
	 * s1(i-1) ==s3(i+j-1) || t(i,j-1) && s2(j-1) == s3(i+j-1);
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	// one dimensional array,
	// could save space by inner loop shorter string of s1 s2
	// see EditDistance
	public static boolean isInterleave1(String s1, String s2, String s3) {
		// check null...
		if (s3 == null)
			return false;
		if (s1 == null && s2 != null) {
			if (s2.equals(s3))
				return true;
			else
				return false;
		}
		if (s1 != null && s2 == null) {
			if (s1.equals(s3))
				return true;
			else
				return false;
		}

		// check length, faster, !! avoid index out of boundary below
		if (s1.length() + s2.length() != s3.length())
			return false;

		// start dp
		String minWord = s1.length() > s2.length() ? s2 : s1;
		String maxWord = s1.length() > s2.length() ? s1 : s2;
		boolean[] res = new boolean[minWord.length() + 1];
		res[0] = true;
		for (int j = 0; j < minWord.length(); j++) { // first row t(0,j)
			res[j + 1] = res[j] && minWord.charAt(j) == s3.charAt(j);
			// res[j + 1] = s3.startsWith(minWord.substring(0, j + 1)); //right
		}
		// res[0] = true;
		for (int i = 0; i < maxWord.length(); i++) { // t(1,j), t(2,j)...
			// res[0] = s3.startsWith(maxWord.substring(0, i + 1)); //right
			res[0] = res[0] && maxWord.charAt(i) == s3.charAt(i);
			for (int j = 0; j < minWord.length(); j++) {
				// prev row
				if ((maxWord.charAt(i) == s3.charAt(i + j + 1) && res[j + 1])
				// current row, prev col
						|| (minWord.charAt(j) == s3.charAt(i + j + 1) && res[j]))
					res[j + 1] = true;
				else
					res[j + 1] = false;// !!!must, may from true to false
				// res[j+1] = res[j+1]&&maxWord.charAt(i)==s3.charAt(i+j+1) ||
				// res[j]&&minWord.charAt(j)==s3.charAt(i+j+1);
			}
		}
		return res[minWord.length()];
	}

	/***** method 3 ******/
	// two dimensional array;
	// http://blog.csdn.net/u011095253/article/details/9248073
	public boolean isInterleave2(String s1, String s2, String s3) {

		if (s1 == null || s2 == null || s3 == null)
			return false;
		if (s1.length() + s2.length() != s3.length())
			return false;
		boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
		dp[0][0] = true;
		for (int i = 1; i < s1.length() + 1; i++) {
			if (s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0]) {
				dp[i][0] = true;
			}
		}
		for (int j = 1; j < s2.length() + 1; j++) {
			if (s2.charAt(j - 1) == s3.charAt(j - 1) && dp[0][j - 1]) {
				dp[0][j] = true;
			}
		}
		for (int i = 1; i < s1.length() + 1; i++) {
			for (int j = 1; j < s2.length() + 1; j++) {
				if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j]) {
					dp[i][j] = true;
				}
				if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1]) {
					dp[i][j] = true;
				}
			}
		}
		return dp[s1.length()][s2.length()];
	}

	public static void main(String[] args) {
		String s1 = "ab";
		String s2 = "bc";
		// String s3 = "aadbbcbcac";
		String s3 = "bbac";
		String s32 = "aadbbbaccc";
		// String test = "s";
		// System.out.println(test.substring(1).length());
		// System.out.println(isInterleave(s1, s2, s32)); // false;
		System.out.println(isInterleave1(s1, s2, s3)); // true
	}
}