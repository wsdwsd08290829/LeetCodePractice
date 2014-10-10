package com.sidausc.ds.graph.dijkstra;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sidausc.ds.graph.models.Edge;
import com.sidausc.ds.graph.models.Graph;
import com.sidausc.ds.graph.models.Vertex;

/**
 * while unsettle not empty extract min from unsettled (|V|) update neighbours
 * that not in settled, add them to unsettle add it self to settled
 * 
 * complexity while loop |V|, find min-> V => V^2 for neighbours |E| total:
 * O(|V^2|+|E|) => O(V^2)
 * 
 * if(use heap) saving all node(comparable for distance, init to be infinity) to
 * heap O(|V|+|E|)*log(V), log(V) for extractMin, and decrease distance in queue
 * 
 * @author sidawang
 * 
 */
public class DijkstraAlgEngine {
	private Graph graph;

	public DijkstraAlgEngine(Graph g) {
		graph = g;
	}

	Set<Vertex> unsettledSet = new HashSet<Vertex>();
	Set<Vertex> settledSet = new HashSet<Vertex>();
	Map<Vertex, Vertex> predecessor = new HashMap<Vertex, Vertex>();
	Map<Vertex, Integer> distance = new HashMap<Vertex, Integer>();

	public void runDijkstra(Vertex src) {
		unsettledSet.add(src);
		while (unsettledSet.size() > 0) {
			Vertex visiting = findMinFromUnsettled();
			settledSet.add(visiting);
			unsettledSet.remove(visiting);
			List<Vertex> neighbours = getUnsettledNeighours(visiting);
			for (Vertex n : neighbours) {
				Integer currentDist = getCurrentDistance(n);
				Integer tryDist = getCurrentDistance(visiting)
						+ getDistanceBetween(visiting, n);
				if (tryDist < currentDist) {
					distance.put(n, tryDist);
					predecessor.put(n, visiting);
				}
				unsettledSet.add(n);
			}
		}
	}

	private Integer getDistanceBetween(Vertex visiting, Vertex n) {
		Edge e = graph.getEdge(visiting, n);
		if (e != null)
			return e.getWeight();
		throw new RuntimeException("did not find edge between" + visiting
				+ " and " + n);
	}

	private Integer getCurrentDistance(Vertex v) {
		if (distance.get(v) == null) {
			distance.put(v, Integer.MAX_VALUE);
		}
		return distance.get(v);
	}

	private List<Vertex> getUnsettledNeighours(Vertex visiting) {
		List<Vertex> nbs = graph.getAdjacentNodes(visiting);
		for (Vertex v : nbs) {
			if (settledSet.contains(v)) {
				nbs.remove(v);
			}
		}
		return nbs;
	}

	private Vertex findMinFromUnsettled() {
		Vertex minVertex = null;
		for (Vertex v : unsettledSet) {
			if (minVertex == null)
				minVertex = v;
			if (distance.get(v) == null) {
				distance.put(v, Integer.MAX_VALUE);
			}
			if (distance.get(v) < distance.get(minVertex)) {
				minVertex = v;
			}
		}
		return minVertex;
	}

	public List<Vertex> getPathToNode(Vertex dest) {
		/*System.out.println(dest);
		System.out.println(predecessor);
		System.out.println(predecessor.get(dest));
		System.out.println(predecessor.get(predecessor.get(dest)));*/
		LinkedList<Vertex> path = new LinkedList<Vertex>();
		path.add(dest);
		while (predecessor.get(dest) != null) {
			dest = predecessor.get(dest);
			path.add(dest);
		}
		return path;
	}

	public int getWeight(Vertex dest) {
		int weight = 0;

		while (predecessor.get(dest) != null) {
			weight += graph.getEdge(predecessor.get(dest), dest).getWeight();
			dest = predecessor.get(dest);
		}
		return weight;
	}
}
