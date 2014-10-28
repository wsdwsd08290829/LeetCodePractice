package leet;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//TODO
public class CopyOfMaxPointsOnLine {
	public static final double epsilon = 1e-30;

	
	/**
	 * copy on line;
	 * @param points
	 * @return
	 */
	static class Line {
		Point p1;
		Point p2;

		public Line(Point p1, Point p2) {
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

		boolean isOnLine(Point p) {
			if (p.x == p1.x && p.y == p1.y) {
				return true;
			}
			if (p.x == p2.x && p.y == p2.y) {
				return true;
			}
			return (p2.y - p1.y) * (p2.x - p.x) - (p2.x - p1.x) * (p2.y - p.y) < epsilon;
		}
	}
	
	/**
	 * copy on line;
	 * @param points
	 * @return
	 */
	 public  int maxPoints(Point[] points) {
         
	        if (points==null||points.length==0){
	            return 0;
	        }  
	        
	        HashMap<Double, Integer> map=new HashMap<Double, Integer>();;
	        int max=1;
	        
	        for(int i=0; i<points.length; i++){
	            // shared point changed, map should be cleared and server the new point
	            map.clear();
	            
	            // maybe all points contained in the list are same points,and same points' k is 
	            // represented by Integer.MIN_VALUE
	            map.put((double)Integer.MIN_VALUE, 1);
	            
	            int dup=0;
	            
	            for(int j=i+1; j<points.length; j++){
	                
	               if (points[j].x==points[i].x&&points[j].y==points[i].y){
	                   dup++;
	                   continue;
	               }
	               
	               // look 0.0+(double)(points[j].y-points[i].y)/(double)(points[j].x-points[i].x)
	               // because (double)0/-1 is -0.0, so we should use 0.0+-0.0=0.0 to solve 0.0 !=-0.0
	               // problem
	               
	               // if the line through two points are parallel to y coordinator, then K(slop) is 
	               // Integer.MAX_VALUE
	               double key=points[j].x-points[i].x==0?Integer.MAX_VALUE:0.0+(double)(points[j].y-points[i].y)/(double)(points[j].x-points[i].x);
	              
	               if (map.containsKey(key)){
	            	   
	                   map.put(key, map.get(key)+1);
	               }
	               else{
	                  map.put(key, 2);
	               }
	           }
	           
	          for (int temp: map.values()){
	            
	              // duplicate may exist
	              if (temp+dup>max){
	                  max=temp+dup;
	              }
	          }
	           
	        }
	        
	        return max;
	    }
	public int maxPoints1(Point[] points) {
		  int ans = 0;
		Map<Line, Integer> map = new HashMap<Line, Integer>();
		
		for (Point p1 : points) {
			int max = 0;
			int same = 0;
			map.clear();
			for (Point p2 : points) {
				if (p1.x != p2.x || p1.y != p2.y) {
					Line l = new Line(p1, p2);
					Integer count = map.get(l);
					if (count != null) {
						count = count +1;
						map.put(l, count);
					} else {
						count = 1;
						map.put(l, count);
					}
					max = Math.max(max, count);
				}else{
					same++;
					System.out.println("same" + same);
				}
			}
			ans = Math.max(ans, max+ same);
		}
		return ans;
	}

	public static void main(String[] args) {
		Point p1 = new Point(1, 1);
		Point p2 = new Point(1, 1);
		Point p3 = new Point(1, 1);
		Point p4 = new Point(3, 4);
		Line l1 = new Line(p1, p2);
		Line l2 = new Line(p2, p3);
		System.out.println(l1.equals(l2));
		Set<Line> set = new HashSet<Line>();
		set.add(l1);
		set.add(l2);
		CopyOfMaxPointsOnLine mpol = new CopyOfMaxPointsOnLine();

	//	System.out.println("size: " + set.size());
		Point[] Points = { p1 };
		System.out.println(mpol.maxPoints(Points));
	//	System.out.println(0.3 == 0.3);

	}
}
