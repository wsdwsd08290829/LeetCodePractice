package leet;

/**
 * find min candies to satisfy:1. Children with a higher rating get more candies
 * than their neighbors; 2. Each child must have at least one candy.
 * 
 * @author sidawang
 * 
 */
public class Candy {
	public static int candy(int[] ratings) {
		if (ratings == null || ratings.length == 0)
			return 0;
		if (ratings.length == 1)
			return 1;
		int len = ratings.length;
		int count = 0;
		int[] candy = new int[len];
		candy[0] = 1;
		for (int i = 1; i < len; i++) {
			// make i correct to its left
			if (ratings[i] > ratings[i - 1]) {
				candy[i] = candy[i - 1] + 1;
			} else {
				candy[i] = 1;
			}
		}
		// start form second to last
		for (int i = len - 2; i >= 0; i--) {
			int cur = 1;
			// make i correct to its right
			if (ratings[i] > ratings[i + 1]) {
				cur = candy[i + 1] + 1;
			}
			// in case cur is larger on first pass, cannot break first pass
			candy[i] = Math.max(cur, candy[i]);
			count += Math.max(cur, candy[i]);
		}
		// add last to result
		count += candy[len - 1];
		return count;
	}

	public static void main(String[] args) {
		int[] ratings = { 5, 3, 1 };
		System.out.println(candy(ratings));
	}
}
