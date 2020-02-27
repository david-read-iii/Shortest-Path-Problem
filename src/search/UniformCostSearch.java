package search;

import java.util.Iterator;
import java.util.PriorityQueue;

import graph.Graph;
import node.UCSNode;

public class UniformCostSearch {

	/**
	 * Runs Uniform Cost Search given an undirected graph, a start node, and a goal
	 * node.
	 *  @graph A graph object initialized with an adjacency matrix.
	 *  @start The index of the start node.
	 *  @goal The index of the goal node.
	 *  @return Returns null if the search fails. Returns an array of UCSNodes with
	 *  optimal cost and parent attributes as determined by the search algorithm.*/
	public static UCSNode[] runSearch(Graph graph, int start, int goal) {
		
		UCSNode[] result = null; // Resulting array that will be returned.
		UCSNode[] nodes = new UCSNode[graph.getNumberOfVertices()]; // Keeps track of the state of all nodes during the algorithm.
		PriorityQueue<UCSNode> frontier = new PriorityQueue<UCSNode>(); // Keeps track of nodes in the frontier.
		
		// Initialize the nodes with default values.
		for(int i = 0; i < graph.getNumberOfVertices(); i++) {
			nodes[i] = new UCSNode(i, Integer.MAX_VALUE, -1, false);
		}
		
		// Initialize the starting node.
		nodes[start] = new UCSNode(start, 0 , -1, false);		
		frontier.add(nodes[start]);
		
		while(!frontier.isEmpty()) {
			
			// Get the node with the lowest cost from the frontier.
			UCSNode u = frontier.poll();
			nodes[u.id].explored = true;
			
			// If node u is the goal node, set the result to the solution and clear the frontier so the loop breaks.
			if(u.id == goal) {
				result = nodes;
				frontier.clear();
			}
			
			// Node u is not the goal node, so iterate through node u's neighbors and add unexplored nodes to the frontier.
			else {
				for(int v = 0; v < graph.getNumberOfVertices(); v++) {
					if(graph.getAdjacencyMatrix()[u.id][v] > 0 && !nodeIsInFrontier(v,frontier) && nodes[v].explored == false) {
						nodes[v] = new UCSNode(v, graph.getAdjacencyMatrix()[u.id][v] + u.distance, u.id, false);
						frontier.add(nodes[v]);
					} else if(graph.getAdjacencyMatrix()[u.id][v] > 0 && nodeIsInFrontier(v,frontier) && getNodeCostFromFrontier(v,frontier) > graph.getAdjacencyMatrix()[u.id][v] + u.distance) {
						frontier.remove(nodes[v]);
						nodes[v] = new UCSNode(v, graph.getAdjacencyMatrix()[u.id][v] + u.distance, u.id, false);
						frontier.add(nodes[v]);
					}
				}
			}
		}
		return result;
	}
	
	private static boolean nodeIsInFrontier(int id, PriorityQueue<UCSNode> queue) {
		
		boolean result = false;
		
		Iterator<UCSNode> iterator = queue.iterator();
		while(iterator.hasNext()) {
			UCSNode temp = iterator.next();
			if(temp.id == id) {
				result = true;
			}
		}
		
		return result;
	}
	
	private static int getNodeCostFromFrontier(int id, PriorityQueue<UCSNode> queue) {
		int result = -1;
		
		Iterator<UCSNode> iterator = queue.iterator();
		while(iterator.hasNext()) {
			UCSNode temp = iterator.next();
			if(temp.id == id) {
				result = temp.distance;
			}
		}
		return result;
	}
}
