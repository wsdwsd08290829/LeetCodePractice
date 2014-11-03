package leet;

/**
 * @author sidawang
 * 
 * 
 * 
 */
public class MaxProfit {
	/******** problem1(one transaction) method1 **********/
	/**
	 * save current minSoFar as buy(greedy), then update max, if curr-minSoFar
	 * is larger
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit11(int[] prices) {
		if (prices == null || prices.length == 0)
			return 0;

		int max = 0;
		int minSoFar = Integer.MAX_VALUE;

		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minSoFar) {
				minSoFar = prices[i];
			} else {
				max = Math.max(max, prices[i] - minSoFar);
			}
		}
		return max;
	}

	/******** problem1 method2 **********/
	/**
	 * similar to longest continuous sum in array; O(n), dynamic programming
	 * copy： local[i+1]=max(local[i]+prices[i+1]-price[i],0),
	 * global[i+1]=max(local[i+1],global[i])
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit12(int[] prices) {
		if (prices == null || prices.length < 2)
			return 0;

		int maxGlobal = 0;
		//save max profit if sell at current day
		int prevMaxLocal = 0;

		for (int i = 1; i < prices.length; i++) {
			prevMaxLocal = Math.max(0, prices[i]-prices[i-1]+prevMaxLocal);
			maxGlobal = Math.max(maxGlobal, prevMaxLocal);
		}
		return maxGlobal;
	}

	/******** problem3(multiple transaction) method1 **********/
	/**
	 * get max profit of multiple transaction(buy sell pair) Alg: greedy
	 * Complexity: O(n^2) -> O(n)
	 * 
	 * @param prices
	 *            : stock price ordered by day(index)
	 * @return max profit
	 */
	public static int maxProfit31(int[] prices) {
		if (prices == null || prices.length < 2) {
			return 0;
		}
		int sellIndex = 1, buyIndex = 0;
		int max = 0;
		while (buyIndex < prices.length - 1) {
			// find min sellIndex that make it larger then buyIndex, may not
			// find(sellIndex == prices.length)
			while (sellIndex < prices.length
					&& prices[sellIndex] <= prices[buyIndex]) {
				// greedy buyIndex, price at buy is decreasing, then use this
				// new buyIndex
				buyIndex = sellIndex; // incease to O(n) from O(n^2)
				sellIndex++;
			}
			if (sellIndex <= prices.length - 1) { // find valid sellIndex
				// eg. 10, 15, 18, 8, 16; choose (10, 18), not (10, 15);
				// //greedy sellIndex
				while (sellIndex < prices.length - 1
						&& prices[sellIndex + 1] > prices[sellIndex]) {
					sellIndex++;
				}
				max += prices[sellIndex] - prices[buyIndex];
				// update new buy&sell Index;
				buyIndex = sellIndex + 1;
				sellIndex += 2;
			}
			/*
			 * else{ //already covered //not find valid sellIndex: eg. 7,6,5,6;
			 * //not valid till buyIndex = 2; //7,6,5, no valid sellIndex
			 * buyIndex++; sellIndex=buyIndex+1; }
			 */
		}
		return max;
	}

	public static void main(String[] args) {
		int[] prices = { 10, 15, 8, 16, 6, 5, 6 };
		System.out.println(maxProfit31(prices)); // 14
		int[] prices1 = { 10, 15, 18, 8, 16 };
		System.out.println(maxProfit31(prices1)); // 16
		int[] prices2 = { 10, 15, 13, 18 };
		System.out.println(maxProfit31(prices2));// 10
		int[] prices3 = { 10, 15, 7, 6, 5 };
		System.out.println(maxProfit31(prices3));// 5
	}
}
