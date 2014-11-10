package leet;

import java.util.Arrays;

public class ThreeSumClosest {
	public int threeSumClosest(int[] num, int target) {
		if (num == null || num.length < 3)
			return 0;
		Arrays.sort(num);
		int sum = 0;
		int minDiff = Integer.MAX_VALUE;
		// 1 for each in num as start(remove dup), i = start +1, j=end;
		for (int start = 0; start < num.length - 2; start++) {
			// !!cannot use start, start+1
			if (start > 1 && num[start] == num[start - 1]) {
				continue;
			}
			int i = start + 1;
			int j = num.length - 1;
			// try all i, j(greedy)
			while (i < j) {
				// check minDiff & update sum
				if (Math.abs(target - num[start] - num[i] - num[j]) < Math
						.abs(minDiff)) { // both need abs
					minDiff = target - num[start] - num[i] - num[j];
					sum = num[start] + num[i] + num[j];
				}
				// remove dup
				if (num[start] + num[i] + num[j] < target) {
					while (i < j && num[i] == num[i + 1]) {
						i++;
					}
					i++;
				} else {
					while (i < j && num[j] == num[j - 1]) {
						j--;
					}
					j--;
				}
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		ThreeSumClosest tsc = new ThreeSumClosest();
		int[] num = { 1, 1, 1, 0 };
		System.out.println(tsc.threeSumClosest(num, -100)); // 2
		int[] num1 = { 1, 1, -1, -1, 3 };
		System.out.println(tsc.threeSumClosest(num1, -1)); // -1

	}
}
