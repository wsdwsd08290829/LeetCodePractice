package basic.list;

import java.util.ArrayList;
import java.util.LinkedList;

public class addTwoList {
	public static void main(String[] args) {
		ArrayList<Integer> l1 = new ArrayList<Integer>();
		ArrayList<Integer> l2 = new ArrayList<Integer>();
		l1.add(5);
		l1.add(3);
		l2.add(5);
		l2.add(6);
		l2.add(9);
		System.out.println(addList(l1, l2));
	}
	//list from low digit to high digit
	private static ArrayList<Integer> addList(ArrayList<Integer> l1, ArrayList<Integer> l2) {
		int carry = 0;
		int result = 0;
		ArrayList<Integer> resultList = new ArrayList<Integer>();
		for(int i=0; i<Math.min( l1.size(), l2.size() ); i++){
			result= (l1.get(i)+ l2.get(i) + carry)%10;
			carry = (l1.get(i)+l2.get(i)+carry)/10;
			resultList.add(result);
		}
		if(l1.size()<l2.size()){
			int i=l1.size();
			while(i<l2.size()){
				result= (l2.get(i) + carry)%10;
				carry = (l2.get(i)+carry)/10;
				resultList.add(result);
				i++;
			}
		}
		if(l1.size()>l2.size()){
			int i=l2.size();
			while(i<l1.size()){
				result= (l1.get(i) + carry)%10;
				carry = (l1.get(i)+carry)/10;
				resultList.add(result);
				i++;
			}
		}
		if(carry != 0){
			resultList.add(carry);
		}
		return resultList;
	}
}
