package basic;

import java.util.ArrayList;

public class PalindromPartition {
	public static ArrayList<ArrayList<String>> partition(String s) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
	 
		if (s == null || s.length() == 0) {
			return result;
		}
	 
		ArrayList<String> partition = new ArrayList<String>();
		addPalindrome(s, 0, partition, result);
	 
		return result;
	}
	 
	private static void addPalindrome(String s, int start, ArrayList<String> partition,
			ArrayList<ArrayList<String>> result) {
		//stop condition
		if (start == s.length()) {
			ArrayList<String> temp = new ArrayList<String>(partition);
			result.add(temp);
			return;
		}
	 
		for (int i = start + 1; i <= s.length(); i++) {
			String str = s.substring(start, i);
			if (isPalindrome(str)) {
				partition.add(str);
				addPalindrome(s, i, partition, result);
				partition.remove(partition.size() - 1);
			}
		}
	}
	 
	private static boolean isPalindrome(String str) {
		int left = 0;
		int right = str.length() - 1;
	 
		while (left < right) {
			if (str.charAt(left) != str.charAt(right)) {
				return false;
			}
	 
			left++;
			right--;
		}
	 
		return true;
	}
	public static void main(String[] args) {
		System.out.println(partition("abab"));
		System.out.println(minCut(partition("ababbbabbababa")));
	}
	public static ArrayList<String> minCut(ArrayList<ArrayList<String>> result){
		ArrayList<String> minList = null;
		int min = Integer.MAX_VALUE;
		for(ArrayList<String> list: result){
			if(list.size()<min){
				min = list.size();
				minList = list;
			}
		}
		return minList;
	}
}
