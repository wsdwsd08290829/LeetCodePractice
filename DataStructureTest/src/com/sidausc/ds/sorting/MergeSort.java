package com.sidausc.ds.sorting;


//usage merge form disk by part
//nlog(n), not in place
public class MergeSort {
	public static int[] mergeSort(int[] arr, int start,int end) throws Exception{
		if(start>end)throw new Exception();
		if(start==end){return new int[]{arr[start]};}
		if(start+1==end){
			if(arr[start]>arr[end])return new int[]{arr[end],arr[start]};
			else return new int[]{arr[start],arr[end]};
		
		}
		int mid = (start+end)/2;
		return merge(mergeSort(arr, start, mid), mergeSort(arr,mid+1,end));
	}
	public static int[] merge(int[] a, int[] b){
		int[] arr = new int[a.length+b.length];
		int index=0;
		int i=0,j=0;
		while(i<a.length && j<b.length){
			if(a[i]>b[j]){arr[index++]=b[j++];}
			else arr[index++] = a[i++];
		}
		if(i==a.length){
			while(j<b.length){arr[index++]=b[j++];}
		}
		if(j==b.length){
			while(i<a.length){arr[index++]=a[i++];}
		}
		return arr;
	}
	public static void main(String[] args) throws Exception {
		int[] arr = {5,4,3,2,1,23,45,15,46,37,67,78,54};
		for(int i : mergeSort(arr, 0, arr.length-1)){
			System.out.print(i+",");
		}
	}
}
