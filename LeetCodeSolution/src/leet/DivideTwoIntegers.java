package leet;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * 
 * @author sidawang
 * 
 */
public class DivideTwoIntegers {
	/***************** method 1 *************/
	/**
	 * time limit exceed: 2100000000/1
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide(int dividend, int divisor) {
		int result = 0;
		// !!!!!, 1000... with 31 0s: -2147483648
		if (dividend == Integer.MIN_VALUE) {
			result = 1; // adjust result;
			dividend += Math.abs(divisor); // minus divisor
		}
		// !!!!! -Integer.MIN_VALUE = -2147483648
		// !!!!! -Integer.MIN_VALUE + Integer.MIN_VALUE = 0
		if (divisor == Integer.MIN_VALUE)
			return result;

		boolean isNeg = false;
		if (dividend < 0 && divisor > 0) {
			isNeg = true;
			dividend = -dividend;
		}
		if (dividend > 0 && divisor < 0) {
			isNeg = true;
			divisor = -divisor;
		}
		int remain = dividend;

		while (remain >= divisor) {
			remain -= divisor;
			result++;
		}
		return isNeg ? -result : result;
	}

	/***************** method 2 *************/
	// O(logn), calculate base by << k; add 2^k to result
	public int divide1(int dividend, int divisor) {
		if (divisor == 0)
			return Integer.MAX_VALUE;
		int result = 0;
		int digit = 0;

		// !!!!!, 1000... with 31 0s: -2147483648
		if (dividend == Integer.MIN_VALUE) {
			result = 1; // adjust result;
			dividend += Math.abs(divisor); // minus divisor
		}
		// !!!!! -Integer.MIN_VALUE = -2147483648
		// !!!!! -Integer.MIN_VALUE + Integer.MIN_VALUE = 0
		if (divisor == Integer.MIN_VALUE)
			return result;

		boolean isNeg = ((dividend ^ divisor) >>> 31 == 1);
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		// first part incase loop back, (base << 1 > base) &&
		// now base is largest value that smaller than dividend
		// !!!do not use base<<1 <= dividend//overflow
		while (divisor <= (dividend >> 1)) {
			divisor = divisor << 1;
			digit++;
		}

		while (digit >= 0) {
			if (dividend >= divisor) {
				dividend -= divisor;
				result += 1 << digit;
			}
			digit--;
			divisor >>= 1;
		}
		return isNeg ? -result : result;
	}

	public static void main(String[] args) {
		DivideTwoIntegers dti = new DivideTwoIntegers();
		System.out.println(-Integer.MIN_VALUE);
		System.out.println(-Integer.MIN_VALUE + Integer.MIN_VALUE);
		System.out.println("-----");
		System.out.println(dti.divide1(-2147483648, 1));
		System.out.println(dti.divide(-2147483648, 1));
		System.out.println("-----");
		System.out.println(2147483647 + 1);
		System.out.println(2147483647 + 2);
	}
}
