package leet;

import java.util.LinkedList;
import java.util.Queue;

public class SurroundedORegionByX {
	public void solve(char[][] board) {
		if (board == null)
			return;
		if (board.length <= 2 || board[0].length <= 2)
			return;
		// check left and right column border
		for (int i = 0; i < board.length; i++) { // each row
			fill1(board, i, 0);
			fill1(board, i, board[0].length - 1);
		}
		// check top and bottom border
		for (int i = 0; i < board[0].length; i++) {
			fill1(board, 0, i);
			fill1(board, board.length - 1, i);
		}
		// mark O to X, D to O;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 'O')
					board[i][j] = 'X';
				if (board[i][j] == 'D') {
					board[i][j] = 'O';
				}
			}
		}
	}

	// lazy detect, dfs, stack over flow!!
	public void fill(char[][] board, int row, int col) {
		// out of boundary
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length)
			return;
		// if not 'O'
		if (board[row][col] != 'O')
			return;
		// if is 'O': mark D, check neighbors
		board[row][col] = 'D'; // dead
		fill(board, row - 1, col);
		fill(board, row + 1, col);
		fill(board, row, col - 1);
		fill(board, row, col + 1);
	}

	// lazy bfs
	public void fill1(char[][] board, int i, int j) {
		// if not 'O'
		if (board[i][j] != 'O')
			return;
		// if is 'O': mark D, check neighbors
		Queue<Integer> q = new LinkedList<Integer>();
		board[i][j] = 'D';
		q.add(i * board[0].length + j);
		while (!q.isEmpty()) {
			int sum = q.remove();
			int row = sum / board[0].length;
			int col = sum % board[0].length;
			// board[row][col] = 'D'; // too slow here, need eagerly mark
			if (isValidO(board, row - 1, col)) {
				q.add((row - 1) * board[0].length + col);
			}
			if (isValidO(board, row + 1, col)) {
				q.add((row + 1) * board[0].length + col);
			}
			if (isValidO(board, row, col - 1)) {
				q.add(row * board[0].length + col - 1);
			}
			if (isValidO(board, row, col + 1)) {
				q.add(row * board[0].length + col + 1);
			}
		}
	}

	public boolean isValidO(char[][] board, int row, int col) {
		if (row >= 1 && row < board.length - 1 && col >= 1
				&& col < board[0].length - 1) {
			if (board[row][col] == 'O') {
				board[row][col] = 'D';
				System.out.println(row + " #################s " + col);
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		char[][] board = { { 'X', 'O', 'X', 'O', 'X', 'O' },
				{ 'O', 'X', 'O', 'X', 'O', 'X' },
				{ 'X', 'O', 'X', 'O', 'X', 'O' },
				{ 'O', 'X', 'O', 'X', 'O', 'X' } };
		SurroundedORegionByX so = new SurroundedORegionByX();
		so.solve(board);
		Helper.printMatrix(board);

	}
}
