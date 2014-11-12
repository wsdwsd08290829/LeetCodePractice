package leet;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {
	public List<String> generateParenthesis(int n) {
		List<String> result = new ArrayList<String>();

		generateParenthesisHelper(n, n, result, "");
		return result;
	}

	// if has left, add left "(", otherwise add ")"
	private void generateParenthesisHelper(int left, int right,
			List<String> result, String str) {
		if (left > right)
			return;
		if (left == 0 && right == 0)
			result.add(str);
		if (left > 0) {
			generateParenthesisHelper(left - 1, right, result, str + "(");
		}
		if (right > 0) {
			generateParenthesisHelper(left, right - 1, result, str + ")");
		}
	}

	// same as above
	private void generateParenthesisHelper1(int left, int right,
			List<String> result, String str) {
		if (left > right)
			return;
		if (left == 0 && right == 0)
			result.add(str);
		if (left > 0) {
			str += "(";
			generateParenthesisHelper(left - 1, right, result, str);
			str = str.substring(0, str.length() - 1);
		}
		if (right > 0) {
			str += ")";
			generateParenthesisHelper(left, right - 1, result, str);
			str = str.substring(0, str.length() - 1);
		}
	}

	/******** bonus:卡塔兰数: C(2n,n) -C(2n, n+1) *********/
	// first 20 start from n=0: 1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796,
	// 58786, 208012, 742900, 2674440, 9694845, 35357670, 129644790, 477638700,
	// 1767263190
	// http://zh.wikipedia.org/wiki/%E5%8D%A1%E5%A1%94%E5%85%B0%E6%95%B0
	// f(n+1) = 2*(2n+1)/(n+2) * f(n); recursion
	public static int katalan(int n) {
		if (n == 0 || n == 1)
			return 1;
		return 2 * (2 * (n - 1) + 1) * katalan(n - 1) / ((n - 1) + 2);
	}

	// f(n+1) = sum of f(i)*f(n-i); i from 0 to n //iteration
	public static int katalan1(int n) {
		int katalan[] = new int[15];
		katalan[0] = 1;
		katalan[1] = 1;
		for (int i = 2; i < 15; i++) { // correspond to f(n+1) above
			// sum of f(i) * f(n-i)
			for (int j = 0; j < i; j++) { // i in f(i) above
				katalan[i] += katalan[j] * katalan[i - 1 - j];
			}
		}
		return katalan[n];
	}

	public static void main(String[] args) {
		GenerateParenthesis gp = new GenerateParenthesis();
		System.out.println(gp.generateParenthesis(3));
		System.out.println(katalan(10));
		System.out.println(katalan1(10));
	}
}
