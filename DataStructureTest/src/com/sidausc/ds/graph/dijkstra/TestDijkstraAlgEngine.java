package com.sidausc.ds.graph.dijkstra;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.sidausc.ds.graph.models.Edge;
import com.sidausc.ds.graph.models.Graph;
import com.sidausc.ds.graph.models.Vertex;

public class TestDijkstraAlgEngine {
	List<Edge> edges;
	List<Vertex> nodes;
	public Graph initGraph(){
		edges = new LinkedList<Edge>();
		nodes = new LinkedList<Vertex>();
		for(int i=0;i<11;i++){
			 Vertex location = new Vertex("Node_" + i, "Node_" + i);
		      nodes.add(location);
		}
		addLane("Edge_0", 0, 1, 85);
	    addLane("Edge_1", 0, 2, 217);
	    addLane("Edge_2", 0, 4, 173);
	    addLane("Edge_3", 2, 6, 186);
	    addLane("Edge_4", 2, 7, 103);
	    addLane("Edge_5", 3, 7, 183);
	    addLane("Edge_6", 5, 8, 250);
	    addLane("Edge_7", 8, 9, 84);
	    addLane("Edge_8", 7, 9, 167);
	    addLane("Edge_9", 4, 9, 502);
	    addLane("Edge_10", 9, 10, 40);
	    addLane("Edge_11", 1, 10, 600);
	    Graph graph = new Graph( nodes, edges);
		return graph;
	}
	private void addLane(String laneId, int sourceLocNo, int destLocNo,
		      int duration) {
		    Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration);
		    edges.add(lane);
	}
	@Test
	public void testRunner(){
		Graph graph = initGraph();
		DijkstraAlgEngine dae = new DijkstraAlgEngine(graph);
		dae.runDijkstra(graph.getNodes().get(0));
		System.out.println(dae.getPathToNode(nodes.get(10)));
	}
}
