package leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
	static int counter;

	/*********************** problem 1 ********************/
	/**
	 * recursive(DFS) find result slow for long string, small dict slow for
	 * short s, large dict //O(s2.length*dict.length + (s2.length-1) *
	 * dict.length +...) => O( s.length^2 * dict.length) see worst case test
	 * 
	 * @param s
	 * @param dict
	 * @return
	 */
	public static boolean wordBreak(String s, Set<String> dict) {
		if (s == null || s == "")
			return false;
		for (int i = 0; i < s.length(); i++) {
			if (dict.contains(s.substring(0, i + 1))) {
				counter++;
				if (i == s.length() - 1)
					return true;
				else {
					if (wordBreak(s.substring(i + 1), dict))
						return true;
				}
			}
		}
		return false;
	}

	/**
	 * slow for short s, large dict //O(s2.length*dict.length + (s2.length-1) *
	 * dict.length +...) => O( s.length^2 * dict.length)
	 * 
	 * @param s
	 * @param dict
	 * @return
	 */
	public static boolean wordBreak1(String s, Set<String> dict) {
		for (String word : dict) {
			if (s.equals(word))
				return true;
			if (s.startsWith(word)) {
				counter++;
				if (wordBreak1(s.substring(word.length()), dict))
					return true;
			}
		}
		return false;
	}

	/**
	 * dynamic programming or BFS, it seem iteration is much faster than
	 * recursion s="aaaa....b" dict = {a, aa, aaa,aaaa} O(s.length*dic.length)
	 * 
	 * @param s
	 * @param dict
	 * @return
	 */
	private static boolean wordBreak2(String s, Set<String> dict) {
		// index of separatePoint means length of string that has match
		boolean[] separatePoint = new boolean[s.length() + 1];
		separatePoint[0] = true;
		// for each start point, mark all valid end point, till end
		int count = 0;
		for (int i = 0; i < s.length(); i++) {
			if (!separatePoint[i])
				continue;
			for (String word : dict) {
				if (s.startsWith(word, i)) {
					System.out.println("count" + count++);
					separatePoint[i + word.length()] = true;
					System.out.println(i + word.length());
					if (i + word.length() == s.length()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * DFS: http://blog.csdn.net/qiyating0808/article/details/39100659
	 */
	public boolean wordBreak3(String str, Set<String> dict) {
		if (dict == null)
			return false;
		if (str == null || str.length() == 0)
			return true;
		boolean[] f = new boolean[str.length() + 1];// 记录从0到i-1的匹配状态
		for (int i = 1; i <= str.length(); ++i) {
			if (!f[i] && dict.contains(str.substring(0, i)))
				f[i] = true;
			if (f[i]) {
				if (i == str.length())
					return true;
				for (int j = i + 1; j <= str.length(); ++j) {
					if (!f[j] && dict.contains(str.substring(i, j)))
						f[j] = true;
					if (f[j] && j == str.length())
						return true;
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		String s = "programcreek";

		Set<String> dict = new HashSet<String>();
		dict.add("a");
		dict.add("programcree");
		dict.add("progra");
		dict.add("creek");
		dict.add("program");

		// worst case test for recursion
		String s2 = "aaaaaaaaaab"; // 41
		Set<String> dict2 = new HashSet<String>();
		dict2.add("a");
		dict2.add("aa");
		dict2.add("aaa");
		dict2.add("aaaa");
		System.out.println(wordBreak1(s2, dict2));
		System.out.println("counter" + counter);
		counter = 0;
		System.out.println(wordBreak2(s2, dict));
		System.out.println("counter" + counter);

	}

}
