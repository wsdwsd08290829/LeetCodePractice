package leet;

public class SearchInRotatedSortedArray {
	/*************** method1 **********/
	/**
	 * 1. right half increase; (1) target in right half or left half 2. left
	 * half increase;(else all) (1) target in left half or right half
	 * 
	 * @param A
	 * @param target
	 * @return
	 */
	public int search(int[] A, int target) {
		if (A == null || A.length == 0)
			return -1;
		return searchHelper(A, target, 0, A.length - 1);
	}

	// recursive
	private int searchHelper(int[] A, int target, int l, int r) {
		if (l > r)
			return -1;
		int m = l + (r - l) / 2;
		if (A[m] == target) {
			// mid == target
			return m;
		}
		// 6701245
		if (A[m] < A[r]) {
			if (target > A[m] && target <= A[r]) {
				return searchHelper(A, target, m + 1, r);
			} else {
				return searchHelper(A, target, l, m - 1);
			}
		} else
		// 4567012
		{
			if (target >= A[l] && target < A[m]) {
				return searchHelper(A, target, l, m - 1);
			} else {
				return searchHelper(A, target, m + 1, r);
			}
		}

	}

	/**************** method2 ******************/
	// iteration
	public int search1(int[] A, int target) {
		if (A == null || A.length == 0)
			return -1;
		int l = 0, r = A.length - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			if (A[m] == target) {
				// mid == target
				return m;
			}
			// 6701245
			if (A[m] < A[r]) {
				if (target > A[m] && target <= A[r]) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			} else
			// 4567012
			{
				if (target >= A[l] && target < A[m]) {
					r = m - 1;
				} else {
					l = m + 1;
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		SearchInRotatedSortedArray sr = new SearchInRotatedSortedArray();
		int[] A = { 1, 3 };
		System.out.println(sr.search(A, 1));
	}
}
