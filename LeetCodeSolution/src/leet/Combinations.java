package leet;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out
 * of 1 ... n.
 * 
 * @author sidawang
 * 
 */
public class Combinations {
	public List<List<Integer>> combine(int n, int k) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if (n < 1 || k > n)
			return result;
		List<Integer> temp = new ArrayList<Integer>();
		combineHelper(n, k, 1, temp, result);
		return result;
	}

	private void combineHelper(int n, int k, int startFrom, List<Integer> temp,
			List<List<Integer>> result) {
		if(k==0){
			List<Integer> list = new ArrayList<Integer>(temp);
			result.add(list);
			return;
		}
		for(int i=startFrom;i<=n-k+1;i++){
			temp.add(i);
			combineHelper(n, k-1, i+1, temp, result);
			temp.remove(temp.size()-1);
		}
	}
	public static void main(String[] args) {
		Combinations comb = new Combinations();
		System.out.println(comb.combine(4, 2));
	}
}
