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
		// save max profit if sell at current day
		int prevMaxLocal = 0;

		for (int i = 1; i < prices.length; i++) {
			prevMaxLocal = Math
					.max(0, prices[i] - prices[i - 1] + prevMaxLocal);
			maxGlobal = Math.max(maxGlobal, prevMaxLocal);
		}
		return maxGlobal;
	}

	/******************************************************************************************/
	/********** problem2(at most 2 transaction-> generalized to k) method1 *******/
	/**
	 * 
	 * @param prices
	 * @return
	 */
	// http://blog.csdn.net/linhuanmars/article/details/23236995
	// global[i][j]=max(local[i][j],global[i-1][j])，
	// local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)
	// i means till day i, finished j transactions
	// O(n) * k, k is max # of transactions allowed
	public int maxProfit21(int[] prices) {
		if (prices == null || prices.length <= 1)
			return 0;
		int[] local = new int[3];
		int[] global = new int[3];
		for (int i = 0; i < prices.length - 1; i++) {
			int diff = prices[i + 1] - prices[i];
			// must backward, otherwise previous value will not be available
			for (int j = 2; j >= 1; j--) {
				local[j] = Math.max(global[j - 1] + (diff > 0 ? diff : 0),
						local[j] + diff);
				global[j] = Math.max(local[j], global[j]);
			}
		}
		return global[2];
	}

	// same as above, waste space, more easy to understand
	public int maxProfit22(int[] prices) {
		if (prices == null || prices.length <= 1)
			return 0;
		int n = 2;// max # of transaction
		int[][] local = new int[prices.length][n + 1];
		int[][] global = new int[prices.length][n + 1];
		for (int i = 1; i < prices.length; i++) {
			for (int j = 1; j <= n; j++) {
				int diff = prices[i] - prices[i - 1];
				local[i][j] = Math.max(local[i - 1][j] + diff,
						global[i - 1][j - 1] + Math.max(diff, 0));
				global[i][j] = Math.max(global[i - 1][j], local[i][j]);
			}
		}
		return global[prices.length - 1][2];
	}

	/************ problem2, method2 **********/
	// http://blog.csdn.net/laozhaokun/article/details/37737175
	// 3*O(n)
	// cannot be generalize to most k transactions
	public int maxProfit23(int[] prices) {
		if (prices == null || prices.length <= 1)
			return 0;
		int max = 0;
		// f(forward) save max profit from day 0 to i
		// b(backward) save max profit from i to last day
		int[] f = new int[prices.length];
		int[] b = new int[prices.length];
		// fill f, use dynamic programming
		int local = 0;
		for (int i = 1; i < prices.length; i++) {
			local = Math.max(local + prices[i] - prices[i - 1], 0);
			f[i] = Math.max(local, f[i - 1]);
		}
		// fill b, use greedy
		int currMax = prices[prices.length - 1];
		for (int i = prices.length - 2; i >= 0; i--) {
			currMax = Math.max(currMax, prices[i]);
			b[i] = Math.max(b[i + 1], currMax - prices[i]);
		}

		// find max combination that make global max;
		for (int i = 0; i < prices.length - 1; i++) {
			// max = Math.max(max, f[i]+b[i+1]);
			// exact two transaction
			// maybe sell then buy back => do nothing, combine previous buy and
			// afterwards sell
			max = Math.max(max, f[i] + b[i]);
		}
		return max;
	}

	/******************************************************************************************/
	/******** problem3(multiple transaction) method1 **********/
	/**
	 * get max profit of multiple transaction(buy sell pair) Alg: greedy
	 * Complexity: O(n^2) -> O(n); not clever, but can find when to buy and sell
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

	public static int maxProfit32(int[] prices) {
		if (prices == null || prices.length <= 1)
			return 0;
		int diff = 0;
		int index = 0;
		int max = 0;
		while (index < prices.length - 1) {
			diff = prices[index + 1] - prices[index];
			max += diff > 0 ? diff : 0;
			index++;
		}
		return max;
	}

	public static void main(String[] args) {
		// problem 1
		int[] prices = { 10, 15, 8, 16, 6, 5, 6 };
		System.out.println(maxProfit31(prices)); // 14
		int[] prices1 = { 10, 15, 18, 8, 16 };
		System.out.println(maxProfit31(prices1)); // 16
		int[] prices2 = { 10, 15, 13, 18 };
		System.out.println(maxProfit31(prices2));// 10
		int[] prices3 = { 10, 15, 7, 6, 5 };
		System.out.println(maxProfit31(prices3));// 5
		int[] prices4 = { 3, 3, 5, 0, 0, 3, 1, 4 };
		System.out.println(maxProfit31(prices4));// 5
		// problem 2
		int[] prices5 = { 1, 2, 4, 5 };
		System.out.println(new MaxProfit().maxProfit23(prices5));
	}
}
