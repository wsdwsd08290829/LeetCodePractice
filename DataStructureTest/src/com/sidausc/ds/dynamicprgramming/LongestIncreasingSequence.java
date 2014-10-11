package com.sidausc.ds.dynamicprgramming;

public class LongestIncreasingSequence {
	public static void main(String[] args) {
		int arr[] = { 10, 22, 9, 33, 21, 50, 41, 60, 80 , 34,35,36,37,38,39,40};
		getLongestIncreasingSequence(arr);
	}

	public static void getLongestIncreasingSequence(int[] arr) {
		int maxlength = 0;
		int maxindex = 0;
		//index is correspondent to arr, value is longest sequence if end with current 
		int[] currentLongest = new int[arr.length];  
		int[] predecessor = new int[arr.length];
		for(int i=0;i<currentLongest.length;i++){
			currentLongest[i]=1;
		}
		for(int i=0;i<predecessor.length;i++){
			predecessor[i]=-1;
		}
		for(int i=0;i<arr.length;i++){
			for(int j=0;j<i;j++){   //for each previous result
				if(currentLongest[j]>maxlength && arr[j]<arr[i]){ //find longest and its largest < current
					maxlength  = currentLongest[j];
					maxindex=j;
				}
			}	
			currentLongest[i] = maxlength+1;   //update result for current
			//if has predecessor, set it, other wise remain -1;
			if(currentLongest[i]>1)predecessor[i]=maxindex;  
			maxindex=0;     //reset assistant vars
			maxlength = 0;
		}
		
		int max = 0;
		int maxResultIndex = 0;
		//find max value and its index
		for(int i=0; i< currentLongest.length;i++){
			if(currentLongest[i]> max){
				max = currentLongest[i];
				maxResultIndex = i;
			}	
		}
		
		//get sequence in order, save to results
		int[] result = new int[max];
		max--;
		while(maxResultIndex!=-1){
			result[max--] = arr[maxResultIndex]; 
			maxResultIndex = predecessor[maxResultIndex];
		}
		//output result
		for(int i :result){
			System.out.println(i);
		}
	
	}
}
