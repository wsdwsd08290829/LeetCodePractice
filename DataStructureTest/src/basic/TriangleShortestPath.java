package basic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import com.sidausc.ds.graph.dijkstra.DijkstraAlgEngine;
import com.sidausc.ds.graph.models.DirectedGraph;
import com.sidausc.ds.graph.models.Edge;
import com.sidausc.ds.graph.models.Vertex;

public class TriangleShortestPath {
	ArrayList<ArrayList<Integer>> al;
	int minWeight = Integer.MAX_VALUE;
	List<Vertex> minPath = new ArrayList<Vertex>();

	public TriangleShortestPath(ArrayList<ArrayList<Integer>> al) {
		this.al = al;
	}

	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> level1 = new ArrayList<Integer>();
		ArrayList<Integer> level2 = new ArrayList<Integer>();
		ArrayList<Integer> level3 = new ArrayList<Integer>();
		ArrayList<Integer> level4 = new ArrayList<Integer>();
		level1.add(2);
		level2.add(3);
		level2.add(4);
		level3.add(6);
		level3.add(5);
		level3.add(7);
		level4.add(4);
		level4.add(1);
		level4.add(8);
		level4.add(3);
		al.add(level1);
		al.add(level2);
		al.add(level3);
		al.add(level4);
		TriangleShortestPath tsp = new TriangleShortestPath(al);

		tsp.runTriangleShortestPathEngine();
		System.out.println("minWeight: " + tsp.minWeight);
		System.out.println("minPath(by index): " + tsp.minPath);
		System.out.println("--------------------");
		tsp.findMin();
		System.out.println("minWeight: " + tsp.minWeight);
		System.out.println("minPath(by index): " + tsp.path);	
	}

	private int getNodeIndex(int mylevel, int mypoint) {
		int[] prevCount = new int[al.size()];
		// init count //0,1,3,6,10...
		prevCount[0] = 0;
		for (int level = 1; level < prevCount.length; level++) {
			prevCount[level] = prevCount[level - 1] + level;
		}
		// for(int i: prevCount){System.out.println(i+" ");}
		return prevCount[mylevel] + mypoint;

	}

	private void runTriangleShortestPathEngine() {
		int index = 0; // node alias
		List<Vertex> nodes = new ArrayList<Vertex>();
		List<Edge> edges = new ArrayList<Edge>();

		// construct all vertex
		for (int level = 0; level < al.size(); level++) {
			ArrayList<Integer> row = al.get(level);
			for (int point = 0; point < row.size(); point++) {
				Vertex v = new Vertex(String.valueOf(index));
				nodes.add(v);
				index++;
			}

		}
		// System.out.println(nodes);

		// top to secondToLast row: create edge
		index = 0;
		for (int level = 0; level < al.size() - 1; level++) {
			ArrayList<Integer> row = al.get(level);
			for (int point = 0; point < row.size(); point++) {
				int indexParent = getNodeIndex(level, point);
				int indexLeft = getNodeIndex(level + 1, point);
				int indexRight = indexLeft + 1;

				Vertex parent = nodes.get(indexParent);
				Vertex left = nodes.get(indexLeft);
				Vertex right = nodes.get(indexRight);
				Edge e1 = new Edge(String.valueOf(index++), parent, left, al
						.get(level + 1).get(point));
				Edge e2 = new Edge(String.valueOf(index++), parent, right, al
						.get(level + 1).get(point + 1));
				edges.add(e1);
				edges.add(e2);
			}
		}
		// System.out.println(edges);

		// get shortest path from top node
		DirectedGraph graph = new DirectedGraph(nodes, edges);
		DijkstraAlgEngine dijkstra = new DijkstraAlgEngine(graph);
		dijkstra.runDijkstra(nodes.get(0));

		// compare shortest path to each bottom
		int topWeight = al.get(0).get(0);
		/*
		 * int minWeight = Integer.MAX_VALUE; List<Vertex> minPath=new
		 * ArrayList<Vertex>();
		 */
		for (int lastRow = 0; lastRow < al.get(al.size() - 1).size(); lastRow++) {
			int level = al.size() - 1;
			// System.out.println( prevCount[level] + lastRow);
			/*
			 * index of nodes 0 12 345 6789
			 */
			System.out.println(dijkstra.getPathToNode(nodes.get(getNodeIndex(
					level, lastRow))));
			System.out.println(dijkstra.getWeight(nodes.get(getNodeIndex(level,
					lastRow))));
			if (dijkstra.getWeight(nodes.get(getNodeIndex(level, lastRow))) < minWeight) {
				minWeight = dijkstra.getWeight(nodes.get(getNodeIndex(level,
						lastRow))) + topWeight;
				minPath = dijkstra.getPathToNode(nodes.get(getNodeIndex(level,
						lastRow)));
			}
		}

	}

	/*************
	 * New Way
	 */
	LinkedList<TriNode> trinodes;
	PriorityQueue<TriNode> pq;
	List<Integer> path;
	class TriNode implements Comparable<TriNode>{
		boolean marked;
		int minVal = Integer.MAX_VALUE;
		int level;
		int point;
		TriNode minParent;
		int value;
		public TriNode(int level, int point, int value) {
			super();
			this.level = level;
			this.point = point;
			this.value = value;
		}

		public TriNode getLeft() {
			return findNode(level + 1, point);
		}

		public TriNode getRight() {
			return findNode(level + 1, point + 1);
		}
		@Override
		public String toString() {
			return String.valueOf(value);
		}
		

		@Override
		public int compareTo(TriNode o) {
			return this.minVal - o.minVal;
		}
	}
	public TriNode findNode(int level, int point) {
		if (level >= al.size()) {
			//throw new RuntimeException("already last level");
			 return null;
		}
		for (TriNode node : trinodes) {
			if (node.level == level && node.point == point) {
				return node;
			}
		}
		return null;
	}
	public void findMin() {
		createStructure();
		pq = new PriorityQueue<TriNode>();
		path = new LinkedList<Integer>();
		TriNode top = findNode(0, 0);
		top.minVal = top.value;
		top.marked = true;
		scan(top);
		while(pq.size()>0){
			System.out.println(pq);
			TriNode currentMin = pq.remove();
			if(currentMin.marked)continue;
			System.out.println("current Min" + " " + currentMin.value + " " + "min Val " + currentMin.minVal);
			if(currentMin.level == al.size()-1)
				{
					TriNode temp = currentMin;
					minWeight = temp.minVal;
					path.add(currentMin.value);
					while(temp.minParent!=null){
						path.add(temp.minParent.value);
						temp = temp.minParent;
					}
					break;
				}
			
			currentMin.marked=true;
			scan(currentMin);
		}
	}

	private void scan(TriNode top) {
		TriNode left = top.getLeft();
		TriNode right = top.getRight();
		if(left!=null){
			if(left.minVal > top.minVal + left.value){
				left.minVal = top.minVal + left.value;
				left.minParent = top;
			}
			if(!left.marked)pq.add(left);
		}
		if(right!=null){
			if(right.minVal > top.minVal + right.value){
				right.minVal = top.minVal + right.value;
				right.minParent = top;
			}
			if(!right.marked)pq.add(right);
		}	
	}

	private void createStructure() {
		trinodes = new LinkedList<TriNode>();
		for (int level = 0; level < al.size(); level++) {
			ArrayList<Integer> row = al.get(level);
			for (int point = 0; point < row.size(); point++) {
				trinodes.add(new TriNode(level, point, al.get(level).get(point)));
			}
		}
	}
}
