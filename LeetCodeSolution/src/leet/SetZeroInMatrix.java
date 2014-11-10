package leet;

public class SetZeroInMatrix {
	// O(m+n) space
	public void setZeroes(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return;
		int m = matrix.length, n = matrix[0].length;

		boolean[] row = new boolean[m];
		boolean[] col = new boolean[n];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					row[i] = true;
					col[j] = true;
				}
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (row[i] == true || col[j] == true) {
					matrix[i][j] = 0;
				}
			}
		}
	}

	// constant space
	public void setZeroes1(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return;
		boolean isFirstRowAllZero = false;
		boolean isFirstColAllZero = false;
		// check whether border(first row and col) all 0
		for (int i = 0; i < matrix.length; i++) { // column
			if (matrix[i][0] == 0)
				isFirstColAllZero = true;
		}
		for (int i = 0; i < matrix[0].length; i++) { // column
			if (matrix[0][i] == 0)
				isFirstRowAllZero = true;
		}
		// mark border 0 by scan inner matrix
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[i][j] == 0) {
					matrix[0][j] = 0;
					matrix[i][0] = 0;
				}
			}
		}
		// fill inner column, row 0s
		for (int i = 1; i < matrix.length; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (matrix[0][j] == 0 || matrix[i][0] == 0) {
					matrix[i][j] = 0;
				}
			}
		}
		// fill border 0s if necessary
		if (isFirstColAllZero) {
			for (int i = 0; i < matrix.length; i++) { // column
				matrix[i][0] = 0;
			}
		}
		if (isFirstRowAllZero) {
			for (int i = 0; i < matrix[0].length; i++) { // column
				matrix[0][i] = 0;
			}
		}
	}
}
