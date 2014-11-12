package leet;

/**
 * find the minimum number of steps required to convert word1 to word2;
 * operations allowed(insert, delete, replace)
 * 
 * @author sidawang
 * 
 */
public class EditDistance {
	// dynamic programming: t(i,j)
	// t(i-1,j-1) if (i == j)
	// t(i-1,j)+1 (delete from word1), t(i,j-1)+1 (delete from word 2),
	// t(i-1,j-1)+1 (replace)
	// delete <=> insert

	// two dimension array
	public int minDistance(String word1, String word2) {
		if (word1 == null || word2 == null)
			return 0;
		// t(i,j) =Min of { remove: t(i-1, j) +1, insert: or t(i-1,j-1)+1
		int[][] table = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i < word1.length(); i++) {
			table[i + 1][0] = i + 1;
		}
		for (int i = 0; i < word2.length(); i++) {
			table[0][i + 1] = i + 1;
		}
		for (int i = 0; i < word1.length(); i++) {
			for (int j = 0; j < word2.length(); j++) {
				if (word1.charAt(i) == word2.charAt(j)) {
					table[i + 1][j + 1] = table[i][j];
				} else {
					table[i + 1][j + 1] = Math.min(
							Math.min(table[i][j + 1] + 1, table[i + 1][j] + 1),
							table[i][j] + 1);
				}
			}
		}
		return table[word1.length()][word2.length()];
	}

	/***** method2 ******/
	// refer to http://blog.csdn.net/linhuanmars/article/details/24213795
	// same idea use one dimension array, beacuse new row only use result of
	// previous row
	// t(i,j), i is hidden, j use for shorter word;
	// !! Space O(min(m, n)), m,n is length of word1 and word2;Time O(mn)

	public int minDistance1(String word1, String word2) {
		if (word1 == null || word2 == null)
			return 0;
		if (word1 == null || word2 == null)
			return 0;
		String minWord = word1.length() > word2.length() ? word2 : word1;
		// if w1Len == w2len => error!!!
		// String maxWord = word1.length() > word2.length() ? word1 : word2;
		String maxWord = word1.length() > word2.length() ? word1 : word2;
		int[] result = new int[minWord.length() + 1];
		for (int i = 0; i < minWord.length(); i++) {
			// remove from min to get max
			result[i + 1] = i + 1; // maxWord as 0, => t(0,j)
		}
		result[0] = 0; // !!
		for (int i = 0; i < maxWord.length(); i++) {
			// newresult for new row as i+1 (with help of prev row(result) as i
			int[] newResult = new int[minWord.length() + 1];
			newResult[0] = i + 1;// insert to min to get max
			for (int j = 0; j < minWord.length(); j++) {
				if (maxWord.charAt(i) == minWord.charAt(j)) {
					newResult[j + 1] = result[j];
				} else {
					newResult[j + 1] = Math.min(
							Math.min(result[j + 1] + 1, newResult[j] + 1),
							result[j] + 1);
				}
			}

			result = newResult;
		}
		return result[minWord.length()];
	}
}
