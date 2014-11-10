package leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}

public class MergeIntervals {
	/*********** method1 *********/
	/**
	 * create new list(lazy: add previous when meet next result interval)
	 */
	public List<Interval> merge(List<Interval> intervals) {
		if (intervals == null || intervals.size() <= 1)
			return intervals;
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		List<Interval> result = new ArrayList<Interval>();
		int end = intervals.get(0).end;
		int start = intervals.get(0).start;
		// lazy
		for (int i = 1; i < intervals.size(); i++) {
			if (intervals.get(i).start <= end) {
				end = Math.max(intervals.get(i).end, end);
			}
			// meet a new interval, add previous interval to result by create
			// new one
			else {
				result.add(new Interval(start, end));
				start = intervals.get(i).start;
				end = intervals.get(i).end;
			}
			// add last interval
			if (i == intervals.size() - 1) {
				result.add(new Interval(start, end));
			}
		}
		return result;
	}

	/*********** method2 *********/
	public List<Interval> merge1(List<Interval> intervals) {
		ArrayList<Interval> res = new ArrayList<Interval>();
		if (intervals == null || intervals.size() == 0)
			return intervals;
		Comparator<Interval> comp = new Comparator<Interval>() {
			@Override
			public int compare(Interval i1, Interval i2) {
				if (i1.start == i2.start)
					return i1.end - i2.end; // do not necessary
				return i1.start - i2.start;
			}
		};
		Collections.sort(intervals, comp);

		res.add(intervals.get(0));
		for (int i = 1; i < intervals.size(); i++) {
			if (res.get(res.size() - 1).end >= intervals.get(i).start) {
				res.get(res.size() - 1).end = Math.max(
						res.get(res.size() - 1).end, intervals.get(i).end);
			} else {
				res.add(intervals.get(i));
			}
		}
		return res;
	}

	/********* method3 ************/
	/**
	 * in place 2* O(n)
	 * 
	 * @param intervals
	 * @return
	 */
	public List<Interval> merge2(List<Interval> intervals) {
		if (intervals == null || intervals.size() <= 1)
			return intervals;
		Collections.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
		});
		int end = intervals.get(0).end;
		int start = intervals.get(0).start;
		for (int i = 1; i < intervals.size(); i++) {
			if (intervals.get(i).start <= end) {
				// the last one is the one to be preserved,
				// all previous will be removed in next loop
				// by comparing start(remove when ==)
				intervals.get(i).start = start;
				end = Math.max(end, intervals.get(i).end);
				intervals.get(i).end = end;
			} else {
				start = intervals.get(i).start;
				end = intervals.get(i).end;
			}
		}
		int index = 0;
		while (index < intervals.size()) {
			if (index + 1 < intervals.size()
					&& intervals.get(index).start == intervals.get(index + 1).start) {
				intervals.remove(index);
			} else {
				index++;
			}
		}
		return intervals;
	}
}
