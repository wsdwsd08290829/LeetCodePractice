package leet;

import java.math.BigInteger;

public class MultiplyString {
	//num string can be very long
	public String multiplyCheat(String num1, String num2) {
		BigInteger a = new BigInteger(num1);
		BigInteger b = new BigInteger(num2);
		BigInteger result = a.multiply(b);
		return result.toString();
	}

	/**
	 * space: O(m+n) complexity(m*n)
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static String multiply(String num1, String num2) {
		int l1 = num1.length();
		int l2 = num2.length();
		// save result in reverse order: result[0]->lowest digit
		int[] result = new int[l1 + l2];
		for (int i = 0; i < l2; i++) { // bottom
			int digit2 = num2.charAt(l2 - i - 1) - '0';
			int carry = 0;
			for (int j = 0; j < l1; j++) { // top
				int digit1 = num1.charAt(l1 - j - 1) - '0';

				int sum = digit1 * digit2 + carry + result[i + j];
				carry = sum / 10;
				result[i + j] = sum % 10; // !!!!
			}
			if (carry > 0) {
				result[i + l1] = carry; // j=l1;
			}
		}
		String strResult = "";
		for (int r : result) {
			strResult = r + strResult;
		}
		// unless result is 0, remove 0s in head
		while (strResult.length() > 1 && strResult.charAt(0) == '0') {
			strResult = strResult.substring(1);
		}
		return strResult;
	}

	public static void main(String[] args) {
		String s1 = "999";
		String s2 = "999";
		System.out.println(multiply(s1, s2));
	}
}
