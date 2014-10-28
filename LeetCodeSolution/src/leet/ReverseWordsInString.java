package leet;
/**
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * @author sidawang
 * ! catch:  
 *  1.leading or trailing spaces
 *  2.multiple spaces( cannot use split(" "))
 */
public class ReverseWordsInString {
	public static String reverseWrodsInString(String s){
		String[] sArr = s.split("\\s+");
		StringBuilder sb = new StringBuilder();
		for(String str : sArr){
			sb.insert(0, str);
			sb.insert(0, " ");
		}
		return sb.toString().trim();
	}
	public static void main(String[] args) {
		String s = "the sky is    blue  ";
		System.out.println(reverseWrodsInString(s));
	}
}
