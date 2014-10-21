package basic;

public class StringToInteger {
	public static int stringToInt(String s){
		
		//You have to check if a String == null before call length(), otherwise it will throw NullPointerException.
		if(s==null || s.trim().length() == 0)return 0; //empty or null or .. new String()
		s=s.trim();
		boolean positive = true;
		int index = 0;
		int result = 0;
			
		if(s.charAt(0)=='-'){
			positive = false;
			index++;
		}else if(s.charAt(0)=='+')index++;
		
		while(index < s.length() && s.charAt(index)>='0'&& s.charAt(index)<='9'){
			result = result * 10+(s.charAt(index)-'0');
			index++;
		}
		if(!positive)return -result;
		return result;
	}
	public static void main(String[] args) {
		System.out.println(stringToInt(null)+100);
		System.out.println(stringToInt("  -123 ")+100);
		StringBuilder s = new StringBuilder("abcde");
		System.out.println(s.reverse());
	}
	
}
