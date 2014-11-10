package leet;

public class PlusOneToArray {
	public int[] plusOne(int[] digits) {
		int i = digits.length - 1;
		int sum = digits[i] + 1;
		int carry = sum > 9 ? 1 : 0;
		digits[i] = sum > 9 ? 0 : sum;
		i--;
		while (i >= 0) {
			sum = digits[i] + carry;
			if (sum > 9) {
				carry = 1;
				digits[i] = 0;
			} else {
				carry = 0;
				digits[i] = sum;
			}
			i--;
		}
		if (carry == 1) {
			int[] result = new int[digits.length + 1];
			result[0] = 1;
			for (int j = 0; j < digits.length; j++) {
				result[j + 1] = digits[j];
			}
			return result;
		}
		return digits;
	}

	// refactorred
	public int[] plusOne1(int[] digits) {
		int i = digits.length - 1;
		int sum = 0;
		int carry = 1; // to simulate plus one
		// while
		while (i >= 0) {
			sum = digits[i] + carry;
			digits[i] = sum > 9 ? 0 : sum;
			carry = sum > 9 ? 1 : 0;
			if (sum < 9)
				break; // stop when no carry possible
			i--;
		}
		if (carry == 1) { // copy array to new
			int[] result = new int[digits.length + 1];
			result[0] = 1;
			for (int j = 0; j < digits.length; j++) {
				result[j + 1] = digits[j];
			}
			return result;
		}
		return digits;
	}
}
