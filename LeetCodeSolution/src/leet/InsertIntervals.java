package leet;

import java.util.ArrayList;
import java.util.List;

/**
 * may assume that the intervals were initially sorted according to their start
 * time
 * 
 * @author sidawang
 * 
 */
public class InsertIntervals {
	// assume intervals already merged, no need merge
	/**
	 * check possibilities of new interval relative to current interval; 1. to
	 * left, 2. to right, 3. have interval(3 subcases)
	 * 
	 * @param intervals
	 * @param newInterval
	 * @return
	 */
	// to complex
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

		List<Interval> result = new ArrayList<Interval>();
		if (intervals == null || intervals.size() < 1) {
			result.add(newInterval);
			return result;
		}

		Interval test = new Interval(newInterval.start, newInterval.end);
		// !!!!!!!!for loop always go forward <-> while loop in method2
		for (int i = 0; i < intervals.size(); i++) {
			if (test.end < intervals.get(i).start) { // newInterval at left
				result.add(new Interval(test.start, test.end));
				test.start = intervals.get(i).start; // save current to be added
														// or merged in future
				test.end = intervals.get(i).end;
			} else if (intervals.get(i).end < test.start) { // new Interval at
															// right
				result.add(new Interval(intervals.get(i).start, intervals
						.get(i).end)); // add current interval(i)
			}
			// have interval(3 subcases)
			else { // else merge current to test, test will be added in future
				test.start = Math.min(test.start, intervals.get(i).start);
				test.end = Math.max(test.end, intervals.get(i).end);
			}
		}
		// for last( still consider all cases)
		if (test.end < intervals.get(intervals.size() - 1).start) {
			result.add(new Interval(intervals.get(intervals.size() - 1).start,
					intervals.get(intervals.size() - 1).end));
		} else {
			result.add(new Interval(test.start, test.end));
		}
		return result;
	}

	/********* method 2 ***********/
	// move till interval cross
	// http://blog.csdn.net/linhuanmars/article/details/22238433
	public List<Interval> insert1(List<Interval> intervals, Interval newInterval) {
		ArrayList<Interval> res = new ArrayList<Interval>();
		if (intervals.size() == 0) {
			res.add(newInterval);
			return res;
		}
		int i = 0;
		// while loop not go forward until satisfy condition
		while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
			res.add(intervals.get(i));
			i++;
		}
		// here implicitly considered newInterval is at beginning
		// fix left boundary
		if (i < intervals.size()) {
			newInterval.start = Math.min(newInterval.start,
					intervals.get(i).start);
		}
		res.add(newInterval); // !!!!!!not creating a new interval, use pointer,
								// could create new also
		// fix right boundary
		while (i < intervals.size()
				&& intervals.get(i).start <= newInterval.end) {
			newInterval.end = Math.max(newInterval.end, intervals.get(i).end);
			i++;
		}
		while (i < intervals.size()) {
			res.add(intervals.get(i));
			i++;
		}
		return res;
	}
	/**** bonus ****/
	/*
	 * 就是如果插入的时候发现冲突，那就返回失败， 不插入了。看起来好像比上面这道题还要简单，
	 * 但是要注意的是，如此我们就不需要进行线性扫描了，而是进行二分查找
	 */
}
