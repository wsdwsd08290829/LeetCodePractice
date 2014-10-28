package leet;

public class Helper {
	public static void printMatrix(int[][] matrix) {
		if (matrix == null) {
			System.out.println("null matrix");
			return;
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}
