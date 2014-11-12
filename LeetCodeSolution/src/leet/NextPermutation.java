package leet;

public class NextPermutation {
	public void nextPermutation(int[] num) {
		if (num == null || num.length <= 1)
			return;
		// for end to start: find first decrease leftPos
		int index = num.length - 2;
		// !!! = is important; eg 511 ->115, other wise still 511 error
		while (index >= 0 && num[index] >= num[index + 1]) {
			index--;
		}
		if (index < 0) {
			reverseArrayFrom(num, 0);// inplace
			return;
		}
		int leftPos = index;
		// !! from pos to end find rightPos that larger than leftPos
		while (index < num.length - 1 && num[leftPos] < num[index + 1]) {
			index++;
		}
		int rightPos = index;
		// swap leftPos and rightPos
		int temp = num[leftPos];
		num[leftPos] = num[rightPos];
		num[rightPos] = temp;

		// reverse after leftPos
		reverseArrayFrom(num, leftPos + 1);
	}

	public void reverseArrayFrom(int[] num, int from) {
		int left = from;
		int right = num.length - 1;
		while (left < right) {
			int temp = num[left];
			num[left] = num[right];
			num[right] = temp;
			left++;
			right--;
		}
	}
}
