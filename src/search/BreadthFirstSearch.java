// ******************************************************************************************
// This class provides a single static method to run breadth-first search on a graph. The 
// method accepts a Graph object and the number of the starting vertex. The method returns an
// array representing the starting vertex's distance from the other vertices in the graph.
// ******************************************************************************************

package search;

import java.util.LinkedList;

import graph.Graph;

public class BreadthFirstSearch {
	
	private final static int WHITE = 0;
	private final static int GRAY = 1;
	private final static int BLACK = 2;
	
	public static int[] runSearch(Graph graph, int startingNode) {
		
		int[] colors = new int[graph.getNumberOfVertices()]; // Holds the color of each vertex.
		int[] distances = new int[graph.getNumberOfVertices()]; // Holds the distance of each vertex from the starting vertex.
		
		// Initialize all vertices with a white color and an infinite distance.
		for(int i = 0; i < graph.getNumberOfVertices(); i++) {
			colors[i] = WHITE;
			distances[i] = Integer.MAX_VALUE;
		}
		
		// Initialize the starting vertex with a gray color and distance of 0.
		colors[startingNode] = GRAY;
		distances[startingNode] = 0;
		
		// Create a linked list to hold the indices of vertices with the color grey.
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		// Enqueue the starting vertex into the linked list, as it has a gray color.
		queue.addLast(new Integer(startingNode));
		
		// This loop will iterate, discovering new vertices, until the linked list is empty.
		while(!(queue.isEmpty())) {
			
			// Dequeues the index of the gray vertex stored at the head of the linked list and sets it as u.
			int u = queue.pop();
						  			
			// This loop iterates until every vertex that shares an edge with vertex u has been discovered.
			for(int v = 0; v < graph.getAdjacencyMatrix()[u].length; v++) {
				
				// If vertex v shares an edge with vertex u, and node v has the color white, execute this code block.
				if(graph.getAdjacencyMatrix()[u][v] > 0 && colors[v] == WHITE) {
					
					// Set the color of vertex v as gray and set the distance of vertex v as the distance of vertex u plus the value of the edge between the vertices.
					colors[v] = GRAY;
					distances[v] = distances[u] + graph.getAdjacencyMatrix()[u][v];
					
					// Enqueue vertex v into linked list, as it has a grey color.
					queue.add(new Integer(v));
				}
			}
			
			// Change the color of vertex u to black.
			colors[u] = BLACK;
		}
		
		return distances;
	}

}
