package leet;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
	/**
	 * move outside circle; adjust savedStart and savedEnd point
	 * 
	 * @param matrix
	 * @return
	 */
	public static List<Integer> spiralOrder(int[][] matrix) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0)
			return list;
		int m = matrix.length;
		int n = matrix[0].length;
		// spiralOrderHelper(matrix, 0, 0, m - 1, n - 1, list);

		int savedStartx = 0;
		int savedStarty = 0;
		int savedEndx = m - 1;
		int savedEndy = n - 1;
		while (savedStartx <= savedEndx) {
			int startx = savedStartx;
			int starty = savedStarty;
			// when m<n, deal with last "-"; or last point
			if (savedStartx == savedEndx) {
				int tempStarty = savedStarty;
				while (tempStarty <= savedEndy) {
					list.add(matrix[savedStartx][tempStarty]);
					tempStarty++;
				}
				return list;
			} else
			// when m > n, deal with last "|";
			if (savedStarty == savedEndy) {
				System.out.println("ere");
				int tempStartx = savedStartx;
				while (tempStartx <= savedEndx) {
					list.add(matrix[tempStartx][savedStarty]);
					tempStartx++;
				}
				return list;
			} else
			// when last square
			if (savedStartx > savedEndx && savedStarty > savedEndy) {
				return list;
			}
			// move right
			while (starty < savedEndy) {
				list.add(matrix[startx][starty]);
				starty++;
			}
			System.out.println("left" + list);
			// move down
			while (startx < savedEndx) {
				list.add(matrix[startx][starty]);
				startx++;
			}
			System.out.println("down" + list);
			// move left
			while (starty > savedStarty) {
				list.add(matrix[startx][starty]);
				starty--;
			}
			// move up
			while (startx > savedStartx) {
				list.add(matrix[startx][starty]);
				startx--;
			}
			System.out.println("up" + list);
			savedStartx++;
			savedStarty++;
			savedEndx--;
			savedEndy--;
		}
		return list;
	}

	/**
	 * same idea as above, better clean code
	 * 
	 * @param matrix
	 * @return
	 */
	public List<Integer> spiralOrder1(int[][] matrix) {

		List<Integer> res = new ArrayList<Integer>();

		if (matrix.length == 0) {
			return res;
		}

		int rowBegin = 0;
		int rowEnd = matrix.length - 1;
		int colBegin = 0;
		int colEnd = matrix[0].length - 1;

		while (rowBegin <= rowEnd && colBegin <= colEnd) {
			// Traverse Right
			for (int j = colBegin; j <= colEnd; j++) {
				res.add(matrix[rowBegin][j]);
			}
			rowBegin++;

			// Traverse Down
			for (int j = rowBegin; j <= rowEnd; j++) {
				res.add(matrix[j][colEnd]);
			}
			colEnd--;

			if (rowBegin <= rowEnd) {
				// Traverse Left
				for (int j = colEnd; j >= colBegin; j--) {
					res.add(matrix[rowEnd][j]);
				}
			}
			rowEnd--;

			if (colBegin <= colEnd) {
				// Traver Up
				for (int j = rowEnd; j >= rowBegin; j--) {
					res.add(matrix[j][colBegin]);
				}
			}
			colBegin++;
		}

		return res;
	}

	public static void main(String[] args) {
		// int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[][] matrix = { { 3 }, { 2 } };
		System.out.println(spiralOrder(matrix));
	}
}
