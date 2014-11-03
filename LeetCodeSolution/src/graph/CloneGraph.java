package graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * a node's neightbour list can have duplicate
 * 
 * @author sidawang
 * 
 */
public class CloneGraph {
	// bfs + map label to graphNode
	public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
		Map<Integer, UndirectedGraphNode> map = new HashMap<Integer, UndirectedGraphNode>();
		if (node == null)
			return null;
		Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
		q.add(node);
		UndirectedGraphNode head = new UndirectedGraphNode(node.label);
		map.put(node.label, head);
		while (!q.isEmpty()) {
			Queue<UndirectedGraphNode> tempQ = new LinkedList<UndirectedGraphNode>();
			while (!q.isEmpty()) {
				UndirectedGraphNode old = q.remove();
				UndirectedGraphNode curr = map.get(old.label);
				// construct curr neighbours;
				List<UndirectedGraphNode> oldNeighbors = old.neighbors;
				for (UndirectedGraphNode oldNei : oldNeighbors) {
					if (!map.containsKey(oldNei.label)) {
						map.put(oldNei.label, new UndirectedGraphNode(
								oldNei.label));
						// !!!here not out and use visited set
						// !!!a node's neighbor list can have duplicate
						tempQ.add(oldNei);
					}
					// curr is completely copied
					curr.neighbors.add(map.get(oldNei.label));
				}
			}
			// update next level to visit
			q = tempQ;
		}
		return head;
	}
	// method2 dfs: http://blog.csdn.net/linhuanmars/article/details/22715747
}
