package basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class FourSum {
	public static void main(String[] args) {
		System.out.println(fourSum(new int[]{1, 0, -1, 0, -2, 2,0,0,0,0,0}, 0));
		System.out.println(fourSum2(new int[]{1, 0, -1, 0, -2, 2,0,0,0,0,0}, 0));
	}
	/*
	 * use Three Sum structure
	 * O(N^ (k-1))   for k-sum problem
	 * kSum is NPComplete for k>3
	 */
	public static ArrayList<ArrayList<Integer>> fourSum(int[] arr, int target) {
		Arrays.sort(arr);
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<arr.length-3;i++){
			if(i>=1 && arr[i-1]==arr[i])continue;
			
			int newTarget = target-arr[i];
			int[] subArr = new int[arr.length-i-1];		
			System.arraycopy(arr, i+1, subArr, 0, arr.length-i-1);
			for(int k:subArr){System.out.println(k);}
			System.out.println();
			
			//use result of threeSum
			ArrayList<ArrayList<Integer>> tempResult = ThreeSum.threeSum(subArr, newTarget);
			for(ArrayList<Integer> r : tempResult){
				r.add(arr[i]);
				Collections.sort(r);
			}
			result.addAll(tempResult);
		}
		// TODO Auto-generated method stub
		return result;
	}
	//from leetcode, http://www.programcreek.com/2013/02/leetcode-4sum-java/
	public static ArrayList<ArrayList<Integer>> fourSum2(int[] num, int target) {
		Arrays.sort(num);
	 
		HashSet<ArrayList<Integer>> hashSet = new HashSet<ArrayList<Integer>>();
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	
		for (int i = 0; i < num.length; i++) {
			for (int j = i + 1; j < num.length; j++) {
				int k = j + 1;
				int l = num.length - 1;
	 
				while (k < l) {
					int sum = num[i] + num[j] + num[k] + num[l];
	 
					if (sum > target) {
						l--;
					} else if (sum < target) {
						k++;
					} else if (sum == target) {
						ArrayList<Integer> temp = new ArrayList<Integer>();
						temp.add(num[i]);
						temp.add(num[j]);
						temp.add(num[k]);
						temp.add(num[l]);
						//use result:same, hashSet not necessary
						if (!hashSet.contains(temp)) {
							hashSet.add(temp);
							result.add(temp);
						}
						k++;
						l--;
					}
				}
			}
		}
		return result;
	}
}
