package com.sidausc.ds.graph.mintree;

import java.util.*;

import com.sidausc.ds.graph.models.Edge;
import com.sidausc.ds.graph.models.UndirectedGraph;
import com.sidausc.ds.graph.models.Vertex;
/**
 * mark first node; and scan it
 * while(not all nodes are marked)    |V|
 * 	remove min edge from pq;   		 log(V)
 * 	scan the other end of edge which is:    |E|
 * 			for all neighbour edges: add to priority queue if the other node is not marked 
 * 			and edge not already in priority queue, remove unqualified edges |logV|
 * mark that node
 * 
 * Total O((V+E)*logV) 
 * @author sidawang
 *
 */
public class PrimsAlg {
	private UndirectedGraph graph;
	private Set<Vertex> marked;
	private PriorityQueue<Edge> pq;
	private Set<Edge> result;
	public PrimsAlg(UndirectedGraph g){
		graph = g;
	}
	public void runPrims(){
		marked = new HashSet<Vertex>();
		result = new HashSet<Edge>();
		pq = new PriorityQueue<Edge>(graph.getEdges().size(), new Comparator<Edge>(){
			public int compare(Edge o1, Edge o2) {
				if(o1.getDweight()-o2.getDweight()>0)return 1;
				else if(o1.getDweight()-o2.getDweight()==0)return 0;
				else return -1;
			}
			
		});
		
	//	for(int i=0;i<graph.getNodes().size();i++){
			runPrimsOnNode(graph.getNodes().get(0));
	//	}
	}
	private void runPrimsOnNode(Vertex v) {
		marked.add(v);  //mark first node
		scan(v);
	
		while(marked.size()<graph.getNodes().size()){
			Edge edge = pq.remove();
			
		//	if(!checkAddedEdge(edge))continue;
			result.add(edge);
			System.out.println("min edge added "+ edge);
			System.out.println("current pq "+ pq);
			System.out.println("current marked " + marked );
			if(!marked.contains(edge.getDestination())){
				scan(edge.getDestination());
			}
			else if(!marked.contains(edge.getSource())){
				scan(edge.getSource());
			}
			System.out.println("-------------");
			markNodesInEdge(edge);
		}
	
	}
	private boolean checkAddedEdge(Edge edge) {
		if( ( marked.contains(edge.getSource()) && !marked.contains(edge.getDestination()) )
			|| ( !marked.contains(edge.getSource()) && marked.contains(edge.getDestination()) )  ){
			return true;
		}
		else return false;
	}
	private void markNodesInEdge(Edge edge) {
		if(marked.contains(edge.getSource()))marked.add(edge.getDestination());
		else marked.add(edge.getSource());

	}
	private void scan(Vertex v) {
		List<Edge> neighbours = graph.getAdjacentEdges(v);
		System.out.println("neighbour edges: "+neighbours);
	
		for(Edge e : neighbours){
			if(!marked.contains(e.getTheOther(v))){
				if(!pq.contains(e)){
					pq.add(e);
				}
			}else{
				pq.remove(e);
			}
		}
	}
	public void getMinTreeEdges(){
		System.out.println("!!!!!!!!!!Prim results "+ result);
	}
}
