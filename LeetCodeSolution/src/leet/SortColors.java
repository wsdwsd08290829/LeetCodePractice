package leet;

//0 red, 1, white, 2 blue
public class SortColors {
	/******* method1 *******/
	// 2pass, counting sort
	public void sortColors(int[] A) {
		int k = 3;
		// index == value in A
		int[] color = new int[k];
		for (int i = 0; i < A.length; i++) {
			color[A[i]]++;
		}
		int index = 0;
		for (int i = 0; i < color.length; i++) {
			int count = color[i];
			while (count > 0) {
				A[index++] = i;
				count--;
			}
		}
	}

	/******* method2 *********/
	// one pass, use start and end pointer to keep 0 and 2
	public void sortColors1(int[] A) {
		int start = 0;
		int end = A.length - 1;
		int cur = 0;
		int count = 0;
		while (cur >= start && cur <= end) {
			if (A[cur] == 0 && cur > start) {
				swap(A, cur, start);
				start++;
			} else if (A[cur] == 1) {
				cur++;
			} else if (A[cur] == 2 && cur < end) {
				swap(A, cur, end);
				end--;
			} else {
				cur++;
			}
			count++;
		} // not exactly O(n), count ==8 for { 2, 1,0,2,0,1};
		System.out.println(count);
	}

	public void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}

	/******* method3 ********/
	// hard to understand
	public void sortColors2(int[] A) {
		int idx0 = 0, idx1 = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == 0) {
				A[i] = 2;
				// potential 1 will be overwrite by 0, need to extend
				A[idx1++] = 1;
				A[idx0++] = 0;
			} else if (A[i] == 1) {
				A[i] = 2;
				A[idx1++] = 1;
			}
		}
	}

	public static void main(String[] args) {
		int[] A = { 2, 1, 0, 2, 0, 1 };
		SortColors sc = new SortColors();
		sc.sortColors2(A);
		for (int i : A) {
			System.out.print(i + " ");
		}
	}
}
