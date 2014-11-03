package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CombinationSumII {
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
		Set<Integer> set = new HashSet<Integer>();
		for (int i = startIndex; i < candidates.length; i++) {
			int cand = candidates[i];
			if (set.contains(cand)) {
				continue;
			} else {
				set.add(cand);
			}

			tempRes.add(cand);
			// pass i to not start from beginning for each recursion
			// thus remove duplicates
			combinationSum(candidates, target - cand, result, tempRes, i + 1);
			tempRes.remove(tempRes.size() - 1); // (Object)(Integer)cand
		}
		return;
	}

	public static void main(String[] args) {
		int[] cands = { 10, 1, 2, 7, 6, 1, 5 };
		int target = 8;
		System.out.println(combinationSum(cands, target));
	}
}
