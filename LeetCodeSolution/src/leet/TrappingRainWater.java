package leet;

public class TrappingRainWater {
	/**
	 * each val in array means height of bar, located at x(index of array) find
	 * water could traped by bar
	 * 
	 * @param A
	 * @return
	 */
	public static int trap(int[] A) {
		if (A == null || A.length <= 2) {
			return 0;
		}
		int len = A.length;
		int[] barMaxLeft = new int[len];
		int count = 0;
		// get highest bar to the left of current bar
		barMaxLeft[0] = A[0];
		for (int i = 1; i < A.length; i++) {
			barMaxLeft[i] = Math.max(A[i - 1], barMaxLeft[i - 1]);
		}

		// int[] barMaxRight = new int[len];
		int maxRight = A[len - 1]; // save space instead of above
		// for bars not including first and last, get trapped water for each
		for (int i = len - 2; i > 0; i--) {
			// count the water could traped above this bar:
			// maxLeft + maxRight - height of this bar
			if (A[i] < maxRight && A[i] < barMaxLeft[i]) {
				count += Math.min(maxRight, barMaxLeft[i]) - A[i];
			}
			// update current max right bar for next left bar
			maxRight = Math.max(A[i], maxRight);
		}
		return count;
	}

	public static void main(String[] args) {
		int[] trap = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(trap(trap));
	}
}
