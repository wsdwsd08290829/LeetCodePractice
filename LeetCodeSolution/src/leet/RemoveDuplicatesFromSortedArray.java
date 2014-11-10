package leet;

/**
 * A = [1,1,2],should return length = 2, and A is now [1,2] Do not allocate
 * extra space for another array, you must do this in place with constant
 * memory.
 * 
 * @author sidawang
 * 
 */
// !! eager assignment
public class RemoveDuplicatesFromSortedArray {
	public int removeDuplicates(int[] A) {
		if (A == null || A.length == 0)
			return 0;
		int index = 1;
		for (int i = 1; i < A.length; i++) {
			if (A[i] != A[i - 1]) {
				A[index++] = A[i];
			}
		}
		return index;
	}

	/******************* problem2: duplicates are allowed at most twice ***************/
	public int removeDuplicates2(int[] A) {
		if (A == null || A.length == 0)
			return 0;
		int index = 1;
		int count = 0; // count # of duplicates found

		for (int i = 1; i < A.length; i++) {
			if (A[i] == A[i - 1] && ++count < 2) {
				A[index++] = A[i];
				// if count >= 2; just increase i, //==continue
			} else if (A[i] != A[i - 1]) {
				count = 0;
				A[index++] = A[i];
			}
		}
		return index;
	}
}