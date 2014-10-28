package leet;

/**
 * @author sidawang
 */
public class NumOfDistinctSubsequence {
	/**
	 * find the max # of distinct subsequences of T in s dynamic programming
	 * example
	 * 
	 * @param S
	 *            : string unit like rabbbit
	 * @param T
	 *            : contain string S: rabbit
	 * @return # of distinct subsequences, eg. above 3
	 */
	public static int numDistincts(String S, String T) {
		if (S == null || T == null) {
			return 0;
		}
		// initialize
		int[][] table = new int[S.length() + 1][T.length() + 1];
		// i is length of S, j is length of T
		for (int i = 0; i < S.length(); i++)
			table[i][0] = 1;
		// dynamic programming: bottom up to create results of s.substring(0,
		// i), t.substring(0,j)
		for (int i = 1; i <= S.length(); i++) {
			for (int j = 1; j <= T.length(); j++) {
				if (S.charAt(i - 1) == T.charAt(j - 1)) {
					table[i][j] += table[i - 1][j] + table[i - 1][j - 1];
				} else {
					table[i][j] += table[i - 1][j];
				}
			}
		}
		return table[S.length()][T.length()];
	}

	public static void main(String[] args) {
		System.out.println(numDistincts("aaa", "aa"));
	}
}
