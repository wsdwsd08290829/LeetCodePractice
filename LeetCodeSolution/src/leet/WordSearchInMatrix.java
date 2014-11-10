package leet;

public class WordSearchInMatrix {
	/**
	 * complexity: try start O(mn); each start need O(mn) search; total: O(m^2*
	 * n^2)
	 * 
	 * @param board
	 * @param word
	 * @return
	 */
	public boolean exist(char[][] board, String word) {
		if (board == null || board.length == 0)
			return false;
		boolean[][] check = new boolean[board.length][board[0].length];
		// try all start position
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (existHelper(board, word, 0, i, j, check))
					return true;
			}
		}
		return false;
	}

	// !! lazy check is common technique in recursive calls, especially matrix
	// boundary check
	private boolean existHelper(char[][] board, String word, int index, int i,
			int j, boolean[][] check) {
		if (index == word.length())
			return true;
		// lazy check boundary, used(check = true), equals
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length
				|| check[i][j] || word.charAt(index) != board[i][j])
			return false;
		check[i][j] = true;
		// eager check need more code
		boolean res = existHelper(board, word, index + 1, i - 1, j, check)
				|| existHelper(board, word, index + 1, i + 1, j, check)
				|| existHelper(board, word, index + 1, i, j - 1, check)
				|| existHelper(board, word, index + 1, i, j + 1, check);
		// if allow duplicates;
		// if charAt(index) == charAt(index+1)
		// res = res ||sexistHelper(board, word, index + 1, i, j, check
		check[i][j] = false; // !! backtrack here;
		return res;
	}
}
