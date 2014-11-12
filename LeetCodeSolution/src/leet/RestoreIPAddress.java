package leet;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
	public List<String> restoreIpAddresses(String s) {
		List<String> result = new ArrayList<String>();
		if (s == null || s.length() < 4 || s.length() > 12)
			return result; // !! incase too long or too short

		List<String> sections = new ArrayList<String>();
		restoreIpAddressesHelper(s, sections, result);
		return result;
	}

	private void restoreIpAddressesHelper(String s, List<String> sections,
			List<String> result) {
		if (sections.size() == 4) {
			if (s.equals("")) {
				boolean validFlag = true;
				for (String str : sections) {
					if (!isValid(str)) {
						validFlag = false;
					}
				}
				if (validFlag) {
					String str = "";
					for (int i = 0; i <= 3; i++) {
						str += sections.get(i) + ".";
					}
					result.add(str.substring(0, str.length() - 1));
				}
			}
		}
		for (int i = 1; i < 4; i++) {
			if (s.length() >= i) {
				sections.add(s.substring(0, i));
				restoreIpAddressesHelper(s.substring(i), sections, result);
				sections.remove(sections.size() - 1);
			}
		}
	}

	public boolean isValid(String str) {
		if (str.length() > 1 && str.charAt(0) == '0')
			return false; // !! "01" is invalid section
		int sect = Integer.parseInt(str);
		if (sect >= 0 && sect <= 255)
			return true;
		else
			return false;
	}

	/****** method2 ********/
	// http://oj.leetcode.com/problems/restore-ip-addresses/
	public ArrayList<String> restoreIpAddresses1(String s) {
		ArrayList<String> res = new ArrayList<String>();
		if (s == null || s.length() == 0)
			return res;
		helper(s, 0, 1, "", res);
		return res;
	}

	private void helper(String s, int index, int segment, String item,
			ArrayList<String> res) {
		if (index >= s.length())
			return;
		if (segment == 4) {
			String str = s.substring(index);
			if (isValid1(str)) {
				res.add(item + "." + str);
			}
			return;
		}
		for (int i = 1; i < 4 && (i + index <= s.length()); i++) {
			String str = s.substring(index, index + i);
			// better, eager check
			if (isValid1(str)) {

				if (segment == 1)
					// better, pass index of string, instead creating new
					// substring
					helper(s, index + i, segment + 1, str, res);
				else
					helper(s, index + i, segment + 1, item + "." + str, res);
			}
		}
	}

	// better, check any string comprehensive
	private boolean isValid1(String str) {
		if (str == null || str.length() > 3)
			return false;
		int num = Integer.parseInt(str);
		if (str.charAt(0) == '0' && str.length() > 1)
			return false;
		if (num >= 0 && num <= 255)
			return true;
		return false;
	}

	public static void main(String[] args) {
		String s = "a";
		System.out.println(s.substring(1).equals(""));
	}
}
