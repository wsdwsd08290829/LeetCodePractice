package com.sidausc.ds.graph.models;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UnionFindForGraph<K> implements UnionFind{
	
	Map<K, K> componentsMap;
	int count;
	public UnionFindForGraph(int N) {
		count = N;
		componentsMap =  new HashMap<K, K>(N);//null if index is root
		for(K k: componentsMap.keySet()){
			componentsMap.put(k, k);   //init vertex parent to itself
		}
	}
	public UnionFindForGraph(int N, Graph g) {
		count = N;
		componentsMap =  new HashMap<K, K>(N);//null if index is root
		for(Vertex k : g.getNodes()){
			componentsMap.put((K)k, (K)k);
		}
		for(K k: componentsMap.keySet()){
			componentsMap.put(k, k);   //init vertex parent to itself
		}
	}
	@Override
	public Object find(Object k) {
		while(componentsMap.get((K)k)!= (K)k){
			//componentsMap.put((K) k, componentsMap.get(componentsMap.get(k))); //compression
			k = componentsMap.get(k);
		}
		return k;
	}
	@Override
	public Object count() {
		// TODO Auto-generated method stub
		return count;
	}
	@Override
	public boolean connected(Object p, Object q) {
		if( find((K)p).equals(find((K)q)) ){
			return true;
		}
		return false;
	}
	@Override
	public void union(Object p, Object q) {
		K pk = (K) find(p);
		K qk = (K) find(q);
		if(pk.equals(qk))return;
		else {
			componentsMap.put(qk, pk);
			count--;
		}
	}
	  public static void main(String[] args) throws FileNotFoundException {
	    	Scanner s = new Scanner(new File(args[0])); //"E:\\java\PrincetonLibrary\algs4-data\tinyUF.txt"
	        int N = s.nextInt();
	        UnionFindForGraph uf = new UnionFindForGraph(N);
	        System.out.println(uf.componentsMap);
	        while (s.hasNext()) {
	            int p = s.nextInt();
	            int q = s.nextInt();
	            if (uf.connected(p, q)) continue;
	            uf.union(p, q);
	            System.out.println(p + " " + q);
	        }
	        System.out.println(" =================== ");
	        for(int i= 0; i< 10;i++){
	        	System.out.println(uf.componentsMap);
	        }
	        System.out.println(uf.count() + " components");
	    }
}
