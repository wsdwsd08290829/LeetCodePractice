package leet;

public class ReverseInteger {
	/********************* method1 ***********************/
	/**
	 * method 1: iteratively compare left and right most
	 * 
	 * @param num
	 * @return
	 */
	public static int reverseNumber(int num) {
		int digitCount = 0;
		int result = 0;
		// !!neg are handle well internally
		int temp = num;
		// get # of digits
		while (temp != 0) { // 12 1
			temp /= 10;
			digitCount++;
		}
		temp = num;
		// get result
		while (digitCount > 0) {
			result = result * 10 + temp % 10;
			temp /= 10;
			digitCount--;
		}
		return result;
	}

	/********************* method2 ***********************/
	/**
	 * method2: compare x with reversed x
	 * 
	 * @param num
	 * @return
	 */
	public static int reverseNumber1(int num) {
		int digitCount = 0;
		int result = 0;
		// !!neg are handle well internally
		int temp = num;
		// get # of digits
		while (temp != 0) { // 12 1
			temp /= 10;
			digitCount++;
		}
		temp = num;
		// get result
		while (digitCount > 0) {
			result = result * 10 + temp % 10;
			temp /= 10;
			digitCount--;
		}
		return result;
	}

	public boolean isPalindrome(int x) {
		if (x < 0)
			return false;
		if (x == reverseNumber(x))
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		System.out.println(reverseNumber(-12345));
		System.out.println(reverseNumber(12345));
		System.out.println(-123 / 10);
		System.out.println(-123 % 10);
	}
}
