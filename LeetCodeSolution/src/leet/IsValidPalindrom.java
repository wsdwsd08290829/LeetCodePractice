package leet;

public class IsValidPalindrom {
	/**
	 * time exceed for very long string; recursive
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome(String s) {
		if (s == null)
			return true;
		s = s.toLowerCase();
		int start = 0;
		int end = s.length() - 1;
		while (start < s.length()
				&& (s.charAt(start) < 'a' || s.charAt(start) > 'z')) {
			start++;
		}
		while (end > 0 && (s.charAt(end) < 'a' || s.charAt(end) > 'z')) {
			end--;
		}
		if (start >= end)
			return true;
		if (s.charAt(start) == s.charAt(end)) {

			return isPalindrome(s.substring(start + 1, end));
		} else {
			return false;
		}
	}

	/**
	 * Ok for very long string; iteration
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isPalindrome1(String s) {
		if (s == null)
			return true;
		s = s.toLowerCase();
		int start = 0;
		int end = s.length() - 1;
		while (start < end) {
			while (start < end && !isAlphaNum(s.charAt(start))) {
				start++;
			}
			while (start < end && !isAlphaNum(s.charAt(end))) {
				end--;
			}
			if (start >= end)
				return true;
			if (s.charAt(start) == s.charAt(end)) {
				start++;
				end--;
			} else {
				return false;
			}
		}
		return true;
	}

	private static boolean isAlphaNum(char ch) {
		if (ch >= '0' && ch <= '9')
			return true;
		if (ch >= 'a' && ch <= 'z')
			return true;
		if (ch >= 'A' && ch <= 'Z')
			return true;
		return false;
	}

	public static void main(String[] args) {
		System.out.println(isPalindrome1("A man, a plan, a canal: Panama"));
	}
}
