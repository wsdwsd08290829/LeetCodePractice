package leet;

public class FirstMissingPositive {
	/**
	 * 
	 * @param A
	 * @return
	 */
	// place A[i] to A[i]-1 index ( 2 to index 1); loop for A[index] !=index +1;
	public int firstMissingPositive(int[] A) {
		if (A == null)
			return 1;
		int i = 0;
		while (i < A.length) {
			// in range && not already in current position && current val !=
			// target val, other wise infinite loop
			if (A[i] - 1 < A.length && A[i] - 1 >= 0 && A[i] - 1 != i
					&& A[A[i] - 1] != A[i]) {
				// swap do not increase index
				// cannot use one var, cause two val to be swapped both use i
				int temp = A[i];
				int temp2 = A[A[i] - 1];
				A[A[i] - 1] = temp;
				A[i] = temp2;
			} else {
				// increase only out of range, or val same as target or val ==
				// current pos+1
				i++;
			}
		}
		for (int j = 0; j < A.length; j++) {
			if (A[j] != j + 1) {
				return j + 1;
			}
		}
		return A.length + 1;
	}

	public static void main(String[] args) {
		FirstMissingPositive fp = new FirstMissingPositive();
		int A[] = { 2, 1 };
		System.out.println(fp.firstMissingPositive(A));
	}
}
