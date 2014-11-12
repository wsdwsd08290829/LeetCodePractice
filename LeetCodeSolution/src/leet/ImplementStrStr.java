package leet;

public class ImplementStrStr {
	// cheating: return haystack.indexOf(needle);
	// brute force
	public int strStr(String haystack, String needle) {
		if (haystack == null || needle == null
				|| needle.length() > haystack.length())
			return -1;
		if (needle.length() == 0) // !! needle is ""
			return 0;
		for (int i = 0; i <= haystack.length() - needle.length(); i++) {
			if (haystack.charAt(i) == needle.charAt(0)) {
				int temp = i;
				for (int j = 0; j < needle.length(); j++) {
					if (haystack.charAt(temp) != needle.charAt(j)) {
						break;
					}
					temp++;
				}
				if (temp == i + needle.length())
					return i;
			}
		}
		return -1;
	}

	/***** method2 *****/
	public int strStr1(String haystack, String needle) {
		for (int i = 0;; i++) {
			for (int j = 0;; j++) {
				if (j == needle.length())
					return i;
				if (i + j == haystack.length())
					return -1;
				if (needle.charAt(j) != haystack.charAt(i + j))
					break;
			}
		}
	}
	/***** method3 ******/
	// http://blog.csdn.net/linhuanmars/article/details/20276833

	/****** bonus ******/
	// Knuth–Morris–Pratt algorithm, Rabin-Karp algorithm,Boyer- Moore algorithm

}
