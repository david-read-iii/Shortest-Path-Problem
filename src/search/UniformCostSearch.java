package search;

import java.util.Iterator;
import java.util.PriorityQueue;

import graph.Graph;
import node.UCSNode;

public class UniformCostSearch {

	/**
	 * Runs Uniform Cost Search given an undirected graph, a start node, and a goal node.
	 *  @graph A graph object initialized with an adjacency matrix.
	 *  @start The index of the start node.
	 *  @goal The index of the goal node.
	 *  @return Returns null if the search fails. Returns an array of UCSNodes with
	 *  optimal cost and parent attributes as determined by the search algorithm.*/
	public static UCSNode[] runSearch(Graph graph, int start, int goal) {
		
		UCSNode[] result = null; // Result array of nodes set to null as default.
		UCSNode[] nodes = new UCSNode[graph.getNumberOfVertices()]; // Keeps track of the state of all nodes during the algorithm.
		PriorityQueue<UCSNode> frontier = new PriorityQueue<UCSNode>(); // Keeps track of nodes in the frontier ordered by cost.
		
		// Initialize the nodes with default values.
		for(int i = 0; i < graph.getNumberOfVertices(); i++)
			nodes[i] = new UCSNode(i, Integer.MAX_VALUE, -1, false);
		
		// Initialize the starting node and add it to the frontier.
		nodes[start] = new UCSNode(start, 0 , -1, false);		
		frontier.add(nodes[start]);
		
		// Repeat these steps while there are still elements in the queue.
		while(!frontier.isEmpty()) {
			
			// Poll the node with the lowest cost from the frontier.
			UCSNode u = frontier.poll();
			nodes[u.id].explored = true;
			
			// If node u is the goal node, set the result array to the array of nodes with optimal costs and parents. Also, clear the frontier so the loop breaks.
			if(u.id == goal) {
				result = nodes;
				frontier.clear();
			}
			
			// If node u is not the goal node, iterate through it's possible neighbors and add qualified nodes to the frontier.
			else {
				for(int v = 0; v < graph.getNumberOfVertices(); v++) {
					// If node u shares an edge with node v, node v is not in the frontier, and node v is unexplored, add it to the frontier.
					if(graph.getAdjacencyMatrix()[u.id][v] > 0 && !nodeIsInFrontier(v,frontier) && nodes[v].explored == false) {
						nodes[v] = new UCSNode(v, graph.getAdjacencyMatrix()[u.id][v] + u.distance, u.id, false);
						frontier.add(nodes[v]);
					}
					
					// If node u shares an edge with node v, node v is in the frontier, and the cost of node v in the frontier is not optimal, update node v with a more optimal cost.
					else if(graph.getAdjacencyMatrix()[u.id][v] > 0 && nodeIsInFrontier(v,frontier) && getCostFromFrontier(v,frontier) > graph.getAdjacencyMatrix()[u.id][v] + u.distance) {
						frontier.remove(nodes[v]);
						nodes[v] = new UCSNode(v, graph.getAdjacencyMatrix()[u.id][v] + u.distance, u.id, false);
						frontier.add(nodes[v]);
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Checks to see if a node with a given index is contained within a given
	 * priority queue object.
	 *  @index The index of the node.
	 *  @queue The priority queue object.
	 *  @return Returns false if a node with the given index is not contained within
	 *  the given priority queue. Returns true if a node with the given index is
	 *  contained within the given priority queue.*/
	private static boolean nodeIsInFrontier(int index, PriorityQueue<UCSNode> queue) {
		
		boolean result = false;
		
		Iterator<UCSNode> iterator = queue.iterator();
		while(iterator.hasNext()) {
			UCSNode temp = iterator.next();
			if(temp.id == index) {
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * Returns the cost attribute of a node with the given index from a given priority
	 * queue object.
	 *  @index The index of the node.
	 *  @queue The priority queue object.
	 *  @return Returns a cost attribute. Will return -1 if the node is not contained
	 *  within the priority queue.*/
	private static int getCostFromFrontier(int index, PriorityQueue<UCSNode> queue) {
		
		int result = -1;
		
		Iterator<UCSNode> iterator = queue.iterator();
		while(iterator.hasNext()) {
			UCSNode temp = iterator.next();
			if(temp.id == index) {
				result = temp.distance;
			}
		}
		return result;
	}
}
