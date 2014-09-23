package com.sidausc.simple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PersonPairDirectoryGeneric {
	public static <K extends Comparable,V,L,W> int 
    comparePairs(Pair<K,V> p, Pair<L,W> q) {
	return p.getKey().compareTo(q.getKey());  // p's key implements compareTo
	}
	
	public static void main(String[] args) {
	    Pair<String,Integer>[] a = new Pair[2]; // right, but gives a warning
	 //   Pair<String,Integer>[] b = new Pair<String,Integer>[10]; // wrong!!
	    a[0] = new Pair<String,Integer>();  // this is completely right
	    a[0].set("Dog",10);		// this and the next statement are right too
	    System.out.println("First  pair is "+a[0].getKey()+", "+a[0].getValue());
//	    Number[] nu = new Integer[5];
	    a[1] = new Pair<String,Integer>(); 
	    a[1].set("Cat",9);
	    int result = comparePairs(a[0], a[1]);
	    System.out.println("result: " + result);
	    Arrays.sort(a, new Comparator<Pair>(){

			@Override
			public int compare(Pair o1, Pair o2) {
				// TODO Auto-generated method stub
				return comparePairs(o1, o2);
			}
	    	
	    });
	    System.out.println("a0: " + a[0]);
	    System.out.println("a1: " + a[1]);
	    Map<String, Integer> map = new HashMap<String, Integer>();
	    map.put("aaa", 1);
	    map.put("ccc", 2);
	    map.put("bbb", 3);
	    Map<String, Integer> treeMap = new TreeMap<String, Integer>(
		    		new Comparator<String>(){
		    			public int compare(String a, String b){
		    				return b.compareTo(a);
		    			}
		    		}
	    		);
	    treeMap.putAll(map);
	    System.out.println(treeMap);
	  }
}
