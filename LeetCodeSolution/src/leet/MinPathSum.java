package leet;

public class MinPathSum {
	// table[i][j] = table[i-1][j] + table[i][j-1]
	public int minPathSum(int[][] grid) {
		if (grid == null || grid.length == 0 || grid[0].length == 0) {
			return 0;
		}
		int m = grid.length;
		int n = grid[0].length;
		int[] res = new int[n];
		// res[0] = grid[0][0];
		for (int i = 0; i < m; i++) {
			res[0] += grid[i][0]; // init first col
			for (int j = 1; j < n; j++) {
				if (i != 0) {
					res[j] = Math.min(res[j], res[j - 1]) + grid[i][j];
				} else {
					res[j] = res[j - 1] + grid[0][j]; // init first row
					// can also start i=1 and init first row in another loop
				}
			}
		}
		return res[n - 1];
	}
}
