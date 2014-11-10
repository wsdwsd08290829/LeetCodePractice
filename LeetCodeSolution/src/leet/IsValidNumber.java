package leet;

public class IsValidNumber {
	// +-, float, exp
	public boolean isNumber(String s) {
		if (s == null || s.trim().length() == 0)
			return false;
		s = s.trim();
		// !!for "." or "e"
		if (s.charAt(0) == '-' || s.charAt(0) == '+') // !!for neg, pos
			s = s.substring(1);
		if (s.length() == 1 && (s.charAt(0) < '0' || s.charAt(0) > '9'))
			return false;
		boolean doubleFlag = false;
		boolean expFlag = false;
		for (int i = 0; i < s.length(); i++) {
			// check for floating point
			if (s.charAt(i) == '.') {
				if (!doubleFlag && !expFlag) { // for xe1.x
					doubleFlag = true;
				} else {
					return false; // two . met
				}
				continue;
			}
			// check for exponential
			if (s.charAt(i) == 'e' || s.charAt(i) == 'E') {
				if (i == 0 || i == s.length() - 1)
					return false;
				if (!expFlag) { // !!for .e is false, 3.e4 is true
					if (s.charAt(i - 1) == '.' && i - 1 == 0)
						return false;
					expFlag = true;
					if ((s.charAt(i + 1) == '-' || s.charAt(i + 1) == '+')) {
						if ((i + 1) != s.length() - 1) {
							i++;
						} else {
							return false;
						}
					}
				} else {
					return false;
				}
				continue;
			}
			// check for digits
			if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
				continue;
			}
			// remove invalid
			return false;

		}
		return true;
	}
	/************* method2 ************/
	// http://blog.csdn.net/linhuanmars/article/details/23809661
	public boolean isNumber1(String s) {
		if (s == null)
			return false;
		s = s.trim();
		if (s.length() == 0)
			return false;
		boolean dotFlag = false;
		boolean eFlag = false;
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
			case '.':
				if (dotFlag
						|| eFlag
						|| ((i == 0 || !(s.charAt(i - 1) >= '0' && s
								.charAt(i - 1) <= '9')) && (i == s.length() - 1 || !(s
								.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9'))))
					return false;
				dotFlag = true;
				break;
			case '+':
			case '-':
				if ((i > 0 && (s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E'))
						|| (i == s.length() - 1 || !(s.charAt(i + 1) >= '0'
								&& s.charAt(i + 1) <= '9' || s.charAt(i + 1) == '.')))
					return false;
				break;
			case 'e':
			case 'E':
				if (eFlag || i == s.length() - 1 || i == 0)
					return false;
				eFlag = true;
				break;
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
				break;
			default:
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(46.e3);
	}
}
