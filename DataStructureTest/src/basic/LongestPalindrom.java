package basic;

import java.util.ArrayList;
import java.util.List;

public class LongestPalindrom {
	public static List<String> allLongestStrings = new ArrayList<String>();
	public static void main(String[] args) {
		String s = "bcbdsece";//"dabcba";
		System.out.println(runLongestPalindrom(s));
		
		runLongestPalindromDynamicProgramming(s);
		System.out.println(allLongestStrings);
		
		///System.out.println(longestPalindrome2(s));
	}
	
	public static String runLongestPalindrom(String s){
		String result="";
		String temp="";
		for(int i = 0; i< s.length(); i++){
			temp = findLongestCenteredPalindrom(s, i);
			if(result.length()<temp.length()){
				result = temp;
			}
		}
		return result;
	}

	private static String findLongestCenteredPalindrom(String s, int i) {
		int left = i-1;
		int right = i+1;
		String temp= "";
		while(left>=0 && right<=s.length()-1){
			if(s.charAt(left)!=s.charAt(right)){
				return s.substring(left+1, right-1);
			}
			left--;
			right++;
		}
		return s.substring(left+1, right);  //right not inclusive
	}
	private static void runLongestPalindromDynamicProgramming(String s){
		
		int[][] table = new int[s.length()][s.length()];
		init(table, s);
		printTable(table);
		for(int l = 3; l<=s.length(); l++){
			boolean removed = false;
			for(int i=0; i<=s.length()-l;i++){
				int j= i+l-1;
			
				if(s.charAt(i) ==s.charAt(j)){
					
					table[i][j] = table[i+1][j-1];
				}else{
					table[i][j] = 0;
				}
				if(table[i][j] == 1){
					if(!removed){
						System.out.println(s);
						allLongestStrings.removeAll(allLongestStrings);
						removed = true;
					}
					System.out.println(s);
					allLongestStrings.add(s.substring(i, j+1));
				}
			}
		}
		
	}

	private static void init(int[][] table, String s) {
		boolean removed = false;
		for(int i=0; i<s.length();i++){
			table[i][i]=1; //single char is palindrom
			allLongestStrings.add(((Character)s.charAt(i)).toString());
			
			if(i+1<=s.length()-1 && s.charAt(i) == s.charAt(i+1)){ //two consecutive is palindrom
				if(!removed){
					allLongestStrings.removeAll(allLongestStrings);
					removed = true;
				}
				allLongestStrings.add(s.substring(i, i+2));
				System.out.println(s);
				table[i][i+1] = 1;
			}
		}
		/*for(int i= 0; i<s.length();i++){
			for(int j= 0; j<s.length();j++){
				if(table[i][j]!=1){table[i][j]=0;}
			}
		}*/
	}
	public static String longestPalindrome2(String s) {
		if (s == null)
			return null;
	 
		if(s.length() <=1)
			return s;
	 
		int maxLen = 0;
		String longestStr = null;
	 
		int length = s.length();
	 
		int[][] table = new int[length][length];
	 
		//every single letter is palindrome
		for (int i = 0; i < length; i++) {
			table[i][i] = 1;
		}
		printTable(table);
	 
		//e.g. bcba
		//two consecutive same letters are palindrome
		for (int i = 0; i <= length - 2; i++) {
			if (s.charAt(i) == s.charAt(i + 1)){
				table[i][i + 1] = 1;
				longestStr = s.substring(i, i + 2);
			}	
		}
		printTable(table);
		//condition for calculate whole table
		for (int l = 3; l <= length; l++) {
			for (int i = 0; i <= length-l; i++) {
				int j = i + l - 1;
				if (s.charAt(i) == s.charAt(j)) {
					table[i][j] = table[i + 1][j - 1];
					if (table[i][j] == 1 && l > maxLen)
						longestStr = s.substring(i, j + 1);
				} else {
					table[i][j] = 0;
				}
				printTable(table);
			}
		}
	 
		return longestStr;
	}
	public static void printTable(int[][] x){
		for(int [] y : x){
			for(int z: y){
				System.out.print(z + " ");
			}
			System.out.println();
		}
		System.out.println("------");
	}
}
