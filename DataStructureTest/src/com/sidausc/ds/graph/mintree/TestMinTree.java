package com.sidausc.ds.graph.mintree;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.sidausc.ds.graph.models.Edge;
import com.sidausc.ds.graph.models.UndirectedGraph;
import com.sidausc.ds.graph.models.Vertex;

public class TestMinTree {
	List<Edge> edges;
	List<Vertex> nodes;
	public UndirectedGraph initGraph(){
		edges = new LinkedList<Edge>();
		nodes = new LinkedList<Vertex>();
		for(int i=0;i<8;i++){
			 Vertex location = new Vertex( String.valueOf((Integer)i), String.valueOf((Integer)i) );
		      nodes.add(location);
		}	
		//use directed graph as undirected graph, cannot add reverse edge like 4->5 and 5->4 at same time
		addLane("0", 4, 5, 0.35);
	    addLane("1", 4, 7, 0.37);
	    addLane("2", 5, 7, 0.28);
	    addLane("3", 0, 7, 0.16);
	    addLane("4", 1, 5, 0.32);
	    addLane("5", 0, 4, 0.38);
	    addLane("6", 2, 3, 0.17);
	    addLane("7", 1, 7, 0.19);
	    addLane("8", 0, 2, 0.26);
	    addLane("9", 1, 2, 0.36);
	    addLane("10", 1, 3, 0.29);
	    addLane("11", 2, 7, 0.34);
	    addLane("12", 6, 2, 0.40);
	    addLane("13", 3, 6, 0.52);
	    addLane("14", 6, 0, 0.58);
	    addLane("15", 6, 4, 0.93);
	    //make it undirected
	/*	addLane("0r", 5, 4, 0.35);
	    addLane("1r", 7, 4, 0.37);
	    addLane("2r", 7, 5, 0.28);
	    addLane("3r", 7, 0, 0.16);
	    addLane("4r", 5, 1, 0.32);
	    addLane("5r", 4, 0, 0.38);
	    addLane("6r", 3, 2, 0.17);
	    addLane("7r", 7, 1, 0.19);
	    addLane("8r", 2, 0, 0.26);
	    addLane("9r", 2, 1, 0.36);
	    addLane("10r", 3, 1, 0.29);
	    addLane("11r", 7, 2, 0.34);
	    addLane("12r", 2, 6, 0.40);
	    addLane("13r", 6, 3, 0.52);
	    addLane("14r", 0, 6, 0.58);
	    addLane("15r", 4, 6, 0.93);*/
	    UndirectedGraph graph = new UndirectedGraph( nodes, edges);
		return graph;
	}
	private void addLane(String laneId, int sourceLocNo, int destLocNo,
		      double duration) {
		    Edge lane = new Edge(laneId, nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
		    edges.add(lane);
	}
	@Test	
	public  void testPrimAlg(){
		//TestMinTree tmt = new TestMinTree();
		UndirectedGraph graph = initGraph();//tmt.initGraph();
		PrimsAlg pa = new PrimsAlg(graph);
		pa.runPrims();
		System.out.println("________");
		pa.getMinTreeEdges();
	}
		
//	public static void main(String[] args){ //testKruskalAlg
	@Test
	public void testKruskalAlg(){
		TestMinTree tmt = new TestMinTree();
		UndirectedGraph graph = tmt.initGraph();
		KruskalAlg ku = new KruskalAlg(graph);
		ku.runKruskal();
		System.out.println("________");
		ku.getMinTreeEdges();
	}	
	
	@Test	
	public  void testPrimsQuickAlg(){
		//TestMinTree tmt = new TestMinTree();
		UndirectedGraph graph = initGraph();//tmt.initGraph();
		PrimsQuickAlg pa = new PrimsQuickAlg(graph);
		pa.runPrims();
		System.out.println("________");
		pa.getMinTreeEdges();
	}
	
	/*public static void main(String[] args){ //testKruskalAlg
		TestMinTree tmt = new TestMinTree();
		UndirectedGraph graph = tmt.initGraph();//tmt.initGraph();
		PrimsQuickAlg pa = new PrimsQuickAlg(graph);
		pa.runPrims();
		System.out.println("________");
		pa.getMinTreeEdges();
	}*/
}
