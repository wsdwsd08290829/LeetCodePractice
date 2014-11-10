package leet;

/*
 * Given a sorted array and a target value, 
 * return the index if the target is found. 
 * If not, return the index 
 * where it would be if it were inserted in order.
 *  may assume no duplicates
 */
public class SearchInsertPosition {
	/************* method 1 **************/
	public int searchInsert(int[] A, int target) {
		int l = 0;
		int r = A.length - 1;
		return searchInsertHelper(A, target, l, r);
	}

	private int searchInsertHelper(int[] A, int target, int l, int r) {
		if (l > r)
			return l;
		int m = l + (r - l) / 2;
		if (A[m] == target) {
			return m;
		}
		if (A[m] < target) {
			return searchInsertHelper(A, target, m + 1, r);
		} else {
			return searchInsertHelper(A, target, l, m - 1);
		}

	}

	/************* method 2 **************/
	// iteration or recursive by pass in start end index
	public int searchInsert1(int[] A, int target) {
		int l = 0;
		int r = A.length - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			if (A[m] == target) {
				return m;
			}
			if (A[m] < target) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return l;
	}

	public static void main(String[] args) {
		int[] A = { 1, 3 };
		SearchInsertPosition sip = new SearchInsertPosition();
		System.out.println(sip.searchInsert(A, 2));
	}
}
