package leet;

public class LongestCommonPrefix {
	/**
	 * find 
	 * @param strs
	 * @return
	 */
    public static String longestCommonPrefix(String[] strs) {
    	String result = "";
    	if(strs == null || strs.length == 0)return "";
    	String minStr = strs[0];
        for(String str: strs){
        	if(str.length()<minStr.length())minStr = str;
        }
        for(int i= 0; i< minStr.length();i++){
        	for(String str:strs){
        		if(str.charAt(i)!=minStr.charAt(i))return result;
        	}
        	result += minStr.charAt(i);
        }
        return result;
    }
    public static void main(String[] args) {
		String[] strs = {"abc", "ab", "abcde", "abdec"};
		System.out.println(longestCommonPrefix(strs));
	}
}
