package basic;

public class StringReverse {
	public static void main(String[] args) {
		StringBuilder s = new StringBuilder("abcde");
		String s1 = "abcde";
		int i=0;
		String s2 = "";
		int j= s.length()-1;
		//StringBuilder inplace
		while(i<=s.length()/2){
			char temp = s.charAt(i);
			s.setCharAt(i, s.charAt(j));
			s.setCharAt(j, temp);
			i++; j--;
		}
		i=0;
		//String 
		while(i<s1.length()){
			s2 = s1.charAt(i++) +s2;
		}
		System.out.println(s);
		System.out.println(s2);
	}
}
