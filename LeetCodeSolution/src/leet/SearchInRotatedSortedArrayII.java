package leet;

public class SearchInRotatedSortedArrayII {
	/*************** method1 **********/
	public boolean search(int[] A, int target) {
		if (A == null || A.length == 0)
			return false;
		return searchHelper(A, target, 0, A.length - 1);
	}

	// recursive
	private boolean searchHelper(int[] A, int target, int l, int r) {
		if (l > r)
			return false;
		int m = l + (r - l) / 2;
		if (A[m] == target) {
			// mid == target
			return true;
		}
		// /////////////
		while (l < m && A[l] == A[m]) {
			l++;
		}
		while (m < r && A[r] == A[m]) {
			r--;
		}
		// ////////////
		// 6701245
		if (A[m] < A[r] || m == r) {
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

	// 1,2,3,3,3,3,3
	// 3,1,2,3,3,3,3
	/**************** method2 ******************/
	// iteration
	public boolean search1(int[] A, int target) {
		if (A == null || A.length == 0)
			return false;
		int l = 0, r = A.length - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			if (A[m] == target) {
				// mid == target
				return true;
			}
			// /////////////
			while (l < m && A[l] == A[m]) {
				l++;
			}
			while (m < r && A[r] == A[m]) {
				r--;
			}
			// ////////////
			// 113-> 311, 011-> 110
			if (A[m] < A[r] || m == r) { // ///////add m==r
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
		return false;
	}
}
