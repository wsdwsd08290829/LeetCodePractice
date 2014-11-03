package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a +
 * b + c = 0? Find all unique triplets in the array which gives the sum of zero.
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤
 * c) The solution set must not contain duplicate triplets.
 * */

public class ThreeSum {
	public static List<List<Integer>> threeSum(int[] num) {
		if (num == null || num.length < 3)
			return new ArrayList<List<Integer>>();
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		Arrays.sort(num);
		// if contains start and start is first of three
		for (int start = 0; start < num.length - 2; start++) {
			// if duplicate, next
			if (start > 0 && num[start] == num[start - 1])
				continue;
			int i = start + 1, j = num.length - 1;
			while (i < j) {
				if (num[start] + num[i] + num[j] > 0) {
					// remove dup
					while (i < j && num[j - 1] == num[j]) {
						j--;
					}
					j--;
				} else if (num[start] + num[i] + num[j] < 0) {
					while (i < j && num[i + 1] == num[i]) {
						i++;
					}
					i++;
				} else {
					List<Integer> temp = new ArrayList<Integer>();
					temp.add(num[start]);
					temp.add(num[i]);
					temp.add(num[j]);
					result.add(temp);
					//remove dup
					while (i < j && num[j - 1] == num[j]) {
						j--;
					}
					while (i < j && num[i + 1] == num[i]) {
						i++;
					}
					i++;
					j--;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] num = { -2, 0, 0, 2, 2 };
		System.out.println(threeSum(num));
	}
}
