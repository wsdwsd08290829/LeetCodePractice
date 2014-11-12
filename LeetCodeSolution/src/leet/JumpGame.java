package leet;

public class JumpGame {
	/**
	 * Each element in the array represents your maximum jump length at that
	 * position.Determine if you are able to reach the last index.
	 * 
	 * @param A
	 * @return
	 */

	/******* method 1 *******/
	public boolean canJump(int[] A) {
		return canJumpHelper(A, 0);
	}

	// recursiont slow, duplicate
	private boolean canJumpHelper(int[] A, int start) {
		if (start == A.length - 1)
			return true;
		for (int i = 1; i <= A[start]; i++) {
			if (canJumpHelper(A, start + i))
				return true;
		}
		return false;
	}

	/********** method 2 **********/
	// greedy, save and go till the most far as can
	public boolean canJump1(int[] A) {
		if (A == null || A.length == 0)
			return false;
		int furthest = 0;
		for (int i = 0; i < A.length && i <= furthest; i++) {
			furthest = Math.max(A[i] + i, furthest);
			if (furthest >= A.length - 1)
				return true;
		}
		return false;
	}

	/************** problem2: min # of jumps to end **************/
	public int jump(int[] A) {
		if (A == null || A.length <= 1)
			return 0;
		int i = 0;
		int boundary = 0;
		int furthest = 0;
		int count = 1;
		// A = [2,3,1,1,4]; boundary is index = 2; update boundary to 4
		// bfs, boundary is level order traversal of # of jumps
		// !!! i <= furthest //can reach the furthest, if cannot break
		while (i <= furthest && i < A.length) {
			// furthest can reach in next one jump
			furthest = Math.max(furthest, A[i] + i);
			if (furthest >= A.length - 1)
				return count;
			if (i == boundary) {
				boundary = furthest;
				count++;
			}
			i++;
		}
		if (furthest < A.length - 1)
			return 0;
		return count;
	}

	public static void main(String[] args) {
		int[] A = { 2, 3, 1, 1, 4 };
		int[] A1 = { 3, 2, 1, 0, 4 };
		int[] A2 = { 0 };
		JumpGame jg = new JumpGame();
		System.out.println(jg.canJump1(A));
		System.out.println(jg.canJump1(A1));
		System.out.println(jg.canJump1(A2));
		int[] A3 = { 1, 3, 2, 2, 1 };
		System.out.println(jg.jump(A3));
	}

}
