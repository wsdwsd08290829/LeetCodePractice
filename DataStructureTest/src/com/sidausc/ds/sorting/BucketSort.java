package com.sidausc.ds.sorting;
public class BucketSort {
	public static void bucketSort(int [] arr){
		//assume the max is 10
		int [] result = new int[10];
	
		for(int i = 0; i<arr.length; i=i+1){
			result[arr[i]]++;
		}
		
		int arrIndex = 0;
		for(int i=0;i<result.length;i++){
			int temp = result[i];		
			while(temp>0){
				arr[arrIndex++] = i;
				temp--;
			}
		}
	}
	public static void main(String[] args) {
		int[] arr = {5,5,4,3,2,1};
		bucketSort(arr);
		for(int a : arr){System.out.println(a);}
	}
}
