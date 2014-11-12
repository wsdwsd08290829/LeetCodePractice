package leet;

public class SpiralMatrixN2 {
	public int[][] generateMatrix(int n) {
		if (n < 0)
			return null;
		int[][] matrix = new int[n][n];
		if (n == 0)
			return matrix;
		int rowBegin = 0;
		int rowEnd = matrix.length - 1;
		int colBegin = 0;
		int colEnd = matrix[0].length - 1;
		int num = 1;
		while (rowBegin <= rowEnd && colBegin <= colEnd) {
			for (int i = colBegin; i <= colEnd; i++) {
				matrix[rowBegin][i] = num++;
			}
			rowBegin++;
			for (int i = rowBegin; i <= rowEnd; i++) {
				matrix[i][colEnd] = num++;
			}
			colEnd--;
			for (int i = colEnd; i >= colBegin; i--) {
				matrix[rowEnd][i] = num++;
			}
			rowEnd--;
			for (int i = rowEnd; i >= rowBegin; i--) {
				matrix[i][colBegin] = num++;
			}
			colBegin++;
		}
		return matrix;
	}

	public static void main(String[] args) {
		SpiralMatrixN2 sm = new SpiralMatrixN2();
		Helper.printMatrix(sm.generateMatrix(0));
	}
}
