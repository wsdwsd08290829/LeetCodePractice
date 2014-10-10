package basic;

public class SearchInsert {
	//wrapper function
	public static int searchInsert(int[] arr, int val){
		{
			return searchInsert(arr, val, 0, arr.length-1);
		}
	}
	public static int searchInsert(int[] arr, int val, int start, int end){
		//boundary case
		if(start >= end){
			if(val <= arr[start])return start;
			else return start+1;
		}
		
		//the mid can be calculated as mid = start + (end-start)/2 
		//to avoid start+end integer overflow if both start and end are big numbers.
		
		int mid= (start+end)/2;
		if(val==arr[mid])return mid;
		else if(val>arr[mid]) return searchInsert(arr, val, mid+1, end);
		else return searchInsert(arr, val, 	start, mid-1);
	}
	public static void main(String[] args) {
		int [] arr = {1,3,5,6};
		System.out.println(searchInsert(arr, 0));
	}
}
