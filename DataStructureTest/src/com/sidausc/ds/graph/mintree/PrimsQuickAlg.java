package com.sidausc.ds.graph.mintree;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sidausc.ds.graph.models.Edge;
import com.sidausc.ds.graph.models.UndirectedGraph;
import com.sidausc.ds.graph.models.Vertex;
import com.sidausc.ds.priorityqueue.IndexedPriorityQueue;

public class PrimsQuickAlg {
	private UndirectedGraph graph;
	private Set<Vertex> marked;
	private IndexedPriorityQueue<Double, VertexEdgeGroup> pq;
	private Set<Edge> result;
	private Map<Vertex, Double> minWeight;
	static class VertexEdgeGroup{
		Vertex vertex;
		Edge edge;
		public VertexEdgeGroup(Vertex vertex, Edge edge) {
			super();
			this.vertex = vertex;
			this.edge = edge;
		}
		
	}
	public PrimsQuickAlg(UndirectedGraph g){
		graph = g;
		minWeight=new HashMap<Vertex, Double>( graph.getNodes().size());
		marked = new HashSet<Vertex>();
		result = new HashSet<Edge>();
		pq = new IndexedPriorityQueue<Double, VertexEdgeGroup>(graph.getEdges().size());
		for(Vertex v : graph.getNodes()){
			minWeight.put(v, Double.MAX_VALUE);
		}
	}
	public void runPrims(){
		Vertex v = graph.getNodes().get(0);//first
		minWeight.put(v, (double) 0);
		marked.add(v);
		scan(v);
		//pq.insert(new Double(0.0),new VertexEdgeGroup(v, null));
		while(marked.size()<graph.getNodes().size()){
			VertexEdgeGroup veg = pq.remove().getValue();
			v = veg.vertex; 
			result.add(veg.edge);
			marked.add(v);
			
			scan(v);
		}
	}
	//update minWeight, update PriorityQueue
	private void scan(Vertex vertex) {
		List<Edge> neighbourEdges = graph.getAdjacentEdges(vertex);
		
		for(Edge neighbour :neighbourEdges){
			Vertex theOther = neighbour.getTheOther(vertex);
			Double tryWeight = neighbour.getDweight();
			if(tryWeight < minWeight.get(theOther)){
				System.out.println(neighbour);
				if(pq.contains(minWeight.get(theOther))){
					pq.changeKey(minWeight.get(theOther), tryWeight, new VertexEdgeGroup(theOther, neighbour));
				}else{
					pq.insert(tryWeight, new VertexEdgeGroup(theOther, neighbour));
				}
				minWeight.put(theOther, tryWeight);
				
			}
		}
	}
	public void getMinTreeEdges(){
		System.out.println("!!!!!!!!!!Prim results "+ result);
	}
}
