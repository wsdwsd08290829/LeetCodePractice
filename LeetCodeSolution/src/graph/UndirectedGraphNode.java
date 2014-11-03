package graph;

import java.util.ArrayList;
import java.util.List;

//  Definition for undirected graph.
class UndirectedGraphNode {
	int label;
	List<UndirectedGraphNode> neighbors;

	UndirectedGraphNode(int x) {
		label = x;
		neighbors = new ArrayList<UndirectedGraphNode>();
	}
};
