package leet;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
	public List<String[]> solveNQueens(int n) {
		List<String[]> list = new ArrayList<String[]>();
		if (n <= 0)
			return list;
		int[] place = new int[n]; // index-column, val-row
		nQueens(0, place, list);
		return list;
	}

	/**
	 * 
	 * @param colIndex
	 *            : current column
	 * @param place
	 *            : result board array: index-column, val-row
	 * @param list
	 */
	private void nQueens(int colIndex, int[] place, List<String[]> list) {
		int n = place.length;
		// try all rows of current column
		for (int i = 0; i < n; i++) {
			if (isSafe(place, i, colIndex)) {
				place[colIndex] = i;
				// when final column success, save board
				if (colIndex == n - 1) {
					saveBoard(place, list);
				} else {
					// when current col success, next column
					nQueens(colIndex + 1, place, list);
				}
			} //otherwise try next row
		}
		// backtrack(col index to previous)
	}

	private void saveBoard(int[] place, List<String[]> list) {
		int n = place.length;
		String str = "";
		String s[] = new String[n];
		for (int placeCol = 0; placeCol < n; placeCol++) {
			int placeRow = place[placeCol];
			s[placeRow] = "";
			for (int strIndex = 0; strIndex < n; strIndex++) {
				if (strIndex == placeCol) {
					s[placeRow] += "Q";
				} else {
					s[placeRow] += '.';
				}
			}
		}
		list.add(s);
	}

	public boolean isSafe(int[] place, int row, int col) {
		// check col; // no need;
		// check diagonal
		for (int colIndex = 0; colIndex < col; colIndex++) {
			// check row
			if (place[colIndex] == row)
				return false;
			// check diagonal
			int rowIndex = place[colIndex];
			if ((col - colIndex) == Math.abs(row - rowIndex)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		NQueens nq = new NQueens();
		nq.solveNQueens(4);
	}
}
