package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author sidawang Given a set of candidate numbers (C) and a target number
 *         (T), find all unique combinations in C where the candidate numbers
 *         sums to T. The same repeated number may be chosen from C unlimited
 *         number of times. For example, given candidate set 2,3,6,7 and target
 *         7, A solution set is: [7], [2, 2, 3]
 */
public class CombinationSum {
	public static List<List<Integer>> combinationSum(int[] candidates,
			int target) {
		Arrays.sort(candidates);
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> tempRes = new ArrayList<Integer>();
		int index = 0;
		combinationSum(candidates, target, result, tempRes, index);
		return result;
	}

	/**
	 * @param candidates
	 * @param target
	 * @param result
	 *            : all result
	 * @param tempRes
	 *            : dynamic add and remove
	 * @param startIndex
	 *            : to remove duplicates
	 */
	private static void combinationSum(int[] candidates, int target,
			ArrayList<List<Integer>> result, List<Integer> tempRes,
			int startIndex) {
		if (target < 0) {
			// tempRes.clear();
			return;
		}
		if (target == 0) {
			Collections.sort(tempRes);
			// if(!result.contains(tempRes)){
			List<Integer> tempResult = new ArrayList<Integer>(tempRes);
			result.add(tempResult);
			// }
			return;
		}
		for (int i = startIndex; i < candidates.length; i++) {
			int cand = candidates[i];
			tempRes.add(cand);
			// pass i to not start from beginning for each recursion
			// thus remove duplicates
			combinationSum(candidates, target - cand, result, tempRes, i);
			tempRes.remove(tempRes.size() - 1); // (Object)(Integer)cand
		}
		return;
	}

	public static void main(String[] args) {
		int[] cands = { 2, 3, 4, 5, 6, 7 };
		int target = 7;
		System.out.println(combinationSum(cands, target));
	}
}
