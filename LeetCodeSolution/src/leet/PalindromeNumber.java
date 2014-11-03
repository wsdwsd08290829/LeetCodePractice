package leet;

public class PalindromeNumber {
	public static boolean isPalindrome(int x) {
		// if(x==Integer.MIN_VALUE) return false;
		// if(x < 0) x = -x;
		if (x < 0)
			return false;

		int tens = 1;
		int temp = x;
		// get tens: 121-> 100
		while (temp >= 10) {
			temp /= 10;
			tens *= 10;
		}
		// compare high == low, update temp, tens
		temp = x;
		while (tens > 1) { // tens == 1 means one num < 10 left, do not need
							// check
			int high = temp / tens;
			int low = temp % 10;
			if (high != low)
				return false;
			temp = temp % tens / 10;
			tens /= 100;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPalindrome(121));
		System.out.println(isPalindrome(-121));
		System.out.println(isPalindrome(122));
		System.out.println(isPalindrome(10));
		System.out.println(isPalindrome(-2147483648));
	}
}
