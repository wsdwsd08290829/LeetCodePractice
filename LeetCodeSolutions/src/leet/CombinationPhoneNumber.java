package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sidawang
 * Given a digit string, return all possible letter combinations that the number could represent.
 * as on 9 digit old mobile phone, see map below
 * DS: recursive
 * Complexity: # of digits?   (length of letter)^(# of digits)?
 * T(n) = T(n-1)* (length of letters) => O(n)
 */
public class CombinationPhoneNumber {
	public static List<String> letterCombinations(String digits) {
		if (digits == null || digits.length() == 0) {
			List<String> result = new ArrayList<String>();
			result.add("");
			return result;
		}
		// construct digit-letter map(could as class field)
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(2, "abc");
		map.put(3, "def");
		map.put(4, "ghi");
		map.put(5, "jkl");
		map.put(6, "mno");
		map.put(7, "pqrs");
		map.put(8, "tuv");
		map.put(9, "wxyz");
		// call recursive version to get result
		return letterCombinations(digits, map);
	}

	private static List<String> letterCombinations(String digits,
			Map<Integer, String> map) {
		List<String> result = new ArrayList<String>();
		// special case
		if (digits.length() == 1) {
			String letters = map.get(digits.charAt(0) - '0');
			if (letters != null) { // check is valid digit
				int index = 0;
				while (index <= letters.length() - 1) {
					result.add(letters.substring(index, index + 1));
					index++;
				}
			} else {
				result.add(""); // so that, other stack can append to it
			}
			return result;
		}
		// recursive call to get prevResult List(except for last digit)
		List<String> prevRes = letterCombinations(
				digits.substring(0, digits.length() - 1), map);
		// handle last digit
		int lastDigit = digits.charAt(digits.length() - 1) - '0';
		String letters = map.get(lastDigit);
		// append each letter to each prev result
		if (letters != null) { // check is valid digit
			for (String s : prevRes) {
				for (int i = 0; i < letters.length(); i++) {
					result.add(s + letters.substring(i, i + 1));
				}
			}
		} else {
			result = prevRes; // do nothing at this stack
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(letterCombinations("23"));
		System.out.println(letterCombinations("123"));
		System.out.println(letterCombinations("231"));
	}
}
