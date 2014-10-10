package basic;

public class MaxiumContinuousSubArray {
	public static int maxSubArray(int[] arr){
		int [] endWith =new int[arr.length];
		int max = Integer.MIN_VALUE;
		int maxEndIndex = 0, maxStartIndex=0;
		endWith[0] = arr[0];
		for(int i=1;i<arr.length;i++){
			//Math.max(arr[i] + endWith[i-1], arr[i]);
			if(endWith[i]< endWith[i] + endWith[i-1]){
				endWith[i] = arr[i] + endWith[i-1];
			}else{
				endWith[i] = arr[i];
			}
			if(endWith[i]>max){
				max=endWith[i];
				maxEndIndex = i;
			}
		}
		int temp = max;
		int tempIndex = maxEndIndex;
		while(temp!=0){
			temp-=arr[tempIndex--];
		}
		maxStartIndex = tempIndex+1;
		System.out.println(maxStartIndex + " " + maxEndIndex + "(Inclusive)");
		return max;
	}
	public static void main(String[] args) {
		int[] arr = {-2,2,-1,4,-1,2,1,-5,4}; 
		System.out.println(maxSubArray(arr));
	}
}
