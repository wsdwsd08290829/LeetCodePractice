package com.sidausc.ds.sorting;

public class BubbleSort {
	public static int[] bubbleSort(int[] arr){
		boolean swap = true;
		//heavest sink, lightest float
		for(int i=0;i<arr.length-1 && swap==true;i++){  //bubble phase
			swap = false;
			for(int j=0;j<arr.length-1-i;j++){
				if(arr[j]>arr[j+1]){
					swap(arr, j, j+1);
					swap = true; //if no swap, then already sorted, no need outer loop
				}
			}
		}
		return arr;
	}
	public static  void swap(int[] arr, int a, int b){
		int temp = arr[b];
		arr[b] = arr[a];
		arr[a] = temp;
	}
	public static void main(String[] args) {
		int[] arr = {15,13,2,11,6,5};
		for(int i: bubbleSort(arr)){
			System.out.println(i);
		}
	}
}
