package leet;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class LargestRectangleInHistogram {
	/**
	 * O(n^2) -> worst(O(n^n) time limit exceed for 111111...: add set for index
	 * time limit exceed for 12345....:???
	 * 
	 * @param height
	 * @return
	 */
	public int largestRectangleArea(int[] height) {
		// set of index that do not need scan in the future, already checked
		Set<Integer> set = new HashSet<Integer>();
		int area = 0;
		// get area of bar i as highest bar
		for (int i = 0; i < height.length; i++) {
			if (set.contains(i)) {
				continue;
			}
			int leftBound = i;
			int rightBound = i;
			int count = 1; // i it self
			while (leftBound > 0 && height[leftBound - 1] >= height[i]) {
				if (height[leftBound - 1] == height[i]) {
					set.add(i);
				}
				leftBound--;
				count++;
			}
			while (rightBound < height.length - 1
					&& height[rightBound + 1] >= height[i]) {
				if (height[rightBound + 1] == height[i]) {
					set.add(i);
				}
				rightBound++;
				count++;
			}
			area = Math.max(area, height[i] * count);
			count = 0;
		}
		return area;
	}

	/**
	 * so... two resources: stack always save index of increasing height, keep
	 * pop when (new height < top of stack)(means meet right boundary) and
	 * calculate max(from peek of stack to current popped) along the way to left
	 * most that larger than current; see more:
	 * http://blog.csdn.net/havenoidea/article/details/11854723
	 * http://www.cnblogs
	 * .com/lichen782/p/leetcode_Largest_Rectangle_in_Histogram.html
	 * 
	 * @param args
	 */
	public int largestRectangleArea1(int[] height) {
		int max = 0;
		// save increasing index to Stack
		Stack<Integer> increasingIndexStack = new Stack<Integer>();
		// use extra O(n) space, if not,
		// need extra code to deal with remain stack after for loop
		int height1[] = Arrays.copyOf(height, height.length + 1); // last is 0
		for (int i = 0; i < height1.length; i++) {
			if (increasingIndexStack.isEmpty()
					|| height1[i] > height1[increasingIndexStack.peek()]) {
				increasingIndexStack.push(i);
			} else {
				while (!increasingIndexStack.isEmpty()
						&& height1[i] < height1[increasingIndexStack.peek()]) {
					int prevIndex = increasingIndexStack.pop();
					if (increasingIndexStack.isEmpty())
						// the current popped is min to all bars from [0, i)
						// bottom is always smallest
						// also because cannot peek value below
						max = Math.max(height1[prevIndex] * i, max);
					else {
						// !!!not i-prevIndex ,
						// increasingIndexStack.peek() means the left most bar
						// smaller than current bar,
						// between may have higher already removed
						max = Math.max(height1[prevIndex]
								* (i - 1 - increasingIndexStack.peek()), max);
					}
				}
				increasingIndexStack.push(i);
			}
		}
		return max;
	}

	public static void main(String[] args) {
		System.out.println(new LargestRectangleInHistogram()
				.largestRectangleArea1(new int[] { 4, 2, 0, 3, 2, 5 }));
	}
}
