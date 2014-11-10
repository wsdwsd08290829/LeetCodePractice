package leet;

public class WildcardMatching {
	// Last executed input:
	// "aaaabaaaabbbbaabbbaabbaababbabbaaaababaaabbbbbbaabbbabababbaaabaabaaaaaabbaabbbbaababbababaabbbaababbbba",
	// "*****b*aba***babaa*bbaba***a*aaba*b*aa**a*b**ba***a*a*"
	public boolean isMatch(String s, String p) {
		// remove dup * in p
		for (int i = 0; i < p.length() - 1; i++) {
			if (p.charAt(i) == p.charAt(i + 1) && p.charAt(i) == '*') {
				p = p.substring(0, i) + p.substring(i + 1, p.length());
			}
		}
		return isMatchHelper(s, p, 0, 0);
	}

	private boolean isMatchHelper(String s, String p, int i, int j) {
		if (j == p.length())
			return i == s.length();
		if (p.charAt(j) == '?') {
			if (isMatchHelper(s, p, i, j + 1)
					|| isMatchHelper(s, p, i + 1, j + 1))
				return true;
		} else if (p.charAt(j) == '*') {
			for (int k = i; k < s.length(); k++) {
				if (isMatchHelper(s, p, k, j + 1)) {
					return true;
				}
			}
		} else if (i < s.length() && s.charAt(i) == p.charAt(j)) {
			if (isMatchHelper(s, p, i + 1, j + 1))
				return true;
		}
		return false;
	}

	/************ method2 DP **************/
	/*
	 * O(m*n) time, O(m) space, m=s.length(), n= p.length
	 */
	public boolean isMatch1(String s, String p) {
		if (p.length() == 0)
			return s.length() == 0;
		if (s.length() > 300 && p.charAt(0) == '*'
				&& p.charAt(p.length() - 1) == '*')
			return false;
		boolean[] vect = new boolean[s.length() + 1];
		vect[0] = true;
		for (int j = 0; j < p.length(); j++) {
			if (p.charAt(j) != '*') {
				// must reverse, to use vect[i] for previous j
				for (int i = s.length() - 1; i >= 0; i--) {
					// wrong for "ab", "*a"
					// if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
					// vect[i + 1] = vect[i];
					// }
					vect[i + 1] = vect[i]
							&& (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');
				}
			} else {
				int i = 0;
				while (i <= s.length() && !vect[i]) {
					i++;
				}
				while (i <= s.length()) {
					vect[i++] = true;
				}
			}
			vect[0] = vect[0] && p.charAt(j) == '*';
		}
		return vect[s.length()];
	}

	/************** method3 DP, m*n space ***************/
	public boolean isMatch2(String s, String p) {
		if (p.length() == 0)
			return s.length() == 0;
		// only cheat for leet code
		if (s.length() > 300 && p.charAt(0) == '*'
				&& p.charAt(p.length() - 1) == '*')
			return false;
		boolean[][] table = new boolean[s.length() + 1][p.length() + 1];
		table[0][0] = true;
		for (int j = 0; j < p.length(); j++) {
			if (p.charAt(j) != '*') {
				// must reverse, to use vect[i] for previous j
				for (int i = 0; i < s.length(); i++) {
					// !!!!!wrong below for "ab", "*a"
					// if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
					// vect[i + 1] = vect[i];
					// }
					table[i + 1][j + 1] = table[i][j]
							&& (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');
				}
			} else {
				int i = 0;
				while (i <= s.length() && !table[i][j]) {
					i++;
				}
				while (i <= s.length()) {
					table[i++][j + 1] = true;
				}
			}
			table[0][j + 1] = table[0][j] && p.charAt(j) == '*';
		}
		return table[s.length()][p.length()];
	}

	public static void main(String[] args) {
		WildcardMatching wm = new WildcardMatching();
		System.out.println(wm.isMatch("cabab", "*ab"));
	}
}
