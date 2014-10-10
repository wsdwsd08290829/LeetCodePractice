package com.sidausc.ds.graph.mintree;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import com.sidausc.ds.graph.models.Edge;
import com.sidausc.ds.graph.models.UndirectedGraph;
import com.sidausc.ds.graph.models.UnionFind;
import com.sidausc.ds.graph.models.UnionFindForGraph;
import com.sidausc.ds.graph.models.Vertex;

public class KruskalAlg {
	private UndirectedGraph graph;
	private Set<Vertex> marked;
	private PriorityQueue<Edge> pq;
	private Set<Edge> result;
	private UnionFind<Vertex> uf;
	public KruskalAlg(UndirectedGraph g){
		marked = new HashSet<Vertex>();
		result = new HashSet<Edge>();
		graph = g;
		//init vertex in UF
		uf = new UnionFindForGraph<Vertex>(graph.getNodes().size(), graph); 
		pq = new PriorityQueue<Edge>(graph.getEdges().size(), new Comparator<Edge>(){
			public int compare(Edge o1, Edge o2) {
				if(o1.getDweight()-o2.getDweight()>0)return 1;
				else if(o1.getDweight()-o2.getDweight()==0)return 0;
				else return -1;
			}
		});
		
		//add all edge to priorityqueue
		for(Edge e : graph.getEdges()){
			pq.add(e);
		}
	}
	public void runKruskal(){
		while(marked.size()<graph.getNodes().size()){
			System.out.println("current marked " + marked);
			System.out.println("current result " + result);
			Edge minEdge = pq.remove();
			Vertex v1 = minEdge.getSource();
			Vertex v2 = minEdge.getTheOther(v1);
			System.out.println(v1 + " " + v2);
			System.out.println(uf.connected(v1, v2));
			if(!uf.connected(v1, v2)){
				uf.union(v1, v2);
				result.add(minEdge);
				marked.add(v1);
				marked.add(v2);
			}
			else continue;
		}
	}
	public void getMinTreeEdges(){
		System.out.println("!!!!!!!!!!!!KU results "+ result);
	}
}
