package leet;

public class DecodeWays {
	// could be 0 in s
	public static int numDecodings(String s) {
		if (s == null || s.length() == 0)
			return 0;
		if (s.charAt(0) == '0')
			return 0;
		if (s.length() == 1)
			return 1;
		// L[i] means num of decodings for substring(0, i), length i
		int[] L = new int[s.length() + 1]; // L[s.length()] is result
		L[0] = 1;
		L[1] = 1;
		/*
		 * dynamic programming:depend on if last two is valid bottom up
		 */
		for (int i = 2; i <= s.length(); i++) {

			char first = s.charAt(i - 2);
			char second = s.charAt(i - 1);
			// both 0,  eg. xx00
			if (first == '0' && second == '0')
				return 0;
			// if second 0, first not 0, eg 10, 30
			if (second == '0' && first > '0' && first < '3') {
				L[i] = L[i - 2];
			} else if (second == '0' && first >= '3')
				return 0;

			// if first 0, second not 0, eg. 0x
			else if (first == '0')
				L[i] = L[i - 1];

			// both not 0,  11-19,21-26 or 3x+
			else if (first == '1' && second > '0' && second <= '9') {
				L[i] = L[i - 1] + L[i - 2];
			} else if (first == '2' && second > '0' && second <= '6') {
				L[i] = L[i - 1] + L[i - 2];
			} else
				L[i] = L[i - 1];

		}
		return L[s.length()];
	}
	public static void main(String[] args) {
		// System.out.println(Integer.valueOf("01"));
		System.out.println(DecodeWays.numDecodings("101"));
		System.out.println(DecodeWays.numDecodings("100"));
		System.out.println(DecodeWays.numDecodings("10"));
		System.out.println(DecodeWays.numDecodings("1010"));
		System.out.println(DecodeWays.numDecodings("110"));
	}
}
