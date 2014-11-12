package leet;

//TODO one dimensional array
public class UniquePathsII {
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0
				|| obstacleGrid[0].length == 0)
			return 0;

		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
		if (m <= 0 || n <= 0)
			return 0;
		int[][] table = new int[m][n];
		// init first col and row
		boolean obsFlag = false;
		// first col
		for (int i = 0; i < m; i++) {
			if (obstacleGrid[i][0] == 1) {
				obsFlag = true;
			}
			if (obsFlag == false) {
				table[i][0] = 1;
			}
		}
		obsFlag = false;
		// first row
		for (int i = 0; i < n; i++) {
			if (obstacleGrid[0][i] == 1) {
				obsFlag = true;
			}
			if (obsFlag == false) {
				table[0][i] = 1;
			}
		}
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < n; j++) {
				if (obstacleGrid[i][j] == 1) {
					table[i][j] = 0;
				} else {
					table[i][j] += table[i - 1][j];
					table[i][j] += table[i][j - 1];
				}
			}
		}
		return table[m - 1][n - 1];
	}
}
