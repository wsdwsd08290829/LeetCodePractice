package leet;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PascalTrangle {

	public static int combination(int base, int choose) {
		if (base < choose)
			return 0;
		BigInteger numerator = factorial(base);
		BigInteger denominatior = factorial(base - choose).multiply(
				factorial(choose));
		return Integer.valueOf((numerator.divide(denominatior)).toString());
	}

	// for pascal triangle is better to create a array of length == level, to
	// avoid repeat all factorial in combination call
	// here just for simplicity
	// ! long, eg 13 is 6227020800 > 2^32 = 4294967296
	// BigInteger eg 21 is ... > 2^64
	public static BigInteger factorial(int i) {
		if (i == 0)
			return BigInteger.valueOf(1);
		// return i*factorial(i-1);
		return BigInteger.valueOf(i).multiply(factorial(i - 1));
	}

	/**
	 * level 3: 1 3 3 1, level 4: 1 4 6 4 1 .. -> combination (4, i), i from 0
	 * to level/2 + 1 # of item == rowIndex + 1;
	 * 
	 * @param rowIndex
	 *            : =level
	 * 
	 *            slower way according definition: level(i) = prevLevel(i) +
	 *            prevLevel(i-1)
	 */
	public static List<Integer> pascalTriangleRow(int rowIndex) {
		if (rowIndex < 0)
			throw new RuntimeException("invalid rowIndex, must >=0");
		ArrayList<Integer> row = new ArrayList<Integer>(rowIndex + 1);
		// eg 1 4 6 4 1 i= 0,1, 2, 3,4 rowIndex = 4
		for (int i = 0; i < rowIndex + 1; i++) {
			if (i < rowIndex / 2 + 1) { // first half + (middle)
				row.add(i, combination(rowIndex, i));
			} else {
				row.add(i, combination(rowIndex, rowIndex - i));
			}
		}
		return row;
	}

	public static List<List<Integer>> generate(int numRows) {
		ArrayList<List<Integer>> al = new ArrayList<List<Integer>>();
		if (numRows == 0)
			return al;
		if (numRows >= 1) { // set init state
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(1);
			al.add(temp);
		}
		for (int i = 2; i <= numRows; i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			ArrayList<Integer> prev = (ArrayList<Integer>) al
					.get(al.size() - 1);
			for (int j = 0; j <= prev.size(); j++) {
				if (j == 0) { // first
					temp.add(1);
				} else if (j == prev.size()) { // last
					temp.add(1);
				} else { // others
					temp.add(prev.get(j - 1) + prev.get(j));
				}
			}
			al.add(temp);
		}
		return al;
	}

	public static void main(String[] args) {
		System.out.println(factorial(21));
		System.out.println(factorial(13));
		System.out.println(combination(5, 2));
		System.out.println(pascalTriangleRow(21));
		System.out.println(pascalTriangleRow(13));
		System.out.println(pascalTriangleRow(4));
		System.out.println(pascalTriangleRow(0));
		System.out.println("---------");
		System.out.println(generate(0));
		System.out.println(generate(1));
		System.out.println(generate(4));
	}
}
