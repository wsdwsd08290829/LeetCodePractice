package leet;

public class Sqrt {
	/**
	 * divide and conquer
	 * 
	 * @param x
	 * @return
	 */
	public int sqrt(int x) {
		if (x < 2)
			return x;
		int low = 1;
		int high = x;
		int t = 0;
		while (low <= high) { // t * t != x never end for not int solution
			t = low + (high - low) / 2;
			// for not int solution, eg 2;
			if (t > (x) / t) {
				high = t - 1;
			} else if (t < (x) / t) {
				low = t + 1;
			} else if (t == (x) / t)
				return t;
		}
		if (t > x / t)
			return t - 1;
		return t;
	}

	/******** method2 ********/
	// http://zh.wikipedia.org/wiki/%E7%89%9B%E9%A1%BF%E6%B3%95
	/**
	 * 牛顿法（Newton's method）又称为牛顿-拉弗森方法（Newton-Raphson method） f(x_0)=
	 * (x_0-x)*f'(x_0)
	 * 
	 * @param args
	 */
	public int sqrt1(int x) {
		double x0 = x / (double) 2;
		double x1 = ((x0 + x / x0) / (double) 2);
		while (Math.abs(x1 - x0) > 10E-5) {
			x0 = x1;
			x1 = (x0 + x / x0) / (double) 2;
		}
		return (int) x1;
	}

	public static void main(String[] args) {
		System.out.println(new Sqrt().sqrt1(2147395599));
		System.out.println(new Sqrt().sqrt1(9));
		System.out.println(new Sqrt().sqrt1(5));
		System.out.println(new Sqrt().sqrt1(3));
		System.out.println(new Sqrt().sqrt1(2));
	}
}
