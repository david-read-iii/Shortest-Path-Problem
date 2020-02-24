package main;

import graph.Graph;
import search.BreadthFirstSearch;

public class Main {

	public static void main(String[] args) {
		
		// A test graph.
		int[][] A = 
			{{0, 1, 0, 0, 1, 0, 0, 0},
			{1, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 1, 0, 1, 1, 0},
			{0, 0, 1, 0, 0, 0, 1, 1},
			{1, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 0, 0, 0, 1, 0},
			{0, 0, 1, 1, 0, 1, 0, 1},
			{0, 0, 0, 1, 0, 0, 1, 0}};
		Graph graph = new Graph(A);
		
		// Run breadth-first search on the test graph from vertex 1 (vertices start at 0).
		int[] distances = BreadthFirstSearch.runSearch(graph, 1);
		for(int i = 0; i < distances.length; i++) {
			System.out.println(i + " : " + distances[i]);
		}
		
	}
}
