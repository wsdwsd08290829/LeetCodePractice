package leet;

public class Search2DMatrix {
	public boolean searchMatrix(int[][] matrix, int target) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;
		int m = matrix.length, n = matrix[0].length;
		int startCode = 0;
		int endCode = m * n - 1; // !!!-1
		// startCode endCode used to flat matrix to 1D array,
		// convert back to 2D for compare,
		// then binary search;
		while (startCode <= endCode) {
			int midCode = startCode + (endCode - startCode) / 2;
			int rowIndex = midCode / n;
			int colIndex = midCode % n;
			if (matrix[rowIndex][colIndex] == target)
				return true;
			if (matrix[rowIndex][colIndex] < target) {
				startCode = midCode + 1;
			} else {
				endCode = midCode - 1;
			}
		}
		return false;
	}

	// could also recursive
	public static void main(String[] args) {
		int[][] matrix = { { 1, 1 } };
		/*
		 * System.out.println(matrix[0][0]); System.out.println(matrix.length);
		 * System.out.println(matrix[0].length); System.out.println(1%1);
		 */
		Search2DMatrix s2m = new Search2DMatrix();
		System.out.println(s2m.searchMatrix(matrix, 2));
	}
}
