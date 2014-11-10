package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/************ problem1 no duplicates ************/
/******** method1 recursion ********/
public class SubSet {
	public List<List<Integer>> subsets(int[] num) {
		if (num == null)
			return null;
		Arrays.sort(num);
		return subsetsHelper(num, num.length - 1);
	}

	private ArrayList<List<Integer>> subsetsHelper(int[] num, int end) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		if (end == -1) {
			List<Integer> list = new ArrayList<Integer>();
			result.add(list);
			return result;
		}
		int last = num[end];
		ArrayList<List<Integer>> prevResult = subsetsHelper(num, end - 1);
		// add all previous subset
		result.addAll(prevResult);
		// append last to each list in prevResult, then to result
		for (List<Integer> tempList : prevResult) {
			List<Integer> list = new ArrayList<Integer>(tempList);
			list.add(last);
			result.add(list);
		}
		return result;
	}

	/******** method2 iteration ********/
	public List<List<Integer>> subsets1(int[] num) {
		if (num == null)
			return null;
		Arrays.sort(num);
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());
		ArrayList<List<Integer>> tempResult = new ArrayList<List<Integer>>();
		for (int i = 0; i < num.length; i++) {

			// if do not use tempResult,
			// java.util.ConcurrentModificationException
			// use index version of for is OK
			tempResult.clear();
			for (List<Integer> tempList : result) {
				List<Integer> list = new ArrayList<Integer>(tempList);
				list.add(num[i]);
				tempResult.add(list);
			}
			result.addAll(tempResult);
		}
		return result;
	}

	/*************** problem2: allow duplicates ********************/
	/******** method1 iteration ********/
	public List<List<Integer>> subsetsWithDup(int[] num) {
		if (num == null)
			return null;
		Arrays.sort(num);
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());
		Set<Integer> set = new HashSet<Integer>();
		ArrayList<List<Integer>> tempResult = new ArrayList<List<Integer>>();
		for (int i = 0; i < num.length; i++) {
			// if do not use tempResult,
			// java.util.ConcurrentModificationException
			ArrayList<List<Integer>> loopResult = null;
			// copy list wast time, see below
			if (set.contains(num[i])) {
				// only use newly added duplicate result-> add 2 to list end
				// with 2 added last loop
				loopResult = new ArrayList<List<Integer>>(tempResult);

			} else {
				loopResult = new ArrayList<List<Integer>>(result);
			}
			tempResult.clear();
			for (List<Integer> tempList : loopResult) {
				List<Integer> list = new ArrayList<Integer>(tempList);
				list.add(num[i]);
				tempResult.add(list);
			}
			set.add(num[i]);
			result.addAll(tempResult);
		}
		return result;
	}

	/************ method 2: iteration:better *************/
	// http://blog.csdn.net/linhuanmars/article/details/24613193
	public List<List<Integer>> subsetsWithDup1(int[] num) {
		ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());
		if (num == null || num.length == 0)
			return result;
		Arrays.sort(num);
		int start = 0;
		for (int i = 0; i < num.length; i++) {
			int size = result.size();
			for (int j = start; j < size; j++) {
				List<Integer> l = new ArrayList<Integer>(result.get(j));
				l.add(num[i]);
				result.add(l);
			}
			if (i + 1 < num.length && num[i] == num[i + 1]) {
				start = size;
			} else {
				start = 0;
			}
		}

		return result;
	}

	/************ method 3: recursion *************/
	// http://blog.csdn.net/linhuanmars/article/details/24613193
	// !!! how to carry previous information above: use list as globle
	// "set in bottom stack"
	public List<List<Integer>> subsetsWithDup2(int[] num) {
		if (num == null)
			return null;
		Arrays.sort(num);
		// use list so that value can carry with list
		ArrayList<Integer> lastSize = new ArrayList<Integer>();
		lastSize.add(0);
		return subsetsWithDupHelper2(num, num.length - 1, lastSize);
	}

	private ArrayList<List<Integer>> subsetsWithDupHelper2(int[] num, int end,
			ArrayList<Integer> lastSize) {

		if (end == -1) {
			ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
			List<Integer> list = new ArrayList<Integer>();
			result.add(list);
			return result;
		}

		ArrayList<List<Integer>> result = subsetsWithDupHelper2(num, end - 1,
				lastSize);
		int start = 0;
		int size = result.size();
		if (end > 0 && num[end] == num[end - 1]) {
			start = lastSize.get(0);
		}
		// /////////
		// append last to each list in prevResult, then to result
		for (int i = start; i < size; i++) {
			List<Integer> list = new ArrayList<Integer>(result.get(i));
			list.add(num[end]);
			result.add(list);
		}
		// ////////////
		lastSize.set(0, size);

		return result;
	}
}
