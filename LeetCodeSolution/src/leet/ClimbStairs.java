package leet;

public class ClimbStairs {
	/*********** method1 ***********/
	// dynamic programming
	// T(j) = T(j-1)+T(j-2)
	public int climbStairs(int n) {
		if (n <= 0)
			return 0;
		if (n == 1)
			return 1;
		int[] T = new int[n + 1];
		T[0] = 1;
		T[1] = 1;
		for (int i = 2; i <= n; i++) {
			T[i] = T[i - 1] + T[i - 2];
		}
		return T[n];
	}

	/*********** method2 ***********/
	// recursive, slow
	public int climbStairs1(int n) {
		if (n <= 0)
			return 0;
		if (n == 1)
			return 1;
		if (n == 2)
			return 2;
		return climbStairs1(n - 1) + climbStairs1(n - 2);
	}

	public static void main(String[] args) {
		System.out.println(new ClimbStairs().climbStairs(44));
		// System.out.println(new ClimbStairs().climbStairs1(44));
	}
}
