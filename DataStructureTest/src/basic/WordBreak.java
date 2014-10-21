package basic;

import java.util.HashSet;
import java.util.Set;

public class WordBreak {
	public static void main(String[] args) {
		String s = "programcreek";
		Set<String> dict  = new HashSet<String>();
		dict.add("a");dict.add("programcree");dict.add("program");dict.add("creek");
		System.out.println(find(s, dict));
		System.out.println(find2(s, dict));
	}
	//brute force recursive find rest: complexity(#dict* length of string);
	private static boolean find(String s, Set<String> dict) {
		for(String word : dict){
			if(s.equals(word))return true;
			if(s.startsWith(word)){
				s = s.substring(word.length());
				return find(s, dict);
			}
		}
		return false;
	}
	//better: dynamic programming
	private static boolean find2(String s, Set<String> dict){
		boolean [] separatePoint = new boolean[s.length()+1];
		separatePoint[0] = true;
		for(int i = 0; i< s.length(); i++){
			if(!separatePoint[i])continue;
			for(String word : dict){
				if(s.startsWith(word, i)){
					separatePoint[i+word.length()] = true;
					System.out.println(i+word.length());
					if(i+word.length() == s.length()){return true;}
				}
			}
		}
		return false;
	}
}
