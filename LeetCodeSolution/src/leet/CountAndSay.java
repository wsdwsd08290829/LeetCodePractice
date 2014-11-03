package leet;

/**
 * 1, 11, 21, 1211, 111221, ... 11 is read off as "two 1s" or 21, 21 is read off
 * as "one 2, then one 1" or 1211
 * 
 * @author sidawang
 * 
 */
public class CountAndSay {
	public static String countAndSay(int n) {
		StringBuilder sb = new StringBuilder("1");
		for (int nth = 1; nth < n; nth++) {
			StringBuilder tempsb = new StringBuilder();
			int index = 0;
			int count = 1;
			// parse one string to a new string
			while (index < sb.length()) {
				// count for current index is 1;
				count = 1;
				// deal with continuous duplicates
				while (((index + 1) <= sb.length() - 1)
						&& sb.charAt(index) == sb.charAt(index + 1)) {
					count++;
					index++; // now index point to next repeat char
				}
				// here index point to last repeat char, update repeated
				// char:count and say
				char ch = sb.charAt(index);
				tempsb.append(count);
				tempsb.append(ch);
				// move to next nuique char
				index++;
			}
			sb = tempsb;
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		System.out.println(countAndSay(5));
	}
}
