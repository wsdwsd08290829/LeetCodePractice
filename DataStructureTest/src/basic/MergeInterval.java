package basic;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeInterval {
	/*static class Interval implements Comparable<Interval>{
		public Interval(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}
		int left;
		int right;
		@Override
		public int compareTo(Interval o) {
			
			 return this.left-o.left;		//throw new RuntimeException("should not here");
		}
		@Override
		public String toString() {
		return "[" + left + ", " + right + "]";
		}
	}*/
	static class Interval implements Comparator<Interval>{
		public Interval(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}
		int left;
		int right;
		
		@Override
		public String toString() {
		return "[" + left + ", " + right + "]";
		}
		@Override
		public int compare(Interval o1, Interval o2) {
			return o1.left-o2.left;
		}
	}
	
	public static void main(String[] args) {
		List<Interval> list = new LinkedList<Interval>();
		list.add(new Interval(8, 10));
		list.add(new Interval(15,18));
		
		list.add(new Interval(1, 4));
		list.add(new Interval(1, 3));
		list.add(new Interval(3, 7));
		list.add(new Interval(5, 8));
		list.add(new Interval(2, 6));
		//list.add(new Interval(1, 40));
		//if no comparator, list element should be Comparable
		
		List<Interval> result = merge(list);
		
		System.out.println(result);
		System.out.println(list);
		
		result = insert(list, new Interval(16,21));
		System.out.println("insert" + result);
		

	}
	
	private static List<Interval> insert(List<Interval> list, Interval interval) {
		list.add(interval);
		Collections.sort(list, new Interval(0,0));
		merge(list);
		return list;
	}

	private static List<Interval> indert(List<Interval> list, Interval interval) {
		// TODO Auto-generated method stub
		return null;
	}

	private static List<Interval> merge(List<Interval> list) {
		Collections.sort(list, new Interval(0, 0));
		System.out.println(list);
		for(int i=0;i<list.size()-1;i++){
			Interval first = list.get(i);
			Interval second = list.get(i+1);
			//merge logic, second is subset of first or
			//second cross first
			if(first.right>=second.left){
				//subset
				if(first.right>second.right){
					list.remove(second);
				}else{// cross
					second.left = first.left;
					list.remove(first);
				}
				i--;
			}
		}
		//another way;
		/*
		 * create List<interval> result
		 * loop through original list
		 * adding or merge to result
		 */
		return list;
	}
	
	
}
