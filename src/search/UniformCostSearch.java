package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;

import graph.Graph;
import node.Node;

public class UniformCostSearch {

	/**
	 * Runs Uniform Cost Search given an undirected graph, a start node, and a goal node.
	 *  @graph A graph object initialized with an adjacency matrix.
	 *  @start The index of the start node.
	 *  @goal The index of the goal node.
	 *  @return Returns null if the search fails. Returns an ordered ArrayList of nodes that
	 *  are in path order.*/
	public static ArrayList<Node> runSearch(Graph graph, int start, int goal) {
		
		ArrayList<Node> result = null; // Result ArrayList of nodes set to null as default.
		Node[] nodes = new Node[graph.getNumberOfNodes()]; // Keeps track of the cost and parent of each node.
		boolean[] explored = new boolean[graph.getNumberOfNodes()]; // Keeps track of the exploration status of each node.
		PriorityQueue<Node> frontier = new PriorityQueue<Node>(); // Keeps track of nodes in the frontier ordered by cost.
		
		// Initialize the nodes with default values.
		for(int i = 0; i < graph.getNumberOfNodes(); i++) {
			nodes[i] = new Node(i, Integer.MAX_VALUE, -1);
			explored[i] = false;
		}
		
		// Initialize the starting node and add it to the frontier.
		nodes[start] = new Node(start, 0 , -1);		
		frontier.add(nodes[start]);
		
		// Repeat these steps while there are still elements in the queue.
		while(!frontier.isEmpty()) {
			
			// Poll the node with the lowest cost from the frontier.
			Node u = frontier.poll();
			explored[u.id] = true;
			
			// If node u is the goal node, set the result array to the array of nodes with optimal costs and parents. Also, clear the frontier so the loop breaks.
			if(u.id == goal) {
				result = generateSolutionArrayList(nodes, goal);
				frontier.clear();
			}
			
			// If node u is not the goal node, iterate through it's possible neighbors and add qualified nodes to the frontier.
			else {
				for(int v = 0; v < graph.getNumberOfNodes(); v++) {
					// If node u shares an edge with node v, node v is not in the frontier, and node v is unexplored, add it to the frontier.
					if(graph.getAdjacencyMatrix()[u.id][v] > 0 && !nodeIsInFrontier(v,frontier) && explored[v] == false) {
						nodes[v] = new Node(v, graph.getAdjacencyMatrix()[u.id][v] + u.cost, u.id);
						frontier.add(nodes[v]);
					}
					
					// If node u shares an edge with node v, node v is in the frontier, and the cost of node v in the frontier is not optimal, update node v with a more optimal cost.
					else if(graph.getAdjacencyMatrix()[u.id][v] > 0 && nodeIsInFrontier(v,frontier) && getCostFromFrontier(v,frontier) > graph.getAdjacencyMatrix()[u.id][v] + u.cost) {
						frontier.remove(nodes[v]);
						nodes[v] = new Node(v, graph.getAdjacencyMatrix()[u.id][v] + u.cost, u.id);
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
	private static boolean nodeIsInFrontier(int index, PriorityQueue<Node> queue) {
		
		boolean result = false;
		
		Iterator<Node> iterator = queue.iterator();
		while(iterator.hasNext()) {
			Node temp = iterator.next();
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
	private static int getCostFromFrontier(int index, PriorityQueue<Node> queue) {
		
		int result = -1;
		
		Iterator<Node> iterator = queue.iterator();
		while(iterator.hasNext()) {
			Node temp = iterator.next();
			if(temp.id == index) {
				result = temp.cost;
			}
		}
		return result;
	}
	
	/**
	 * Generates the ArrayList that will be returned by the runSearch() method.
	 *  @solutionState A Node object array of the solution state of the algorithm.
	 *  @goal The index of the goal node.*/
	private static ArrayList<Node> generateSolutionArrayList(Node[] solutionState, int goal) {
		
		ArrayList<Node> result = new ArrayList<Node>();

		// Use the node's parent attribute to traceback through the parents. Start at the goal node.
		Node current = solutionState[goal];
		
		do {
			result.add(current);
			current = solutionState[current.parent];
		} while(current.parent != -1);
		
		result.add(current);
		
		// Reverse the ArrayList so the starting node is followed by subsequent nodes ending with the goal node.
		Collections.reverse(result);
		
		return result;
	}
}
