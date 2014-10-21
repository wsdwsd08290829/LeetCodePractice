package com.sidausc.ds.graph.models;

import java.util.LinkedList;
import java.util.List;

public class UndirectedGraph extends DirectedGraph {

	public UndirectedGraph(List<Vertex> nodes, List<Edge> edges) {
		super(nodes, edges);
		// TODO Auto-generated constructor stub
	}
	public List<Vertex> getAdjacentNodes(Vertex v){
		List<Vertex> adjacentNodes = new LinkedList<Vertex>();
		for(Edge edge : edges){
			if(edge.getSource().equals(v) || edge.getDestination().equals(v)){
				adjacentNodes.add(edge.getTheOther(v));
			}
		}
		return adjacentNodes;
	}
	public Edge getEdge(Vertex v1, Vertex v2){
		for(Edge e : edges){ 
			if(v1.equals(e.getSource()) && v2.equals(e.getDestination())){
				return e;
			}
			if(v2.equals(e.getSource()) && v1.equals(e.getDestination())){
				return e;
			}
		}
		return null;
	}
	public List<Edge> getAdjacentEdges(Vertex v){
		List<Edge> adjacentEdges = new LinkedList<Edge>();
		for(Edge edge : edges){
			if(edge.getSource().equals(v)){
				adjacentEdges.add(edge);
			}
			if(edge.getDestination().equals(v)){
				adjacentEdges.add(edge);
			}
		}
		return adjacentEdges;
	}
}
