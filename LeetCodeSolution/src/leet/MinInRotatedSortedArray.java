package leet;

/**
 * Find Minimum in Rotated Sorted Array && added for deal with duplicates Log(n)
 */
public class MinInRotatedSortedArray {
	public static int findMin(int[] num) {
		// if not rotated, special case
		if (num[0] < num[num.length - 1])
			return num[0];
		// otherwise rotated
		return findMinHelper(num, 0, num.length - 1);
	}

	private static int findMinHelper(int[] num, int start, int end) {
		// &&: // 10, 1, 10, 10, 10; //"remove first 10,
		// became sorted arr without rotate, need check special case
		if (num[start] < num[end])
			return num[start];
		// start == end in case only one elem in num
		if (end - start == 1 || start == end)
			return Math.min(num[start], num[end]);
		int mid = start + (end - start) / 2;
		// 4,5,6,7,0,1,2, in right half
		if (num[start] < num[mid]) {
			return findMinHelper(num, mid, end);
		}
		// &&: not sure which half // 10, 1, 10, 10
		else if (num[start] == num[mid]) {
			// simulate remove duplicates
			return findMinHelper(num, start + 1, end);
		}
		// left half
		else { // 6,7, 0,1,2,4,5
			return findMinHelper(num, start, mid);
		}
	}

	/**
	 * O(n)
	 * 
	 * @param num
	 * @return
	 */
	public int findMinBruteForce(int[] num) {
		int min = Integer.MAX_VALUE;
		for (int i : num) {
			if (i < min) {
				min = i;
			}
		}
		return min;
	}

	public static void main(String[] args) {
		int[] arr = { 4, 5, 6, 7, 0, 1, 2 };
		System.out.println(findMin(arr));
		int[] arr1 = { 6, 7, 0, 1, 2, 4, 5 };
		System.out.println(findMin(arr1));
		int[] arr2 = { 0, 1, 2, 4, 5, 6, 7 };
		System.out.println(findMin(arr2));
		// if allow duplicates
		int[] arr3 = { 1, 1, 0, 1 };
		System.out.println(findMin(arr3));
		int[] arr4 = { 10, 1, 10, 10, 10 };
		System.out.println(findMin(arr4));
	}
}
