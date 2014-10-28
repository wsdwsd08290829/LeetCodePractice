package leet;

public class ReverseInteger {
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

	public static void main(String[] args) {
		System.out.println(reverseNumber(-12345));
		System.out.println(reverseNumber(12345));
		System.out.println(-123/10);
		System.out.println(-123%10);
	}
}
