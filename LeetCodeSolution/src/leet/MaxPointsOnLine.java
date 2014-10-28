package leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MaxPointsOnLine {
	public static final double epsilon = 1e-10;

	static class MyPoint extends Point {
		int x;
		int y;

		MyPoint() {
			x = 0;
			y = 0;
		}

		MyPoint(int a, int b) {
			x = a;
			y = b;
		}

		@Override
		public int hashCode() {
			return (x) * 100 % 31 + (y) * 100 % 31;
		}

		@Override
		public boolean equals(Object obj) {
			return (((MyPoint) obj).x == x) && (((MyPoint) obj).y == y);
		}

		@Override
		public String toString() {
			return x + " , " + y;
		}
	}

	static class Line {
		MyPoint p1;
		MyPoint p2;

		public Line(MyPoint p1, MyPoint p2) {
			this.p1 = p1;
			this.p2 = p2;
		}

		@Override
		public int hashCode() {
			if (p1.x == p2.x)
				return Integer.MAX_VALUE;
			else
				return (int) ((p1.y - p2.y) / (double) (p1.x - p2.x) * 1000) % 31;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Line) {
				Line testLine = (Line) obj;
				return isOnLine(testLine.p1) && isOnLine(testLine.p2);
			}
			return false;
		}

		boolean isOnLine(MyPoint p) {
			if (p.x == p1.x && p.y == p1.y) {
				return true;
			}
			if (p.x == p2.x && p.y == p2.y) {
				return true;
			}
			return (p2.y - p1.y) * (p2.x - p.x) - (p2.x - p1.x) * (p2.y - p.y) < epsilon;
		}
	}
	
	// Map line to set of points on line, use equals in point to avoid duplicates added to Set
	public int maxMyPoints2(MyPoint[] MyPoints) {
		Map<Line, Set<MyPoint>> map = new HashMap<Line, Set<MyPoint>>();
		for (MyPoint p1 : MyPoints) {
			for (MyPoint p2 : MyPoints) {
				if (p1.x != p2.x || p1.y != p2.y) {
					Line l = new Line(p1, p2);
					if (map.containsKey(l)) {
						map.get(l).add(p1);
						map.get(l).add(p2);
					} else {
						Set<MyPoint> set = new HashSet<MyPoint>();
						set.add(p1);
						set.add(p2);
						map.put(l, set);
					}
				}
			}
		}
		System.out.println(map);
		int max = 0;
		for (Line l : map.keySet()) {
			Set<MyPoint> set = map.get(l);
			if (set.size() > max)
				max = set.size();
		}
		return max;
	}
	// Map line to list of points on line, loop list to avoid duplicate
	public int maxMyPoints1(MyPoint[] MyPoints) {
		Map<Line, List<MyPoint>> map = new HashMap<Line, List<MyPoint>>();
		for (MyPoint p1 : MyPoints) {
			for (MyPoint p2 : MyPoints) {
				if (p1.x != p2.x || p1.y != p2.y) {
					Line l = new Line(p1, p2);
					if (map.containsKey(l)) {
						List<MyPoint> list = map.get(l);
						boolean p1Exist = false;
						boolean p2Exist = false;
						for (MyPoint p : list) {
							if (p.x == p1.x && p.y == p1.y) {
								p1Exist = true;
							}
							if (p.x == p2.x && p.y == p2.y) {
								p2Exist = true;
							}
						}
						if (!p1Exist)
							list.add(p1);
						if (!p2Exist)
							list.add(p2);
					} else {
						List<MyPoint> list = new ArrayList<MyPoint>();
						list.add(p1);
						list.add(p2);
						map.put(l, list);
					}
				}
			}
		}
		System.out.println(map);
		int max = 0;
		for (Line l : map.keySet()) {
			List<MyPoint> list = map.get(l);
			if (list.size() > max)
				max = list.size();
		}
		return max;
	}

	public static void main(String[] args) {
		MyPoint p1 = new MyPoint(1, 1);
		MyPoint p2 = new MyPoint(2, 2);
		MyPoint p3 = new MyPoint(3, 3);
		MyPoint p4 = new MyPoint(3, 4);
		Line l1 = new Line(p1, p2);
		Line l2 = new Line(p2, p3);
		System.out.println(l1.equals(l2));
		Set<Line> set = new HashSet<Line>();
		set.add(l1);
		set.add(l2);
		MaxPointsOnLine mpol = new MaxPointsOnLine();

		System.out.println("size: " + set.size());
		MyPoint[] MyPoints = { p1, p2, p3, p4 };
		System.out.println(mpol.maxMyPoints1(MyPoints));

	}
}
