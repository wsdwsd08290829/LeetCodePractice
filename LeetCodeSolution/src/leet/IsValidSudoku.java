package leet;

import java.util.HashSet;
import java.util.Set;

/**
 * check is valid sudoku board The Sudoku board could be partially filled, where
 * empty cells are filled with the character '.'
 * 
 * @author sidawang
 * 
 */
public class IsValidSudoku {
	public boolean isValidSudoku(char[][] board) {
		if (board == null || board.length != 9 || board[0].length != 9)
			return false;
		Set<Character> set1 = new HashSet<Character>();
		Set<Character> set2 = new HashSet<Character>();
		// check columns and rows
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				// i as row, check ith row is valid
				if (set1.contains(board[i][j]) && board[i][j] != '.') {
					return false;
				} else {
					set1.add(board[i][j]);
				}
				// i as column
				if (set2.contains(board[j][i]) && board[j][i] != '.') {
					return false;
				} else {
					set2.add(board[j][i]);
				}
			}
			set1.clear();
			set2.clear();
		}
		// check 3*3 blocks
		for (int i = 0; i <= 6; i = i + 3) {
			for (int j = 0; j <= 6; j = j + 3) {
				for (int innerI = 0; innerI < 3; innerI++) {
					for (int innerJ = 0; innerJ < 3; innerJ++) {
						int indexI = i + innerI;
						int indexJ = j + innerJ;
						if (set1.contains(board[indexI][indexJ])
								&& board[indexI][indexJ] != '.') {
							return false;
						} else {
							set1.add(board[indexI][indexJ]);

						}
					}
				}
				set1.clear();
			}
		}
		return true;
	}
}
