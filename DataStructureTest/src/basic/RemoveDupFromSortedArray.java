package basic;

public class RemoveDupFromSortedArray {
	public static int removeDup(int[] arr){
		int i=0, j=1;
		while(j<arr.length){
			if(arr[i] == arr[j]){
				j++;
			}else{
				i++;
				arr[i] = arr[j];
				j++;
			}
		}
		return i+1;
	}
	/*
	 * tolerance - max number of duplicate allowed (if 1, no duplicate
	 * if 2 allow one duplicate)
	 */
	public static int removeDupAllowDupCount(int[] arr, int tolerance){
		int  counter= 0;
		int i=0, j=0;
		while(i<arr.length && j<arr.length){
			
			//move i to place where it need to be changed
			while(i<arr.length-1 && arr[i] == arr[i+1]){
				i++;
				counter++;
				if(counter>tolerance)break;
			}
			//if i place is too much duplicated
			if(counter>tolerance) {
				j=i+1;
				if(j==arr.length)break;
				//find next j to if fill i
				while(arr[i]==arr[j]){j++;if(j==arr.length)break;}
				if(j==arr.length)break;
				int temp = i;
				//fill i till j
				while(temp<j)arr[temp++] = arr[j];
				counter=0;
			}else {
				i++;
				counter = 0;
				//continue;
			}
		}
		for(int t:arr)System.out.println(t);
			
			
		
		return i;
	}
	public static void main(String[] args) {
		int[] arr = {0,1,1,1,1,2,3};
	/*	System.out.println(removeDup(arr));
		for(int i:arr)
		System.out.print(i+" ");
		System.out.println();
		System.out.println("-----------");*/
		
		//change second parameter to see result
		System.out.println(removeDupAllowDupCount(arr,1));
		/*for(int i:arr)
		System.out.print(i+" ");*/
	}
}
