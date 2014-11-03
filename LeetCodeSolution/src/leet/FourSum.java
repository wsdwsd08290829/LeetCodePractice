package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/** 
 * all list of four value add to target
 * no duplicate, value in list is increasing
 * @author sidawang
 *
 */
public class FourSum {
	public static List<List<Integer>> fourSum(int[] num, int target) {
		Arrays.sort(num);
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = 0; i < num.length - 3; i++) {
			for (int j = i + 1; j < num.length - 2; j++) {
				//deal with duplicates for i and j
				if (i > 0 && num[i] == num[i - 1]) {
					break;
				}
				if (j > i + 1 && num[j] == num[j - 1]) {
					continue;
				}
				//greedy try third and fourth like three sum
				int third = j + 1;
				int fourth = num.length - 1;
				while (third < fourth) {
					if (num[i] + num[j] + num[third] + num[fourth] > target) {
						while (third < fourth && num[fourth] == num[fourth - 1]) {
							fourth--;
						}
						fourth--;
					} else if (num[i] + num[j] + num[third] + num[fourth] < target) {
						while (third < fourth && num[third] == num[third + 1]) {
							third++;
						}
						third++;
					} else {
						ArrayList<Integer> temp = new ArrayList<Integer>();
						temp.add(num[i]);
						temp.add(num[j]);
						temp.add(num[third]);
						temp.add(num[fourth]);
						result.add(temp);
						while (num[fourth] == num[fourth - 1] && third < fourth) {
							fourth--;
						}
						while (num[third] == num[third + 1] && third < fourth) {
							third++;
						}
						third++;
						fourth--;
					}
				} // end while
			} // for loop j
		} // for loop i
		return result;
	}

	public static void main(String[] args) {
		// int[] num = {-2, -1, 0, 0, 1, 2};
		int[] num = { 0, 0, 0, 0 };
		System.out.println(fourSum(num, 0));
	}
}
