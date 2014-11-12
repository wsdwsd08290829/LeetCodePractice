package leet;

public class RemoveElement {
	// O(n) compare with remove dups from list or array
	public int removeElement(int[] A, int elem) {
		if (A == null || A.length == 0)
			return 0;
		int i = 0, j = A.length - 1;
		int count = A.length;
		// swap all elem at i to j, if is elem at j, j--
		while (i <= j) {
			if (A[i] == elem) {
				count--;
				while (A[j] == elem && j != i) {
					j--;
					count--;
					if (j <= i)
						break;
				}
				A[i] = A[j];
				A[j] = elem;
				j--;
			}
			i++;
		}
		return count;
	}

	/********* method2 **********/
	public int removeElement1(int[] A, int elem) {
		if (A == null)
			return 0;
		int len = A.length - 1;
		for (int i = 0; i <= len; i++) {
			if (A[i] == elem) {
				A[i--] = A[len--];
			}
		}
		return len + 1;
	}
}
