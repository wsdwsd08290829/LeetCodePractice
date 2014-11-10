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

	/************ method2 **************/
	// http://blog.csdn.net/linhuanmars/article/details/20593391
	// 就是先用二分查找找到其中一个target，然后再往左右找到target的边缘
	public int[] searchRange1(int[] A, int target) {
		int[] res = new int[2];
		res[0] = -1;
		res[1] = -1;
		if (A == null || A.length == 0) {
			return res;
		}
		int l = 0;
		int r = A.length - 1;
		int m = (l + r) / 2;
		while (l <= r) {
			m = (l + r) / 2;
			if (A[m] == target) {
				res[0] = m;
				res[1] = m;
				break;
			} else if (A[m] > target) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		if (A[m] != target)
			return res;
		int newL = m;
		int newR = A.length - 1;
		while (newL <= newR) {
			int newM = (newL + newR) / 2;
			if (A[newM] == target) {
				newL = newM + 1;
			} else {
				newR = newM - 1;
			}
		}
		res[1] = newR;
		newL = 0;
		newR = m;
		while (newL <= newR) {
			int newM = (newL + newR) / 2;
			if (A[newM] == target) {
				newR = newM - 1;
			} else {
				newL = newM + 1;
			}
		}
		res[0] = newL;

		return res;
	}

	public static void main(String[] args) {
		int[] A = { 5, 7, 7, 8, 8, 10 };
		System.out.println(searchRange(A, 8)[1]);
	}
}
