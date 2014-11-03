package leet;

public class SearchRangeOfValueInArray {
	// recursively binary search left boundary and right boundary, 2*(Log(n))->
	// log(n)
	public static int[] searchRange(int[] A, int target) {
		int[] result = { -1, -1 };
		result[0] = searchLeft(A, 0, A.length - 1, target);
		result[1] = searchRight(A, 0, A.length - 1, target);
		return result;
	}

	private static int searchLeft(int[] A, int start, int end, int target) {
		if (start > end) {
			return -1;
		}
		int mid = start + (end - start) / 2;
		if (A[mid] == target) {
			if (mid == 0 || (mid - 1 >= 0 && A[mid] > A[mid - 1]))
				return mid;

			else
				return searchLeft(A, start, mid - 1, target);
		}
		if (A[mid] < target)
			return searchLeft(A, mid + 1, end, target);
		if (A[mid] > target)
			return searchLeft(A, start, mid - 1, target);
		return -1;
	}

	private static int searchRight(int[] A, int start, int end, int target) {
		if (start > end) {
			return -1;
		}
		int mid = start + (end - start) / 2;
		if (A[mid] == target) {
			if (mid == end || (mid + 1 <= end && A[mid] < A[mid + 1]))
				return mid;

			else
				return searchRight(A, mid + 1, end, target);
		}
		if (A[mid] < target)
			return searchRight(A, mid + 1, end, target);
		if (A[mid] > target)
			return searchRight(A, start, mid - 1, target);
		return -1;
	}

	public static void main(String[] args) {
		int[] A = { 5, 7, 7, 8, 8, 10 };
		System.out.println(searchRange(A, 8)[1]);
	}
}
