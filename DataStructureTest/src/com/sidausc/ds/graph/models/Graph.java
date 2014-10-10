package com.sidausc.ds.graph.models;

import java.util.List;

public interface Graph {

	public abstract List<Vertex> getNodes();

	public abstract List<Edge> getEdges();

	/*
	 * helper methods
	 * 
	 */
	public abstract List<Vertex> getAdjacentNodes(Vertex v);

	public abstract Edge getEdge(Vertex src, Vertex dest);

	public abstract List<Edge> getAdjacentEdges(Vertex v);

}