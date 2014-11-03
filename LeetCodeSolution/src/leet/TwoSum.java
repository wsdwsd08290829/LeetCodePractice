package leet;

import java.util.HashMap;
import java.util.Map;
/**
 * find two position(index+1) as array that add to target
 * @author sidawang
 */
public class TwoSum {
	/************** method 1: brute force(O(n^2) *************/
	/************** method 2: HashMap O(n) *************/

	public static int[] twoSum(int[] numbers, int target) {
		// map diff to target => current index
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] result = { -1, -1 };
		for (int i = 0; i < numbers.length; i++) {
			if (!map.containsKey(numbers[i])) {
				map.put(target - numbers[i], i);
			} else {
				result[0] = map.get(numbers[i]) + 1;
				result[1] = i + 1;
				return result;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] num = { 3, 2, 4 };
		int[] result = twoSum(num, 6);
		System.out.println(result[0] + " " + result[1]);
	}
}
