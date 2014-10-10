package basic;

public class MedianOfTwoSortedArray {
	public static double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
	 
		if ((m + n) % 2 != 0) // odd
			return (double) findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1);
		else { // even
			return (findKth(A, B, (m + n) / 2, 0, m - 1, 0, n - 1) 
				+ findKth(A, B, (m + n) / 2 - 1, 0, m - 1, 0, n - 1)) * 0.5;
		}
	}
	 
	public static int findKth(int A[], int B[], int k, 
		int aStart, int aEnd, int bStart, int bEnd) {
	 
		int aLen = aEnd - aStart + 1;
		int bLen = bEnd - bStart + 1;
	 
		// Handle special cases
		if (aLen == 0)
			return B[bStart + k];
		if (bLen == 0)
			return A[aStart + k];
		if (k == 0)
			return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
	 
		int aMid = aLen * k / (aLen + bLen); // a's middle count
		int bMid = k - aMid - 1; // b's middle count
	 
		// make aMid and bMid to be array index
		aMid = aMid + aStart;
		bMid = bMid + bStart;
	 
		if (A[aMid] > B[bMid]) {
			k = k - (bMid - bStart + 1);
			aEnd = aMid;
			bStart = bMid + 1;
		} else {
			k = k - (aMid - aStart + 1);
			bEnd = bMid;
			aStart = aMid + 1;
		}
	 
		return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
	}
	public static void main(String[] args) {
		int[] arr1 = {1,3,5,7};
		int[] arr2 = {2,4,6,8,10, 11,12};
		double result = findMedianSortedArrays(arr1, arr2);
		System.out.println(result);
		
		double res = findKth2(arr1, arr2, 5, 0, 3, 0, 6);
		System.out.println(res);
	}
	
	public static int findKth2(int A[], int B[], int k, 
		int aStart, int aEnd, int bStart, int bEnd){
		int alen = aEnd-aStart+1;
		int blen = bEnd-bStart+1;
		
		//special case
		if(alen==0){
			System.out.println(alen+ " s1 " + blen);
			return B[bStart+k];
		}else
		if(blen==0){
			System.out.println(alen+ " s2 " + blen);
			return A[aStart+k];
		}
		if (k == 0)
			return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
		
		int amid = alen*k/(alen+blen);
		int bmid = k-amid-1; //?? -1 +1 ??   //assume k = 1, then you know 
		int aindex = aStart+amid;
		int bindex = bStart+bmid;

		System.out.println(aindex + "???");
		if(A[aindex] == B[bindex]){
			
			return A[aindex];
			
		}
		else 
		if(A[aindex] < B[bindex]){
			aStart = amid+1;   //use example
			bEnd=bmid;
			k=k-amid-1;  //use example
			System.out.println(aStart + "@Q@@"+ bEnd);
			
		}
		if(A[aindex] > B[bindex]){
			bStart = bmid+1;
			aEnd = amid;
			k=k-bmid-1;

		}
		System.out.println("K" + k);
		return findKth2(A, B, k, aStart, aEnd, bStart, bEnd);
	}
}
