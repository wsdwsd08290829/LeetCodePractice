import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Parenthesis {
	public static Set<String> getParenthesis(int count) {
		if (count <= 0)
			return null;
		Set<String> set = new HashSet<String>();
		set.add("()");

		while (count > 1) {
			Set<String> tempSet = new HashSet<String>();
			for (String s : set) {

				// System.out.println("set: " + set);
				for (int i = 1; i <= s.length(); i++) {
					String temp = s.substring(0, i) + "()" + s.substring(i);
					// System.out.println(temp);
					if (!tempSet.contains(temp))
						tempSet.add(temp);
				}

			}
			// System.out.println("tempset" + tempSet);
			set = tempSet;
			count--;
		}

		return set;
	}

	public static void getParenthesis2(int count) { // count of ()
		char[] charArr = new char[count * 2];
		getParenthesis2(0, count, count, charArr);
	}

	public static void getParenthesis2(int total, int left, int right,
			char[] charArr) {
		if(left<0 || right<left)return;
		if (left == 0 && right == 0) {
			System.out.println(charArr);
		}
		if (left > 0) {
			charArr[total] = '(';
			//simulating pass by reference, do not change total or left 
			getParenthesis2(total+1, left-1, right, charArr);
		}
		if (right > left) {
			charArr[total] = ')';
			
		//	right--;
			getParenthesis2(total+1, left, right-1, charArr);
		}

	}

	public static void main(String[] args) {
		System.out.println(getParenthesis(3)); //take too musch space not valid for 8 + 
		getParenthesis2(3);

	}
}
