
public class BoxSize {
	public static int find(int[] arr, int target){
		if(target>arr[arr.length-1]) return -1;
		return find(arr, 0, arr.length-1, target);
	}
	public static int find(int[] arr, int start, int end, int target){
		
		if(start==end)return arr[end];
		int mid = start + (end-start)/2;
		if(target == arr[mid])return arr[mid];
		else if(target>arr[mid])return find(arr, mid+1, end, target);
		else return find(arr, start, mid, target);
	}
	public static void main(String[] args) {
		int [] arr = { 10,20,30,40,50,60,70};
		System.out.println(find(arr, 14));
		System.out.println(find(arr, 75));
		System.out.println(find(arr, 50));
		System.out.println(find(arr, 30));
	}

}
