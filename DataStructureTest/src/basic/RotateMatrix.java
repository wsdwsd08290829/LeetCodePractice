package basic;

public class RotateMatrix {

	public static void main(String[] args) {
		int[][] arr = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		printMatrix(arr);
		System.out.println("-------");
		printMatrix(rotateMatrix(arr));
		System.out.println("____________");
		int[][] arr2 = { { 1, 2, 3,4,5 },{1,2,3,4,5 },{1,2,3,4,5 },{1,2,3,4,5 },{1,2,3,4,5 }};
		printMatrix(arr2);
		System.out.println("-------");
		printMatrix(rotateMatrix(arr2));
		System.out.println("____________");
		int[][] arr3 = { { 1, 2, 3,4 },{1,2,3,4 },{1,2,3,4 },{1,2,3,4 }};
		printMatrix(arr3);
		System.out.println("-------");
		printMatrix(rotateMatrix(arr3));
		
		
		System.out.println("@@@@@@@");
		rotate(arr3);
		printMatrix(arr3);
	}
	//rotate from outer to inner circle
	/*
	 *  @@@@@X
	 *  X@@@XX
	 *  XX@XXX
	 *  XXXXXX
	 *  XXXXXX
	 *  XXXXXX
	 */
	private static int[][] rotateMatrix(int[][] arr) {
		if(arr==null)return null;
		if(arr.length != arr[0].length)System.out.println("could only return n*n matrix");
		int m = arr.length;
		int rotateLength = m;
		int rowIndex = 0;
		for(int l = 0; l<m/2; l++){
			rowIndex = l;
			for(int colIndex=l;colIndex<l+rotateLength-1;colIndex++){
		//		System.out.println("("+rowIndex+","+colIndex+")");
				rotateElem(arr, rowIndex, colIndex );
			}
			rotateLength-=2;
		}
		
		return arr;
	}
	//in place rotate one item clockwise
	public static void rotateElem(int[][] arr, int i, int j){
		int m= arr.length;
		int temp = arr[i][j];
		arr[i][j] = arr[m-j-1][i];
		arr[m-j-1][i] = arr[m-i-1][m-j-1];
		arr[m-i-1][m-j-1] = arr[j][m-i-1];
		arr[j][m-i-1] = temp;
	}
	private static void printMatrix(int[][] matrix) {
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
	//rotate first 1/4 sector (better than mine)
	/*
	 *  @@@XXX
	 *  @@@XXX
	 *  @@@XXX
	 *  XXXXXX
	 *  XXXXXX
	 *  XXXXXX
	 */
	public static void rotate(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < Math.ceil(((double) n) / 2.); j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n-1-j][i];
				matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
				matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
				matrix[j][n-1-i] = temp;
			}
		}
	}
}
