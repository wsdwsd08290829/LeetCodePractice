package com.sidausc.ds.sorting;

import java.util.Comparator;

public class QuickSort {
	public static int quickSearchKthMin(Integer[] arr, int start, int end, int k){
		
		if(arr.length == 1) return arr[0];
		
		int pivot = arr[end];
		int l = 0, r=end-1;
		while(l<=r){
			while(l<=r && arr[l]<pivot){
				l++;
			}
			while(l<=r && arr[r]>pivot){
				r--;
			}
			if(l<r){
				int temp = arr[r];
				arr[r] = arr[l];
				arr[l] = temp;
			}
		}
		int temp = arr[end];
		arr[end] = arr[l];
		arr[l] = temp;
		if(k<=l)return quickSearchKthMin(arr, start, l-1,  k);
		else if(k==l+1)return pivot;
		else return quickSearchKthMin(arr, l+1, end, k-l-1);
		
	}
	public static <E> void quickSort(E[] arr, Comparator<E> c, int start, int end){
		if(start >= end)return;
		E pivot = arr[end];

		int l = 0, r=end-1;
		while(l<=r){
			while(l<=r && c.compare(arr[l], pivot)<=0){
				l++;
			}
			while(l<=r && c.compare(arr[r], pivot)>=0){
				r--;
			}
			if(l<r){
				E temp = arr[r];
				arr[r] = arr[l];
				arr[l] = temp;
			}
		}
		E temp = arr[end];
		arr[end] = arr[l];
		arr[l] = temp;
		quickSort(arr,c, 0,l-1);
		quickSort(arr,c, l+1,end);
		
	}
	public static void main(String[] args) {
		Integer[] arr = {31,42,23,13,51};
		quickSort(arr,new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// TODO Auto-generated method stub
				return o1-o2;
			}
		}, 0, 4);
		for(int i: arr){
			System.out.println(i);
		}
		
		
		System.out.println(quickSearchKthMin(arr, 0, 4, 4));
	}
}
