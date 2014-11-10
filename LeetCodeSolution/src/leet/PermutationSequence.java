package leet;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequence {
	/**
	 * use list to save 1 to n;!!remove from list is O(n); time O(n^2), space
	 * O(n)
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public String getPermutation(int n, int k) {
		if (n < 1)
			return "";

		int factorial = 1;
		// n-1 factorial
		for (int i = 1; i <= n - 1; i++) {
			factorial *= i;
		}
		// if k too large
		if (k > factorial * n)
			return "";
		k = k - 1;// change k to index;
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= n; i++) {
			list.add(i);
		}

		StringBuilder sb = new StringBuilder();
		// loop n times, add from left to right
		while (n > 0) {
			// factorial is (n-1)!
			// index of list to get first digit of result
			int index = k / factorial;
			sb.append(list.get(index));
			list.remove(index);
			k %= factorial; // kth for next round (n-1)
			n--;
			if (n > 0) {
				factorial /= n;
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		PermutationSequence ps = new PermutationSequence();
		System.out.println(ps.getPermutation(3, 7));
	}
}
