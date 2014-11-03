package leet;

public class MedianOfTwoArray {
	public static double findMedianSortedArrays(int A[], int B[]) {
		if (A == null || B == null || (A.length == 0 && B.length == 0)) {
			return 0;
		}
		int alen = A.length;
		int blen = B.length;
		int k = (alen + blen) / 2;
		// k is index of combined array where median sits
		if ((alen + blen) % 2 == 1) {
			return (double) findKth(A, 0, alen - 1, B, 0, blen - 1, k);
		} else {
			return ((double) findKth(A, 0, alen - 1, B, 0, blen - 1, k) + (double) findKth(
					A, 0, alen - 1, B, 0, blen - 1, k - 1)) / 2;
		}
	}

	public static double findKth(int[] A, int aStart, int aEnd, int[] B,
			int bStart, int bEnd, int k) {
		int alen = aEnd - aStart + 1;
		int blen = bEnd - bStart + 1;
		// special
		if (alen == 0) {
			return B[bStart + k];
		}
		if (blen == 0) {
			return A[aStart + k];
		}
		if (k == 0) {
			return Math.min(A[aStart], B[bStart]);
		}
		// find proportion of k for A and B
		// ak is "index"(start from 0), # of value = ak + 1;
		int ak = k * alen / (alen + blen);
		// for length: k+1 = ak+1 + bk+1; so:
		int bk = k - ak - 1;
		// to real index
		ak = aStart + ak;
		bk = bStart + bk;
		if (A[ak] > B[bk]) {
			// a left b right
			k = k - (bk - bStart + 1);
			aEnd = ak;
			bStart = bk + 1;
			return findKth(A, aStart, aEnd, B, bStart, bEnd, k);
		} else {
			// a right b left
			k = k - (ak - aStart + 1);
			bEnd = bk;
			aStart = ak + 1;
			return findKth(A, aStart, aEnd, B, bStart, bEnd, k);
		}
	}

	public static void main(String[] args) {
		int[] arr1 = { 1, 3 };
		int[] arr2 = { 2, 4 };

		// double result = findMedianSortedArrays(arr1, arr2);
		double result = findKth(arr1, 0, 1, arr2, 0, 1, 1);
		System.out.println("result" + result);

	}
}
