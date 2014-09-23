package com.sidausc.ds.tasks;

public class LargestContinuous {
	public static int largestContinuous(int arr[]){
		int largestSoFar = arr[0];  // eg   3, 2, -1, 4, -3, 5, -13,20
		int largestUpToHere = arr[0];
		for(int i = 1; i<arr.length;i++){
			if(arr[i] >0){
				largestUpToHere  += arr[i];
				largestSoFar  = Math.max(largestUpToHere, largestSoFar);
				System.out.println(largestUpToHere + " " + largestSoFar);
			}
			else{
				if(largestUpToHere < 0){
					largestUpToHere = arr[i];
				}else{
					largestUpToHere  += arr[i];
					System.out.println(largestUpToHere + " " + largestSoFar);
				}
			}
		}
		return largestSoFar;
	}
	public static void main(String[] args) {
		int[] arr = { 3, 2, -1, 4, -3, 5, -23,20};
		System.out.println(largestContinuous(arr));
	}
}
