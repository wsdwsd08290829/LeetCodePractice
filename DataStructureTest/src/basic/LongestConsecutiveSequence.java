package basic;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LongestConsecutiveSequence {
	public static  List<Integer> longestConsecutiveSequence(int[] arr){
		if(arr==null|| arr.length==0)return null;
		List<Integer> tempList = new LinkedList<Integer>();
		List<Integer> maxList = new LinkedList<Integer>();
		Set<Integer> set = new HashSet<Integer>();
		int count = 1;
		int maxSize = Integer.MIN_VALUE;
		int minVal = Integer.MAX_VALUE;
		for(int i:arr){set.add(i);}
		for(int i:arr){
			int left=i-1, right=i+1;
			tempList.add(i);
			
			while(set.contains(left)){
				tempList.add(left);
				set.remove(left);
				left--;
				count++;
			}
			
			while(set.contains(right)){
				tempList.add(right);
				set.remove(right);
				right++;
				count++;
			}
			//faster
			if(count>maxSize){
				maxSize=count;
				minVal=left+1;
			}
			count=1;
			//copy list may take time & space;
			if(tempList.size()>maxList.size()){
				maxList.clear();
				maxList.addAll(tempList);
			}	
			tempList.clear();
		}
		System.out.println("minVal:"+minVal + " maxSize:"+ maxSize);
		//or loop to find min, with size, you know the sequence
		return maxList;
		
	}
	public static void main(String[] args) {
		int[] arr ={100, 4, 200,  3, 2, 5, 101, 102, 103, 104};
		List<Integer> result = longestConsecutiveSequence(arr);
		System.out.println(result);
	}
}
 