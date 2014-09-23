package com.sidausc.ds.sorting;
public class InsertionSort {
	//like sort cards in hands
	public static void insertionSort(int[] arr){
		int temp = 0;
		if(arr.length<=1)return;
		for(int i=1; i<arr.length; i++){
			int j= i-1;
			temp= arr[i];
			while(j>=0 && temp<arr[j]){
					arr[j+1] = arr[j];
					j--;
			}
			arr[j+1] = temp;	
		}
	}
	public static int[] insertionSortNew(int[] arr){
		for(int i=1;i<arr.length;i++){
			for(int j=i; j>0; j--){
				if(arr[j]<arr[j-1]){
					swap(arr, j, j-1);
				}
			}
		}
		return arr;
	}
	public static void swap(int[] arr, int a, int b){
		int temp = arr[b];
		arr[b] = arr[a];
		arr[a] = temp;
	}
	public static void main(String[] args) {
		int[] arr = {5,2,1,3,4};
		insertionSortNew(arr);
		//System.out.println(arr.length);
		for(int i : arr){
			System.out.println(i + " ");
		}
	}
}
