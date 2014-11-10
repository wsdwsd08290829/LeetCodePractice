package leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakII {
	/*********** problem 2 *******************/
	/**
	 * Given a string s and a dictionary of words dict, add spaces in s to
	 * construct a sentence where each word is a valid dictionary word. s =
	 * "catsanddog", dict = ["cat", "cats", "and", "sand", "dog"]. A solution is
	 * ["cats and dog", "cat sand dog"]
	 * 
	 * @param args
	 */
	// TODO bug exists
	public List<String> wordBreak21(String s, Set<String> dict) {

		List<String> result = new ArrayList<String>();
		List<String> temp = new ArrayList<String>();
		wordBreak21(s, dict, temp, result);
		return result;
	}

	// ??cannot print some cases (eg. "a aaaa aa")??????
	public void wordBreak21(String s, Set<String> dict, List<String> temp,
			List<String> result) {
		for (String word : dict) {
			if (s.equals("")) {
				String str = "";
				// form temp and save to result
				for (String t : temp) {
					str = str + " " + t;
				}

				if (!result.contains(str.substring(1))) {
					result.add(str.substring(1));
				}
			}
			if (s.startsWith(word)) {
				temp.add(word);
				wordBreak21(s.substring(word.length()), dict, temp, result);
				temp.remove(word);
			}
		}
	}

	// both 2 method below referenced
	// http://blog.csdn.net/linhuanmars/article/details/22452163
	// to pass leet need check wordbreak return true first;
	// t[i] = t[i-j](i to j is word in dict)
	/********************** method1 ******************/
	/**
	 * DP(more like bfs), "map": i to all string result end at i in s;
	 */
	public List<String> wordBreak(String s, Set<String> dict) {
		// or use map<integer, list<string>>
		//like here: http://blog.csdn.net/nicaishibiantai/article/category/2471825
		List<List<String>> result = new ArrayList<List<String>>();
		// "map":index i to all string result end at i in s
		for (int i = 0; i <= s.length(); i++) {
			result.add(new ArrayList<String>());
		}
		// init
		result.get(0).add("");
		boolean[] canReach = new boolean[s.length() + 1];
		canReach[0] = true;
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 0; j < i; j++) {
				String sub = s.substring(j, i);
				if (canReach[j] && dict.contains(sub)) {
					canReach[i] = true;
					// result.get(j).size() == 0?
					List<String> temp = result.get(i);
					for (String str : result.get(j)) {
						temp.add(str.length() == 0 ? str + sub : str + " "
								+ sub);
					}
				}
			}
		}
		return result.get(s.length());
	}

	/********************** method2 ******************/
	/**
	 * recursion(more like dfs)
	 * 
	 * @param args
	 */
	public List<String> wordBreak2(String s, Set<String> dict) {
		List<String> result = new ArrayList<String>();
		String temp = "";
		wordBreak2Helper(s, dict, 0, result, temp);
		return result;
	}

	public void wordBreak2Helper(String s, Set<String> dict, int start,
			List<String> result, String temp) {
		if (start == s.length()) {
			// result.add
			result.add(temp);
		}
		for (int i = start + 1; i <= s.length(); i++) {
			if (dict.contains(s.substring(start, i))) {
				// !!! creat new string, not change on old
				String newTemp = (temp.length() == 0) ? temp
						+ s.substring(start, i) : temp + " "
						+ s.substring(start, i);
				wordBreak2Helper(s, dict, i, result, newTemp);
			}
		}
	}

	public static void main(String[] args) {
		/****************** test problem2 **************/
		String s3 = "a";
		Set<String> dict3 = new HashSet<String>();

		dict3.add("a");
		WordBreakII wb = new WordBreakII();
		System.out.println(wb.wordBreak2(s3, dict3));
	}
}
