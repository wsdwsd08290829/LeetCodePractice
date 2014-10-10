package com.sidausc.ds.graph.models;

public class Edge {
	private String id;
	private Vertex source;
	private Vertex destination;
	private int weight;
	private double dweight;
	
	public Edge(String id, Vertex source, Vertex destination, int weight) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.weight = weight;
	}
	public Edge(String id, Vertex source, Vertex destination, double dweight) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.dweight = dweight;
	}
	public String getId() {
		return id;
	}
	public Vertex getSource() {
		return source;
	}
	public Vertex getDestination() {
		return destination;
	}
	public int getWeight() {
		return weight;
	}
	public double getDweight() {
		return dweight;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[" + source + ", " + destination + "]";
	}
	/**
	 * for undirected tree
	 */
	public Vertex getTheOther(Vertex v){
		return  v==source ? destination: source;
	}
}
