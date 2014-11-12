package leet;

public class UniquePaths {
	// TODO use one dimensional array
	/*********** method 1 *************/
	/**
	 * unique paths from upper left to bottom right; dynamic programming,O(n^2)
	 * T[i][j] = T[i-1][j] + T[i][j-1]; first col and row is 1;
	 * 
	 * @param m
	 * @param n
	 * @return
	 */
	public int uniquePaths(int m, int n) {
		if (m <= 0 || n <= 0)
			return 0;
		int[][] table = new int[m][n];
		// init first col and row
		// first col
		for (int i = 0; i < m; i++) {
			table[i][0] = 1;
		}
		// first row
		for (int i = 0; i < n; i++) {
			table[0][i] = 1;
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (i - 1 >= 0) {
					table[i][j] += table[i - 1][j];
				}
				if (j - 1 >= 0) {
					table[i][j] += table[i][j - 1];
				}
			}
		}
		return table[m - 1][n - 1];
	}

	/*********** method 2 *************/
	public int uniquePaths2(int m, int n) {
		double dom = 1;
		double dedom = 1;
		int small = m < n ? m - 1 : n - 1;
		int big = m < n ? n - 1 : m - 1;
		System.out.println(big + " " + small);
		for (int i = 1; i <= small; i++) {
			dedom *= i;
			dom *= small + big + 1 - i;
			System.out.println("dmo" + dom);
		}
		return (int) (dom / dedom);
	}

	/****** method3 ******/
	/**
	 * recursion O(n^2); f(m,n) = f(m-1,n) + f(m, n-1); too much stack
	 * required...
	 */
	public static void main(String[] args) {
		System.out.println(new UniquePaths().uniquePaths2(11, 3));
		System.out.println(new UniquePaths().uniquePaths(1, 1));
		System.out.println(new UniquePaths().uniquePaths(0, 0));
		System.out.println(new UniquePaths().uniquePaths(1, 3));
	}
}
