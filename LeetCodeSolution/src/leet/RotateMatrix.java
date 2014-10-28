package leet;

public class RotateMatrix {
	public static void rotate(int[][] matrix) {
		int n  = matrix.length;
		for(int i = 0; i<= (matrix.length-1-1)/2;i++){
			for(int j= 0; j<= (matrix.length-1)/2; j++){
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n-j-1][i];
				matrix[n-j-1][i] = matrix[n-i-1][n-j-1];
				matrix[n-i-1][n-j-1] = matrix[j][n-i-1];
				matrix[j][n-i-1]  = temp;
			}
		}
	}
	public static void main(String[] args) {
		int[][] matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
		rotate(matrix);
		Helper.printMatrix(matrix);
	}
}
