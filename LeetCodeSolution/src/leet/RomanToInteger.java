package leet;

import java.util.HashMap;
import java.util.Map;

/**
 * I（1）、V（5）、X（10）、L（50）、C（100）、D（500）和M（1000）; 右加左减(IV:4, VI:6);
 * 左减的数字有限制，仅限于I、X、C。比如45不可以写成VL，只能是XLV
 * 
 * @author sidawang
 * 
 */
public class RomanToInteger {
	public int romanToInt(String s) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("M", 1000);
		map.put("D", 500);
		map.put("C", 100);
		map.put("L", 50);
		map.put("X", 10);
		map.put("V", 5);
		map.put("I", 1);
		int result = 0;
		int i = s.length() - 2;
		while (i > -2) {
			//if i>=0, has 2 char to check, && if leftchar val < rightchar val => do substraction
			if (i >= 0
					&& map.get(s.substring(i, i + 1)) < map.get(s.subSequence(
							i + 1, i + 2))) {
				result += map.get(s.subSequence(i + 1, i + 2))
						- map.get(s.substring(i, i + 1));
				i -= 2;
			} else {  //otherwise: add rightchar val;
				result += map.get(s.substring(i + 1, i + 2));
				i -= 1;
			}
		}
		return result;
	}

	public String intToRoman(int num) {
		String str = "";
		String symbol[] = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
				"IX", "V", "IV", "I" };
		int value[] = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		int i = 0;
		while (num != 0) {
			if (num >= value[i]) {
				num -= value[i];
				str += symbol[i];
			} else {
				i++;
			}
		}
		return str;
	}

	public static void main(String[] args) {
		System.out.println(new RomanToInteger().intToRoman(111));
		System.out.println(new RomanToInteger().romanToInt("CMXI"));
	}
}
