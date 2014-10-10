package com.sidausc.ds.graph.models;

import java.util.LinkedList;
import java.util.List;

public class DirectedGraph implements Graph {
	protected List<Vertex> nodes;  
	protected List<Edge> edges;
	
	public DirectedGraph(List<Vertex> nodes, List<Edge> edges) {
		super();
		this.nodes = nodes;
		this.edges = edges;
	}
	/* (non-Javadoc)
	 * @see com.sidausc.ds.graph.models.Graph#getNodes()
	 */
	@Override
	public List<Vertex> getNodes() {
		return nodes;
	}
	/* (non-Javadoc)
	 * @see com.sidausc.ds.graph.models.Graph#getEdges()
	 */
	@Override
	public List<Edge> getEdges() {
		return edges;
	} 
	/*
	 * helper methods
	 * 
	 */
	/* (non-Javadoc)
	 * @see com.sidausc.ds.graph.models.Graph#getAdjacentNodes(com.sidausc.ds.graph.models.Vertex)
	 */
	@Override
	public List<Vertex> getAdjacentNodes(Vertex v){
		List<Vertex> adjacentNodes = new LinkedList<Vertex>();
		for(Edge edge : edges){
			if(edge.getSource().equals(v)){
				adjacentNodes.add(edge.getDestination());
			}
		}
		return adjacentNodes;
	}
	/* (non-Javadoc)
	 * @see com.sidausc.ds.graph.models.Graph#getEdge(com.sidausc.ds.graph.models.Vertex, com.sidausc.ds.graph.models.Vertex)
	 */
	@Override
	public Edge getEdge(Vertex src, Vertex dest){
		for(Edge e : edges){ 
			if(src.equals(e.getSource()) && dest.equals(e.getDestination())){
				return e;
			}
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see com.sidausc.ds.graph.models.Graph#getAdjacentEdges(com.sidausc.ds.graph.models.Vertex)
	 */
	@Override
	public List<Edge> getAdjacentEdges(Vertex v){
		List<Edge> adjacentEdges = new LinkedList<Edge>();
		for(Edge edge : edges){
			if(edge.getSource().equals(v)){
				adjacentEdges.add(edge);
			}
		}
		return adjacentEdges;
	}
}
