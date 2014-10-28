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

	public static void main(String[] args) {
		System.out.println(factorial(21));
		System.out.println(factorial(13));
		System.out.println(combination(5, 2));
		System.out.println(pascalTriangleRow(21));
		System.out.println(pascalTriangleRow(13));
		System.out.println(pascalTriangleRow(4));
		System.out.println(pascalTriangleRow(0));
	}
}
