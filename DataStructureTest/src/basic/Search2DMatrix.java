package basic;

public class Search2DMatrix {

	public static boolean searchMatrix(int[][] matrix, int target) { 
		//binary search O(log(mn)) < O(m+n)
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
			return false;

		int m = matrix.length;
		int n = matrix[0].length;

		int start = 0;
		int end = m * n - 1;

		while (start <= end) {
			int mid = (start + end) / 2;
			int midX = mid / n;
			int midY = mid % n;

			if (matrix[midX][midY] == target)
				return true;

			if (matrix[midX][midY] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}

		return false;
	}
	public static boolean searchMatrix2(int[][] matrix, int target) {
		//start from upper right corner, snake to lower left corner O(m+n)
		int i=0, j=matrix[0].length-1;
		while(i<matrix.length && j>=0){
			if(target > matrix[i][j]){i++;continue;}
			if(target < matrix[i][j]){j--;continue;}
			if(target == matrix[i][j])return true;
		}
		return false;
	}
	public static void main(String[] args) {
		int[][] arr = { { 1, 3, 5, 7 }, { 10, 11, 16, 20 }, { 23, 30, 34, 50 } };
		System.out.println(searchMatrix(arr, 20));
		System.out.println(searchMatrix2(arr, 20));
	}
}
