package leet;

public class ScrambleString {
	/**
	 * if two string is scrambled
	 * https://oj.leetcode.com/problems/scramble-string/
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public boolean isScramble(String s1, String s2) {
		if (s1 == null || s2 == null || s1.length() != s2.length())
			return false;
		if (s1.length() == 0)
			return true;
		// i,j: s1 start from i, s2 start from j, both of length len
		// is two scrambled? -> boolean[i][j][len]
		int length = s1.length();
		boolean[][][] table = new boolean[length][length][length + 1];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				table[i][j][1] = s1.charAt(i) == s2.charAt(j);
			}
		}
		for (int len = 2; len <= length; len++) {
			for (int i = 0; i <= length - len; i++) {
				for (int j = 0; j <= length - len; j++) {
					for (int k = 1; k < len; k++) {
						table[i][j][len] = table[i][j][len]
								|| (table[i][j][k] && table[i + k][j + k][len
										- k])
								|| (table[i][j + len - k][k] && table[i + k][j][len
										- k]);
					}
				}
			}
		}
		return table[0][0][length];
	}

	public static void main(String[] args) {
		ScrambleString ss = new ScrambleString();
		System.out.println(ss.isScramble("eat", "ate"));
	}
}
