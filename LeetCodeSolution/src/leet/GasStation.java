package leet;

public class GasStation {
	/**
	 * BruteForce check all start point, until success O(n^2)
	 * 
	 * @param gas
	 * @param cost
	 * @return
	 */
	public static int canCompleteCircuit(int[] gas, int[] cost) {
		int len = gas.length;
		boolean canNotComplete = false;
		// check all start point
		for (int start = 0; start < len; start++) {
			canNotComplete = false;
			int currGas = gas[start];
			for (int i = start; i < start + len; i++) {
				int index = i % len;
				if (currGas < cost[index]) {
					canNotComplete = true;
					break;
				}
				currGas -= cost[index];
				currGas += gas[(index + 1) % len];
			}
			if (!canNotComplete)
				return start;
		}
		return -1;
	}

	/**
	 * greedy: if i-> j == true, j-> j+1 == false; any start point between i and
	 * j is false
	 * 
	 * @param gas
	 * @param cost
	 * @return
	 */
	public static int canCompleteCircuit1(int[] gas, int[] cost) {
		if(gas==null || cost==null || gas.length == 0|| cost.length ==0) return -1;
		int start = 0;  //index
		int total = 0;  //gas 
		int cumuGas = 0;
		for (int i = 0; i < gas.length; i++) {
			cumuGas += gas[i] -cost[i];
			if(cumuGas < 0){ //cannot make from i to i+1
				start = i+1;
				cumuGas = 0;
			}
			total += gas[i] - cost[i];
		}
		if(total < 0 || start == gas.length){
			return -1;
		}else{
			return start;
		}
	}

	public static void main(String[] args) {
		int[] gas = {3, 1, 2, 5, 4 };
		int[] cost = { 4, 1, 1, 2, 3 };
		System.out.println(canCompleteCircuit(gas, cost));
	}
}
