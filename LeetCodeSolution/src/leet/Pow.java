package leet;

public class Pow {
	/**
	 * Time Limit Exceeded ?????? Last executed input: 0.00001, 2147483647
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	public double pow(double x, int n) {
		if (n >= 0)
			return powHelper1(x, n);
		else
			return 1 / powHelper1(x, -n);
	}

	// n always >0;
	public double powHelper(double x, int n) {
		if (x == 0)
			return 0;
		if (n == 1)
			return x;
		if (n == 0)
			return 1;
		if (n % 2 == 0)
			return powHelper(x, n / 2) * powHelper(x, n / 2); // duplicate twice
		else
			return x * powHelper(x, (n - 1) / 2) * powHelper(x, (n - 1) / 2);
	}

	// refactorred
	public double powHelper1(double x, int n) {
		if (x == 0)
			return 0;
		if (n == 1)
			return x;
		if (n == 0)
			return 1;
		double half = powHelper1(x, n / 2);
		if (n % 2 == 0)
			return half * half;
		else
			return x * half * half;
	}

	/**** same idea: harder to understand ***/
	double pow1(double x, int n) {
		if (n == 0)
			return 1.0;
		double half = pow1(x, n / 2);
		if (n % 2 == 0) {
			return half * half;
		} else if (n > 0) {
			return half * half * x;
		} else {
			return half * half / x;
		}
	}

	/*************** method2 ****************/
	// copied from: http://blog.csdn.net/linhuanmars/article/details/20092829
	public double pow2(double x, int n) {
		if (n == 0)
			return 1.0;
		double res = 1.0;
		if (n < 0) {
			if (x >= 1.0 / Double.MAX_VALUE || x <= 1.0 / -Double.MAX_VALUE)
				x = 1.0 / x;
			else
				return Double.MAX_VALUE;
			if (n == Integer.MIN_VALUE) {
				res *= x;
				n++;
			}
		}
		n = Math.abs(n);
		boolean isNeg = false;
		if (n % 2 == 1 && x < 0) {
			isNeg = true;
		}
		x = Math.abs(x);
		while (n > 0) {
			if ((n & 1) == 1) {
				if (res > Double.MAX_VALUE / x)
					return Double.MAX_VALUE;
				res *= x;
			}
			x *= x;
			n = n >> 1;
		}
		return isNeg ? -res : res;
	}

	public static void main(String[] args) {
		Pow p = new Pow();
		System.out.println(p.pow(0, 1));
		System.out.println(p.pow(1, 2));
		System.out.println(p.pow(-2, 2));
		System.out.println(p.pow(-2, 3));
		System.out.println(p.pow(2, -2));
		System.out.println(p.pow1(2, -3));
		System.out.println(Double.MIN_VALUE);
		System.out.println(Math.abs(Double.MIN_VALUE));
	}
}
