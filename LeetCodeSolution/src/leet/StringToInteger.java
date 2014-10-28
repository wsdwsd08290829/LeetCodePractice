package leet;

public class StringToInteger {
	/*
	 * flag true: positive
	 * flag false: negative
	 */
	public static int atoiPos(String str, boolean flag) {
		// System.out.println("Integer" + Integer.valueOf(str));
		int result = 0;
		for (int i = 0; i < str.length(); i++) {
			int digit = str.charAt(i) - '0';

			// meet other chars,stop immediately
			if (digit > 9 || digit < 0)
				break;
			// check boundary before calculate
			if (!flag) {
				if ((long) result * 10 + digit > Integer.MAX_VALUE)
					return Integer.MIN_VALUE;
			} else {
				if ((long) result * 10 + digit > Integer.MAX_VALUE)
					return Integer.MAX_VALUE;
			}

			result = result * 10 + digit;
		}

		return result;
	}

	/**
	 * Requirements for atoi: The function first discards as many whitespace
	 * characters as necessary until the first non-whitespace character is
	 * found. Then, starting from this character, takes an optional initial plus
	 * or minus sign followed by as many numerical digits as possible, and
	 * interprets them as a numerical value.
	 * 
	 * The string can contain additional characters after those that form the
	 * integral number, which are ignored and have no effect on the behavior of
	 * this function.
	 * 
	 * If the first sequence of non-whitespace characters in str is not a valid
	 * integral number, or if no such sequence exists because either str is
	 * empty or it contains only whitespace characters, no conversion is
	 * performed.
	 * 
	 * If no valid conversion could be performed, a zero value is returned. If
	 * the correct value is out of the range of representable values, INT_MAX
	 * (2147483647) or INT_MIN (-2147483648) is returned.
	 * 
	 * @param str
	 * @return
	 */
	public static int atoi(String str) {
		// remove space, check not all space
		if (str == null || str.trim().length() == 0) 
			return 0;
		str = str.trim();
		char sign = str.charAt(0);
		// none of the valid case is true
		if (!(str.length() == 0 || sign == '+' || sign == '-' || (sign >= '0' && sign < '9')))
			return 0;
		// case by sign
		if (str.charAt(0) == '-') {
			return -atoiPos(str.substring(1), false);
		} else if (str.charAt(0) == '+') {
			return atoiPos(str.substring(1), true);
		} else {
			return atoiPos(str, true);
		}
	}

	public static void main(String[] args) {
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(atoi(""));
		System.out.println(atoi(null));
		System.out.println(atoi("+-2"));

		System.out.println(atoi("-1"));
		System.out.println(atoi("1"));
		System.out.println(atoi("  12zz  "));
		System.out.println(atoi("4312345678"));
		System.out.println(atoi("4123456789"));
		System.out.println(atoi("-4312345678"));
		System.out.println(atoi("-4123456789"));
	}
}
