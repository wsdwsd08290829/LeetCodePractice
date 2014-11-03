package leet;

/**
 * Find two lines, which together with x-axis forms a container, such that the
 * container contains the most water.O(n)
 * 
 * @author sidawang
 * 
 */
public class ContainerWithMostWater {
	/*************** method1 ************/
	// lazy
	public int maxArea(int[] height) {
		int left = 0; // left index
		int right = height.length - 1; // right index
		int maxArea = 0;
		while (left < right) {
			maxArea = Math.max(maxArea,
					(right - left) * Math.min(height[left], height[right]));
			if (height[left] < height[right])
				left++;
			else
				right--;
		}
		return maxArea;
	}

	/*************** method2 ************/
	// eager (comparison faster)
	public int maxArea1(int[] height) {
		int left = 0; // left index
		int right = height.length - 1; // right index
		int maxArea = 0;
		while (left < right) {
			maxArea = Math.max(maxArea,
					(right - left) * Math.min(height[left], height[right]));
			if (height[left] < height[right]) {
				left++;
				while (left < right && height[left - 1] > height[left]) {
					left++;
				}
			} else {
				right--;
				while (left < right && height[right] <= height[right + 1]) {
					right--;
				}
			}
		}
		return maxArea;
	}
}
