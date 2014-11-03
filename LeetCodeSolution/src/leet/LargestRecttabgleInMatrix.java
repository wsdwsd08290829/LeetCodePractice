package leet;

import java.util.Arrays;
import java.util.Stack;

public class LargestRecttabgleInMatrix {

	/************** method1 **************/
	/**
	 * ones[i][j] stores the number of contiguous 1s ended at matrix[i][j],
	 * along ith row. e.g. if matrix[i] = "1011101", then ones[i]=1,0,1,2,3,0,1.
	 * This array stores the length of the rectangle, for every possible
	 * bottom-right corner point.
	 * 
	 * @param matrix
	 * @return
	 */
	public int maximalRectangle(char[][] matrix) {
		if (matrix == null)
			return 0;
		int area = 0;
		int rowLen = matrix.length;
		if (rowLen == 0)
			return 0;
		int colLen = matrix[0].length;
		int[][] ones = new int[rowLen][colLen];
		for (int i = 0; i < rowLen; i++) {
			for (int j = 0; j < colLen; j++) {
				if (j == 0) {
					ones[i][j] = matrix[i][j] - '0';
				} else {
					if (matrix[i][j] == '1') {
						System.out.println("i" + i + j);
						ones[i][j] = ones[i][j - 1] + 1;
					}
				}
			}
		}
		Helper.printMatrix(ones);
		// slow O(m*n * m);
		for (int i = 0; i < rowLen; i++) { // O(m)
			for (int j = 0; j < colLen; j++) { // O(n)
				int minHeightIndex = i;
				int minWidth = ones[i][j];
				while (minHeightIndex >= 0) { // O(m)
					minWidth = Math.min(minWidth, ones[minHeightIndex][j]);
					area = Math.max(area, minWidth * (i - minHeightIndex + 1));
					minHeightIndex--;
				}
			}
		}
		return area;
	}

	/************* method2 ***************/
	/**
	 * each row as bottom of histogram, calculate max rect in histogram.
	 */
	public int maximalRectangle1(char[][] matrix) {
		if (matrix == null)
			return 0;
		int area = 0;
		int rowLen = matrix.length;
		if (rowLen == 0)
			return 0;
		int colLen = matrix[0].length;
		int[] prevRow = new int[colLen];
		for (int row = 0; row < rowLen; row++) {
			for (int col = 0; col < colLen; col++) {
				if (matrix[row][col] == '0')
					prevRow[col] = 0;
				else
					prevRow[col] += matrix[row][col] - '0';
			}
			area = Math.max(area, largestRectangleArea1(prevRow));
		}
		return area;
	}

	// from LargestRectangleInHistogram
	public int largestRectangleArea1(int[] height) {
		int max = 0;
		// save increasing index to Stack
		Stack<Integer> increasingIndexStack = new Stack<Integer>();
		// use extra O(n) space, if not,
		// need extra code to deal with remain stack after for loop
		int height1[] = Arrays.copyOf(height, height.length + 1); // last is 0
		for (int i = 0; i < height1.length; i++) {
			if (increasingIndexStack.isEmpty()
					|| height1[i] > height1[increasingIndexStack.peek()]) {
				increasingIndexStack.push(i);
			} else {
				while (!increasingIndexStack.isEmpty()
						&& height1[i] < height1[increasingIndexStack.peek()]) {
					int prevIndex = increasingIndexStack.pop();
					if (increasingIndexStack.isEmpty())
						// the current popped is min to all bars from [0, i)
						// bottom is always smallest
						// also because cannot peek value below
						max = Math.max(height1[prevIndex] * i, max);
					else {
						// !!!not i-prevIndex ,
						// increasingIndexStack.peek() means the left most bar
						// smaller than current bar,
						// between may have higher already removed
						max = Math.max(height1[prevIndex]
								* (i - 1 - increasingIndexStack.peek()), max);
					}
				}
				increasingIndexStack.push(i);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		char[][] matrix = { { '0', '1', '1' }, { '1', '1', '0' } };
		System.out.println(new LargestRecttabgleInMatrix()
				.maximalRectangle1(matrix)); // 0
		char[][] test = { { '0', '0', '0' }, { '0', '0', '0' },
				{ '1', '1', '1' } };
		System.out.println(new LargestRecttabgleInMatrix()
				.maximalRectangle1(test)); // 0
	}
}
