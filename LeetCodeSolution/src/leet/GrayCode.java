package leet;

import java.util.ArrayList;
import java.util.List;

/********* method1 recursion **********/
// get result of n+1 from n;
// 0 + each result of grayCode(n), 1+ reversed Order of grayCode(n)
// 00, 01, 11, 10 <-> 0+ ... 1 10, 1 11, 1 01, 1 00
public class GrayCode {
	public List<Integer> grayCode(int n) {
		List<Integer> base = new ArrayList<Integer>();
		base.add(1);
		return grayCodeHelper(n, base);
	}

	private List<Integer> grayCodeHelper(int n, List<Integer> base) {
		List<Integer> result = new ArrayList<Integer>();
		if (n == 0) {
			result.add(0); // !!
			return result;
		}
		List<Integer> firstHalf = grayCodeHelper(n - 1, base);
		result.addAll(firstHalf);
		for (int i = firstHalf.size() - 1; i >= 0; i--) {
			result.add(base.get(0) + firstHalf.get(i));
		}
		base.set(0, base.get(0) << 1); // !!
		return result;
	}

	// better recursion on line
	public ArrayList<Integer> grayCode2(int n) {
		if (n == 0) {
			ArrayList<Integer> result = new ArrayList<Integer>();
			result.add(0);
			return result;
		}
		ArrayList<Integer> tmp = grayCode2(n - 1);
		int addNumber = 1 << (n - 1);
		ArrayList<Integer> result = new ArrayList<Integer>(tmp);
		for (int i = tmp.size() - 1; i >= 0; i--) {
			result.add(addNumber + tmp.get(i));
		}
		return result;
	}

	/******* method2 iteration **********/
	public List<Integer> grayCode1(int n) {
		List<Integer> result = new ArrayList<Integer>();
		result.add(0);
		int base = 1;
		for (int i = 1; i <= n; i++) {
			// add reversed half
			for (int j = result.size() - 1; j >= 0; j--) {
				result.add(base + result.get(j));
			}
			base <<= 1;
		}
		return result;
	}

	public static void main(String[] args) {
		GrayCode gc = new GrayCode();
		System.out.println(gc.grayCode1(1));
		System.out.println(gc.grayCode1(2));
		System.out.println(gc.grayCode1(3));
	}
}
