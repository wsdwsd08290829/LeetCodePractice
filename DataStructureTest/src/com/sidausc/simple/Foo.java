package com.sidausc.simple;

import java.util.HashMap;
import java.util.Map;

class Foo<T> {
	T var;
	public static int[] myminmax(int[] arr, int start, int end) throws Exception{
		if(start == end){return new int[]{arr[start],arr[start]};}
		if(start+1==end){
			System.out.print("@");
			return new int[]{Math.min(arr[start], arr[end]),Math.max(arr[start], arr[end])};
		}
		int mid = (start+end)/2;
		return new int[]{
				Math.min(myminmax(arr,start,mid)[0],myminmax(arr,mid+1,end)[0]),
				Math.max(myminmax(arr,start,mid)[1],myminmax(arr,mid+1,end)[1])
		};
	}
	class Dog{
		String name;
		Dog(String n ){
			name = n;
		}
		public int hashCode(){
			return name.length();
		}
		public boolean equals(Object o){
			return ((Dog)o).name == name; 
		}
	}
	public static int mymin(int[] arr, int start, int end) throws Exception{
		if(start == end){System.out.print("!");return arr[start];}
		if(start+1==end){
			System.out.print("!");
			return Math.min(arr[start], arr[end]);
		}
		int mid = (start+end)/2;
		return Math.min(mymin(arr,start,mid),mymin(arr,mid+1,end));
	}		

	public static void main(String[] args) throws Exception{
		Foo<String> f = new Foo<String>();
		int [] arr = {5,4,3,4,5,6,7,8,99,1};
		System.out.println(myminmax(arr, 0, arr.length-1)[1]);
		System.out.println(mymin(arr, 0, arr.length-1));
		String s= "abdc";
		String s1 = "";
		for(int i =0 ;i<s.length();i++){
			s1 = s.charAt(i) + s1;
		}
		int i = 0;
		s1="";
		while(i<s.length()){
			s1 = s.charAt(i++)+s1;
		}
		System.out.println(s1);
		System.out.println(s1.charAt(1));
		byte a = 'b';
		Foo foo = new Foo();
		Foo.Dog dog = foo.new Dog("a");
		
		Map map = new HashMap();
		map.put(dog, "a");
		dog.name="b";
		System.out.println(map.get(foo.new Dog("b")));
	}
}