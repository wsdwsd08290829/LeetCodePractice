package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author sidawang
 * 
 */
// copy from
// https://oj.leetcode.com/problems/substring-with-concatenation-of-all-words/
// TODO myself
// sliding window, similar to minWindowSubstring, and longest no dup subsring
public class SubStringWithConcatenationOfAllWords {
	public List<Integer> findSubstring(String S, String[] L) {
		List<Integer> res = new ArrayList<Integer>();
		if (S == null || S.length() == 0 || L == null || L.length == 0)
			return res;
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		// add to map, !!may have duplicates
		for (int i = 0; i < L.length; i++) {
			if (map.containsKey(L[i])) {
				map.put(L[i], map.get(L[i]) + 1);
			} else {
				map.put(L[i], 1);
			}
		}
		int l = L[0].length();
		// !! way to loop; cross two loop for O(n) to cover all case and jump by
		// l
		for (int i = 0; i < l; i++) {
			HashMap<String, Integer> curMap = new HashMap<String, Integer>();
			int count = 0;
			int left = i;
			for (int j = i; j <= S.length() - l; j = j + l) {
				String str = S.substring(j, j + l);
				// if contains
				if (map.containsKey(str)) {
					// add to curMap
					if (curMap.containsKey(str))
						curMap.put(str, curMap.get(str) + 1);
					else
						curMap.put(str, 1);
					// if no dup, count++;
					if (curMap.get(str) <= map.get(str))
						count++;
					else {
						// while dup(curMap > map)
						while (curMap.get(str) > map.get(str)) {
							// && same as left, left+=l;
							String temp = S.substring(left,
									left + L[0].length());
							// adjust count and curMap val
							if (curMap.containsKey(temp)) {
								curMap.put(temp, curMap.get(temp) - 1);
								if (curMap.get(temp) < map.get(temp))
									count--;
							}
							// move left window till remove the prev dup
							left += L[0].length();
						}
					}
					// if count==L.length(), add left, update left, count, and
					// curMap
					if (count == L.length) {
						res.add(left);
						// if(left<)
						String temp = S.substring(left, left + L[0].length());
						// if(curMap.containsKey(temp)) //not necessary
						curMap.put(temp, curMap.get(temp) - 1);
						count--;
						left += L[0].length();
					}

				} else {
					curMap.clear();
					count = 0;
					left = j + l;
				}
				// if no contain: reset all;
			}
		}
		return res;
	}
}
