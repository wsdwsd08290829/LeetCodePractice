import java.util.ArrayList;
import java.util.Arrays;


public class Subset {
	//recursion
	public static ArrayList<String> getSubset(String s){
		if(s==null)return null;
		if(s.length() ==0){
			ArrayList<String> subres = new ArrayList<String>();
			subres.add("");
			return subres;
		}
		char first  =  s.charAt(0);
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> temp = getSubset(s.substring(1));
		for(String str: temp){
			result.add(first+str);
		}
		result.addAll(temp);
		return result;
	}
	
	//iterative
	public static ArrayList<ArrayList<Integer>> getSubset2(int[] arr){
		Arrays.sort(arr);
		ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
		result.add(new ArrayList<Integer>());
		
		for(int i=0;i<arr.length;i++){
			ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
			int first =arr[i];
			for(ArrayList<Integer> sub :result){
				ArrayList<Integer> newNode = new ArrayList<Integer>();
				newNode.addAll(sub);
				newNode.add(first);
				temp.add(newNode);
				
			}
			//System.out.println("temp" + temp);
			result.addAll(temp);
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(getSubset("abc"));
		System.out.println(getSubset2(new int[]{1,2,3}));
	}
	
}
