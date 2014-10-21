package basic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


class Interval {
      int start;
      int end;
      Interval() { start = 0; end = 0; }
      Interval(int s, int e) { start = s; end = e; }
      @Override
    public String toString() {
    	  return "[" + start + ", " + end + "]";
    }
  }
 
public class InsertInterval {
    public static ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
 
        ArrayList<Interval> result = new ArrayList<Interval>();
 
        for(Interval interval: intervals){
            if(interval.end < newInterval.start){
                result.add(interval);
            }else if(interval.start > newInterval.end){
                result.add(newInterval);
                newInterval = interval;        
            }else if(interval.end >= newInterval.start || interval.start <= newInterval.end){
                newInterval = new Interval(Math.min(interval.start, newInterval.start), Math.max(newInterval.end, interval.end));
            }
        }
 
        result.add(newInterval); 
 
        return result;
    }
    @Test
    public void testInsert(){
    	ArrayList<Interval> list = new ArrayList<Interval>();
		
		
		list.add(new Interval(1, 3));
		list.add(new Interval(1, 4));
		list.add(new Interval(3, 7));
		list.add(new Interval(5, 8));
		list.add(new Interval(15,18));
		//list.add(new Interval(1, 40));
		//if no comparator, list element should be Comparable
		
		List<Interval> result = new ArrayList<Interval>();
		
		
		
		result = insert(list, new Interval(16,21));
		System.out.println("insert" + result);
    }
}
