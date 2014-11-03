package leet;

public class SingleNumber {
	public int singleNumber(int[] A) {
		int i = 0;
		for (int a : A) {
			i = i ^ a;
		}
		return i;
	}

	public int singleNumberRepeatThree(int[] A) {
		int result = 0;
		int[] count = new int[32];
		for(int i=0;i<32;i++){
			for(int j = 0;j<A.length;j++){
				if(((A[j] >> i) & 1) == 1)count[i]++;
			}
			result = result | (count[i]%3<<i);
		}
		return result;
	}
	public static void main(String[] args) {
		int[] arr = { 1,2,1,2,3,1,2};
		System.out.println(new SingleNumber().singleNumberRepeatThree(arr));
	}
}
