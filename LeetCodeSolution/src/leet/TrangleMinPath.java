package leet;

import java.util.List;

public class TrangleMinPath {
	/********* method1 top to bottom ***********/
	// dynamic programming
	// sum[i][j]=min(sum[i-1][j-1],sum[i-1][j])+triangle[i][j]
	public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle == null)
			return 0;
		int rows = triangle.size();
		int sizeOfLastRow = triangle.get(triangle.size() - 1).size();
		int[] res = new int[sizeOfLastRow];
		res[0] = triangle.get(0).get(0); // top val
		for (int i = 1; i < rows; i++) { // from second row
			int sizeOfCurrRow = triangle.get(i).size();
			// !!backward to use previous value
			for (int j = sizeOfCurrRow - 1; j >= 1; j--) { // j=i-1 same
				if (j >= i) {
					res[j] = res[j - 1] + triangle.get(i).get(j);
				} else {
					res[j] = Math.min(res[j - 1], res[j])
							+ triangle.get(i).get(j);
				}
			}
			res[0] += triangle.get(i).get(0); // update new row first
		}
		int min = Integer.MAX_VALUE;
		for (int i : res) {
			if (i < min) {
				min = i;
			}
		}
		return min;
	}

	/******** method2 bottom up ***********/
	// better, donot need to scan last row
	// sum(i,j) =Math.min(sum(i+1, j), sum(i+1, j+1)) + triangle(i,j)
	public int minimumTotal1(List<List<Integer>> triangle) {
		if (triangle == null)
			return 0;
		int rows = triangle.size();
		int sizeOfLastRow = triangle.get(triangle.size() - 1).size();
		int[] res = new int[sizeOfLastRow];
		// init last row to res;
		for (int i = 0; i < sizeOfLastRow; i++) {
			res[i] = triangle.get(rows - 1).get(i);
		}
		// i is row index
		for (int i = rows - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
			}
		}
		return res[0];
	}
}
