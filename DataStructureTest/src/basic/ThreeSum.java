package basic;

import java.util.ArrayList;
import java.util.Arrays;

public class ThreeSum {
	public static void main(String[] args) {
		System.out.println(threeSum(new int[] { 3, 6, -1, 0, 7, 0, 0, 0, 10, 0,
				10, 10 }, 9));
		System.out.println(closestThreeSum(new int[]{-1, 2, 1, -4}, 1));
		
		
	}

	public static ArrayList<ArrayList<Integer>> threeSum(int[] arr, int target) {
		Arrays.sort(arr);
		/* for(int i:arr){System.out.println(i);} */
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < arr.length - 2; i++) {
			// if duplicate from start
			if (i >= 1 && arr[i - 1] == arr[i])
				continue;
			int newTarget = target - arr[i];
			int start = i + 1, end = arr.length - 1;
			while (start < end) {
				if (arr[start] + arr[end] == newTarget) {
					ArrayList<Integer> resultGroup = new ArrayList<Integer>();
					resultGroup.add(arr[i]);
					resultGroup.add(arr[start]);
					resultGroup.add(arr[end]);
					result.add(resultGroup);
					start++;
					end--;
					// avoid duplicate
					// for(int k:arr){System.out.println(k);}
					// if no start < end, eg. 0,0,0,0,1,2; target -1;
					while (arr[start] == arr[start - 1] && start < end)
						start++;
					while (arr[end] == arr[end + 1] && start < end)
						end--;
				} else if (arr[start] + arr[end] < newTarget) {
					start++;
					while (arr[start] == arr[start - 1] && start < end)
						start++;
				} else if (arr[start] + arr[end] > newTarget) {
					end--;
					while (arr[end] == arr[1 + end] && start < end)
						end--;
				}
			}
		}
		return result;
	}

	public static int closestThreeSum(int[] arr, int target) {
		int min = Integer.MAX_VALUE;
		int result = 0;
		Arrays.sort(arr);
		/* for(int i:arr){System.out.println(i);} */
		for (int i = 0; i < arr.length - 2; i++) {
			// if duplicate from start
			if (i >= 1 && arr[i - 1] == arr[i])
				continue;
			//int newTarget = target - arr[i];
			int start = i + 1, end = arr.length - 1;
			while (start < end) {
				if(Math.abs(arr[i]+arr[start]+arr[end]-target)<min){
					min = Math.abs(arr[i]+arr[start]+arr[end]-target);
				//	System.out.println(min);
					result = arr[i]+arr[start]+arr[end];	
					if(result == 0)return 0;
				}
				if (arr[start] + arr[end] +arr[i] < target) {
					start++;
					while (arr[start] == arr[start - 1] && start < end)
						start++;
				} else if (arr[start] + arr[end] +arr[i] > target) {
					end--;
					while (arr[end] == arr[1 + end] && start < end)
						end--;
				}
			}
		}
		return result;
	}
}
