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
	 * dynamic programming
	 * http://blog.csdn.net/u011095253/article/details/9248073
	 * dp[i][j]表示s1取前i位，s2取前j位
	 * ，是否能组成s3的前i+j位dp[i][j]表示s1取前i位，s2取前j位，是否能组成s3的前i+j位
	 * 
	 * @param s1
	 * @param s2
	 * @param s3
	 * @return
	 */
	/* TODO */
	public static boolean isInterleave1(String s1, String s2, String s3) {
		return true;
	}

	public static void main(String[] args) {
		String s1 = "aabcc";
		String s2 = "dbbca";
		// String s3 = "aadbbcbcac";
		String s3 = "aabccdbbca";
		String s32 = "aadbbbaccc";
		// String test = "s";
		// System.out.println(test.substring(1).length());
		// System.out.println(isInterleave(s1, s2, s32)); // false;
		System.out.println(isInterleave1(s1, s2, s3)); // true
	}
}