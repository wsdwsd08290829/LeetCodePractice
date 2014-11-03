package leet;

public class AddBinaryString {
	public String addBinary(String a, String b) {
		if (a == null)
			a = "";
		if (b == null)
			b = "";
		String result = "";
		int sum = 0, carry = 0; // sum may be 0,1,2,3
		for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
			// i is ith from right. if one reach is max length, default is 0
			int first = 0, second = 0;
			if (i < a.length()) {
				first = a.charAt(a.length() - i - 1) - '0';
			}
			if (i < b.length()) {
				second = b.charAt(b.length() - i - 1) - '0';
			}
			// calculate result
			sum = first + second + carry;
			result = sum % 2 + result;
			carry = sum / 2;
		}
		return (carry == 1) ? "1" + result : result;
	}

	/************** same method as mine above, more succinct ***********/
	public String addBinary1(String a, String b) {
		String result = "";
		int m = a.length();
		int n = b.length();

		int tmp = 0;

		while (m + n > 0) {
			tmp += m > 0 ? a.charAt(--m) - '0' : 0;
			tmp += n > 0 ? b.charAt(--n) - '0' : 0;

			result = tmp % 2 + result;
			tmp /= 2;
		}
		return (tmp == 0) ? result : "1" + result;
	}

	public static void main(String[] args) {
		System.out.println(new AddBinaryString().addBinary(null, "11"));
	}
}
