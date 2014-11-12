package leet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacter {
	/**
	 * map char to last visited index; if duplicates after last duplicate
	 * occurred index, update count and last; else count++ and save max; always
	 * update map;
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring(String s) {
		if (s == null)
			return 0;
		if (s.length() <= 1)
			return s.length();

		// map char to last visited index
		// or map char-'a' to last meet index, only apply to letter
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		// save last duplicate index;
		int last = -1;
		int count = 0;
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.containsKey(c) && map.get(c) > last) {
				last = map.get(c);
				count = i - last;
			} else {
				count++;
				max = Math.max(count, max);
			}
			map.put(c, i);
		}
		return max;
	}

	/******** method2 *******/
	// same idea, more clear, maintain a moving window
	public int lengthOfLongestSubstring1(String s) {
		if (s == null || s.length() == 0)
			return 0;
		HashSet<Character> set = new HashSet<Character>();
		int max = 0;
		int walker = 0;
		int runner = 0;
		while (runner < s.length()) {
			if (set.contains(s.charAt(runner))) {
				if (max < runner - walker) {
					max = runner - walker;
				}
				while (s.charAt(walker) != s.charAt(runner)) {
					set.remove(s.charAt(walker));
					walker++;
				}
				walker++;
			} else {
				set.add(s.charAt(runner));
			}
			runner++;
		}
		max = Math.max(max, runner - walker);
		return max;
	}

	public static void main(String[] args) {
		String s = "cdeaba";
		LongestSubstringWithoutRepeatingCharacter ls = new LongestSubstringWithoutRepeatingCharacter();
		System.out.println(ls.lengthOfLongestSubstring(s));
	}
}
