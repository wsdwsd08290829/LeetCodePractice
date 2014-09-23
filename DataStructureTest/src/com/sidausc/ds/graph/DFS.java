package com.sidausc.ds.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DFS {
	/**
	 * ABCDEFGH
	 * 01234567
	 * @param graph
	 */
	public static void traverseDFS(ArrayList[] graph, String node, int[] visitedArr){ //start from a
		Map<String, Integer> hm = new HashMap<String, Integer>();
		for(int i=0; i<graph.length; i++){
			hm.put((String) graph[i].get(0), i);  //map A->0, B->1...
		}
		visitedArr[hm.get(node)] = 1;
		List list = graph[hm.get(node)];
		System.out.println(node);
		for(int i=1; i<list.size();i++){
			if(visitedArr[hm.get(list.get(i))] == 0){
				traverseDFS(graph, (String) list.get(i), visitedArr);
			}
		}
	}
	public static void main(String[] args) {
		ArrayList<String>[] al= new ArrayList[8];
		for(int i=0; i<al.length; i++){
			al[i] = new ArrayList<String>();
		}
		al[0].add("A");al[0].add("B"); al[0].add("D"); al[0].add("G");
		al[1].add("B");al[1].add("A"); al[1].add("E"); al[1].add("F");
		al[2].add("C");al[2].add("F"); al[2].add("H");
		al[3].add("D");al[3].add("A");al[3].add("F");
		al[4].add("E");al[4].add("B");al[4].add("G");
		al[5].add("F");al[5].add("B");al[5].add("C");al[5].add("D");
		al[6].add("G");al[6].add("A");al[6].add("E");
		al[7].add("H");al[7].add("C");
		
		int[] visitedArr = new int[8];
		traverseDFS(al, "A",visitedArr);
	}
}
