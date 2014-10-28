package leet;

import java.util.ArrayList;
import java.util.List;

/**
 * Find the contiguous subarray within an array (containing at least one number)
 * which has the largest product. For example, given the array [2,3,-2,4], the
 * contiguous subarray [2,3] has the largest product = 6. catch: [-2,3, -4] ->24
 * not 3, need keep minEnd also to adjust -- = +
 * 
 * @author sidawang
 * 
 */
public class MaxProductSubarray {
	public static int maxProductBruteForce(int[] A) {
		// brute force
		List<Integer> results = new ArrayList<Integer>();
		// add all result loop by length to list, then find min O(n^2)
		for (int l = 1; l <= A.length; l++) {
			for (int i = 0; i <= A.length - l; i++) {
				int tempLength = l;
				int tempIndex = i;
				int tempResult = 1;
				while (tempLength > 0) {
					tempResult *= A[tempIndex];
					tempIndex++;
					tempLength--;
				}
				results.add(tempResult);
			}
		}
		System.out.println(results);
		int max = 0;
		for (int result : results) {
			if (result > max)
				max = result;
		}
		return max;
	}

	public static int maxProduct(int[] A) {
		if (A == null || A.length == 0)
			return 0;
		int currMax = A[0];
		// int currMin = A[0];
		// save the maximum subarray end with index i
		int[] maxEnd = new int[A.length];
		int[] minEnd = new int[A.length];
		minEnd[0] = A[0];
		maxEnd[0] = A[0];
		for (int i = 1; i < A.length; i++) {
			// update maxEnd;
			if (A[i] * maxEnd[i - 1] > A[i] || A[i] * minEnd[i - 1] > A[i]) {
				maxEnd[i] = Math.max(A[i] * maxEnd[i - 1], A[i] * minEnd[i - 1]);
			} else {
				maxEnd[i] = A[i];
			}
			// update minEnd;
			if (A[i] * minEnd[i - 1] < A[i] || A[i] * maxEnd[i - 1] < A[i]) {
				minEnd[i] = Math.min(A[i] * minEnd[i - 1], A[i] * maxEnd[i - 1]);
			} else {
				minEnd[i] = A[i];
			}
			// update currMax
			if (currMax < maxEnd[i]) {
				currMax = maxEnd[i];
			}
		}
		return currMax;
	}

	public static void main(String[] args) {
		int[] arr = { 2, 3, 4, -2, 4, 2 };
		System.out.println(maxProduct(arr)); //24
		int[] arr2 = { -2, 3, -4 };
		System.out.println(maxProduct(arr2)); //24
		int[] arr3 = { 7, -3, -4 };
		System.out.println(maxProduct(arr3)); //84
		int[] arr4 = { 2, -5, -2, -4, 3 };
		System.out.println(maxProduct(arr4)); //24
	}
}
