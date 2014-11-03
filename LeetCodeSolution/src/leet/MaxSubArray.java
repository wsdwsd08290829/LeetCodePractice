package leet;

/**
 * contiguous subarray within an array (containing at least one number) which
 * has the largest sum.
 * 
 * @author sidawang
 * 
 */
public class MaxSubArray {
	/**
	 * similar, buy sell stock once; dynamic programming; O(n) Local(i) =
	 * max(A[i], Local(i-1)+A[i]-A[i-1])
	 * 
	 * @param A
	 * @return
	 */
	public int maxSubArray(int[] A) {
		if (A == null || A.length == 0)
			return 0;
		int maxGlobal = A[0];
		// save max sum if sub array end at current index
		int maxLocal = A[0];
		for (int i = 1; i < A.length; i++) {
			maxLocal = Math.max(A[i], maxLocal + A[i]);
			maxGlobal = Math.max(maxLocal, maxGlobal);
		}
		return maxGlobal;
	}

	public static void main(String[] args) {
		System.out.println(new MaxSubArray().maxSubArray(new int[] { 1, 2 }));
	}
}
