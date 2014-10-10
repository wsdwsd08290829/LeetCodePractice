package basic;

import java.util.HashMap;
import java.util.Map;

public class LongestNonrepeatSubstring {
	public static int longestSubstring(String s){
		String max_str = "";
		int[] visitedPositionOfChar = new int[256];
		int prev_index = 0; //most recently visited index
		int cur_len = 1;
		int max_len = 1;
		for(int i=0;i< visitedPositionOfChar.length;i++){
			visitedPositionOfChar[i] = -1;
		}
		
		visitedPositionOfChar[s.charAt(0)] = 0;  //currentChar: last visited index
		for(int i=1;i<s.length();i++){
			prev_index = visitedPositionOfChar[s.charAt(i)];
			if(prev_index == -1 || prev_index < i-cur_len){
				cur_len++;
			}else{
				if(cur_len > max_len){
					max_len = cur_len;
					max_str = s.substring(i-cur_len, i);
				}
				cur_len = i-prev_index;
				
			}
			visitedPositionOfChar[s.charAt(i)] = i;
		}
		if(cur_len > max_len){
			max_len = cur_len;
			max_str = s.substring(s.length()-cur_len, s.length());
		}
		System.out.println(max_str);
		return Math.max(cur_len, max_len);
	}
	public static void main(String[] args) {
		System.out.println(longestSubstring("abcadebebb")); //should be 5
		System.out.println(longestSubstring("ABDEFGABEF")); //should be 5
		System.out.println(longestSubstring("GEEKSFORG")); //should be 5
		System.out.println("----------");
		System.out.println(longestSubstringNUnique("abcbbbbcccbdddadacb",2)); //should be 5
	}
	//currently only for two
	public static int longestSubstringNUnique(String s, int n){
		n=2;
		int cur_len = 1;
		int max_len = 1;
		String max_str = "";
		int diff = 1;
		int end_index = 0;
		int start_index = 0;
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		map.put(s.charAt(0), 0);
		for(int i=0;i<s.length();i++){
		//	while(diff<=n){
				map.put(s.charAt(i), i);
				diff = map.size();
				end_index=i;
				if(diff>n){
					if(end_index-start_index > max_len){
						max_len = end_index-start_index;
						max_str = s.substring(start_index, end_index);
					}
					//first not start, not end
					int temp = i-1;
					while(s.charAt(temp) == s.charAt(temp-1))temp--;
					start_index = temp;
					
					map.clear();
					map.put(s.charAt(start_index),start_index);
					map.put(s.charAt(end_index), end_index);
				}
		//	}
		}
		if(end_index-start_index>max_len)max_str = s.substring(start_index, end_index);
		System.out.println(max_str);
		return Math.max(max_len, end_index-start_index);
	}
}
