package com.sidausc.ds.graph.models;

import java.util.LinkedList;
import java.util.List;

public class Graph {
	private List<Vertex> nodes;  
	private List<Edge> edges;
	
	public Graph(List<Vertex> nodes, List<Edge> edges) {
		super();
		this.nodes = nodes;
		this.edges = edges;
	}
	public List<Vertex> getNodes() {
		return nodes;
	}
	public List<Edge> getEdges() {
		return edges;
	} 
	/*
	 * helper methods
	 * 
	 */
	public List<Vertex> getAdjacentNodes(Vertex v){
		List<Vertex> adjacentNodes = new LinkedList<Vertex>();
		for(Edge edge : edges){
			if(edge.getSource().equals(v)){
				adjacentNodes.add(edge.getDestination());
			}
		}
		return adjacentNodes;
	}
	public Edge getEdge(Vertex src, Vertex dest){
		for(Edge e : edges){ 
			if(src.equals(e.getSource()) && dest.equals(e.getDestination())){
				return e;
			}
		}
		return null;
	}
}
