package leet;

import java.util.HashSet;
import java.util.Set;

public class SudokuSolver {
	public void solveSudoku(char[][] board) {
		// solveSudokuHelper(board);
		solveSudokuHelper1(board, 0, 0);
	}

	/************** method 1 ***********/
	private boolean solveSudokuHelper(char[][] board) {
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				if (board[row][col] == '.') {
					// try all values
					for (int tmp = 1; tmp <= 9; tmp++) {
						board[row][col] = (char) ('0' + tmp);
						if (isValid(board, row, col)) {
							if (solveSudokuHelper(board)) {
								return true;
							}
						}
						// backtracking
						board[row][col] = '.';
					}
					// !!!if all values fail because subsequent calls did not
					// return true, then return false, to upper level, continue
					// next tmp;
					return false;
				}
			}
		}
		return true;
	}

	private boolean isValid(char[][] board, int row, int col) {
		// check column
		for (int i = 0; i < 9; i++) {
			if (i != row && board[row][col] == board[i][col])
				return false;
		}
		// check row
		for (int i = 0; i < 9; i++) {
			if (i != col && board[row][col] == board[row][i])
				return false;
		}
		// check 3*3
		for (int i = row / 3 * 3; i < row / 3 * 3 + 3; i++) {
			for (int j = col / 3 * 3; j < col / 3 * 3 + 3; j++) {
				if ((i != row || j != col) && board[i][j] == board[row][col])
					return false;
			}
		}
		return true;
	}

	/********** method 2 *************/
	private boolean solveSudokuHelper1(char[][] board, int row, int col) {
		if (col >= 9) {
			row++;
			col = 0;
		}
		if (row == 9)
			return true;
		if (board[row][col] == '.') {
			for (int tmp = 1; tmp <= 9; tmp++) {
				board[row][col] = (char) ('0' + tmp);
				if (isValid(board, row, col)) {
					if (solveSudokuHelper1(board, row, col + 1)) {
						return true;
					}
				}
				// backtracking
				board[row][col] = '.';
			}
			return false;
		}else{
			return solveSudokuHelper1(board, row, col+1);
		}
	}

	public static void main(String[] args) {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' },
				{ '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' },
				{ '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' },
				{ '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' },
				{ '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		SudokuSolver ss = new SudokuSolver();
		ss.solveSudoku(board);
		Helper.printMatrix(board);
	}
}
