package leet;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * You may assume that A has enough space
 * 
 * @author sidawang
 * 
 */
public class MergeSortedArrayBToA {
	public void merge(int A[], int m, int B[], int n) {
		// from back to head, last index m+n-1
		int end = m + n - 1;
		while (m > 0 && n > 0) {
			if (A[m - 1] > B[n - 1]) {
				A[end--] = A[m - 1];
				m--;
				if (m == 0)
					break;
			} else {
				A[end--] = B[n - 1];
				n--;
				if (n == 0)
					break;
			}
		}
		// if m>0, already in correct place
		// if n>0, copy to A
		while (n > 0) {
			A[end--] = B[n - 1];
			n--;
		}
	}
}
