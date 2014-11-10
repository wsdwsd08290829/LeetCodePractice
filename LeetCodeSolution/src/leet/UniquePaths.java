package leet;

public class UniquePaths {
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
	/**
	 * recursion O(n^2); f(m,n) = f(m-1,n) + f(m, n-1); too much stack
	 * required...
	 */
	public static void main(String[] args) {
		System.out.println(new UniquePaths().uniquePaths(3, 3));
		System.out.println(new UniquePaths().uniquePaths(1, 1));
		System.out.println(new UniquePaths().uniquePaths(0, 0));
		System.out.println(new UniquePaths().uniquePaths(1, 3));
	}
}
