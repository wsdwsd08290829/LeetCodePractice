package leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Anagrams {
	public List<String> anagrams(String[] strs) {
		// sort each string
		// add to map
		// generate result
		List<String> res = new ArrayList<String>();
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		for (int i = 0; i < strs.length; i++) {
			// char[] charArr = strs[i].toCharArray();
			// Arrays.sort(charArr);
			// !! or construct string from array
			// String str = new String(charArr);

			String s = strs[i];
			StringBuilder sb = new StringBuilder("");
			char[] chArr = s.toCharArray();
			Arrays.sort(chArr);
			for (char c : chArr) {
				sb.append(c);
			}

			if (map.containsKey(sb.toString())) {
				map.get(sb.toString()).add(strs[i]);
			} else {
				List<String> list = new ArrayList<String>();
				list.add(strs[i]);
				map.put(sb.toString(), list);
			}
		}
		for (List<String> list : map.values()) {
			if (list.size() > 1) {
				res.addAll(list);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		char[] charr = { 'a', 'b', 'c' };
		System.out.println((String) charr.toString());
		Set<String> set = new HashSet<String>();
		set.add("");
		System.out.println(set.contains(""));
	}
}
