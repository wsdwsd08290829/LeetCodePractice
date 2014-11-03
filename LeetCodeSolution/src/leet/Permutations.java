package leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * thought1: !!insert!! one char to each position or rest; eg. 1-> {23, 32} ->
 * {123,213,231...}; Thought2: each num as !!head!! of rest permutation
 * 
 * @author sidawang
 * 
 */
public class Permutations {
	public List<List<Integer>> permute(int[] num) {
		return permuteHelper(num, 0, num.length - 1);
	}

	/**
	 * method1: recursive(insert), no deal with duplicates yet; recursive get
	 * permutation of num array, from start to end;
	 * 
	 * @param num
	 * @param start
	 * @param end
	 * @return
	 */
	private List<List<Integer>> permuteHelper(int[] num, int start, int end) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		if (num == null) {
			return result;
		}
		LinkedList<Integer> temp = new LinkedList<Integer>();
		// if only one to permute
		if (start == end) {
			temp.add(num[end]);
			result.add(temp);
			return result;
		}
		// insert start to each position or each list
		List<List<Integer>> prevResult = permuteHelper(num, start + 1, end);
		for (List<Integer> list : prevResult) {
			for (int i = 0; i <= list.size(); i++) {
				temp = new LinkedList<Integer>(list);
				temp.add(i, num[start]);
				result.add(temp);
			}
		}
		return result;
	}

	public List<List<Integer>> permute1(int[] num) {
		List<Integer> numList = new LinkedList<Integer>();
		for (Integer i : num) {
			numList.add(i);
		}
		return permuteHelper1(numList);
	}

	/**
	 * method2:recursive: head
	 * 
	 * @param num
	 * @param start
	 * @param end
	 * @return
	 */
	private List<List<Integer>> permuteHelper1(List<Integer> numList) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		if (numList == null || numList.size() == 0) {
			result.add(new ArrayList<Integer>());
			return result;
		}

		HashSet<Integer> set = new HashSet<Integer>();
		// loop each elem in numList as head
		for (int i = 0; i < numList.size(); i++) {
			int head = numList.get(i);
			// if head already used, deal with duplicates
			if (set.contains(head)) {
				continue;
			} else {
				set.add(head);
			}
			numList.remove(i); // index
			List<List<Integer>> tempResult = permuteHelper1(numList);
			for (List<Integer> temp : tempResult) {
				temp.add(0, head);
			}
			result.addAll(tempResult);
			numList.add(i, head);

		}

		return result;
	}

	public static void main(String[] args) {
		int[] num = { 1, 1, 3 };
		System.out.println(new Permutations().permute(num));
		System.out.println(new Permutations().permute1(num));
	}
}
