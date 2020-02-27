package main;

import java.util.ArrayList;
import java.util.Scanner;

import graph.Graph;
import node.Node;
import search.BestFirstSearch;
import search.UniformCostSearch;

public class Main {

	/**
	 * Creates an example graph object and asks the user to pick an start node, goal node and choice of search
	 * algorithm. Then, the user's choice of search algorithm is executed and returns the appropriate path to the goal
	 * node and cost to get from start to finish.*/
	public static void main(String[] args) {
		
		// Create graph object.
		int[][] A = 
			{{0, 4, 2, 3, 0, 0, 0, 0, 0},
			{4, 0, 0, 0, 0, 7, 0, 9, 0},
			{2, 0, 0, 0, 0, 2, 6, 0, 7},
			{3, 0, 0, 0, 0, 0, 2, 0, 9},
			{0, 0, 0, 0, 0, 6, 0, 5, 0},
			{0, 7, 2, 0, 6, 0, 8, 0, 0},
			{0, 0, 6, 2, 0, 8, 0, 0, 3},
			{0, 9, 0, 0, 5, 0, 0, 0, 12},
			{0, 0, 7, 0, 0, 0, 3, 12, 0}};
		Graph graph = new Graph(A);
		
		// Create scanner object.
		Scanner scan = new Scanner(System.in);
		
		// List city directory.
		System.out.println("City directory:");
		System.out.println("0: Los Santos");
		System.out.println("1: Goldenrod City");
		System.out.println("2: Gel City");
		System.out.println("3: Bigfoot City");
		System.out.println("4: Accident");
		System.out.println("5: Pewter City");
		System.out.println("6: Nameless City");
		System.out.println("7: Big Arm City");
		System.out.println("8: Cianwood City");
		System.out.println();
		
		// Ask user to specify their origin city, destination city, and choice of search algorithm.
		System.out.print("What is your origin city? ");
		int origin = scan.nextInt();
		
		System.out.print("What is your destination city? ");
		int destination = scan.nextInt();
		
		System.out.print("What search algorithm do you want to use? (0: Best First Search, 1: Uniform Cost Search) ");
		int algorithm = scan.nextInt();
		System.out.println();
		
		try {
			
			// Use the user's favorite search algorithm to find the shortest path from their origin city to their destination city.
			ArrayList<Node> result = null;

			if(algorithm == 0) {
				/* TODO: Call the static method runSearch from the BestFirstSearch class. The inputs of this class
				 *  should be a Graph object, an int containing the index of the start node, and an int containing the
				 *  index of the goal node. The output of this class should be an ArrayList of Node objects, where the
				 *  Node objects are in path order. Node objects not in the final path should not be in the ArrayList.
				 *  So, the first Node object in the ArrayList will be the start node the second Node object will be
				 *  the node who has the start node as a parent, the third Node object will be the node who has the
				 *  second node as a parent, ..., the last Node object will be the goal node. */
				
				// result = BestFirstSearch.runSearch(graph, origin, destination);
			} else if(algorithm == 1) {
				result = UniformCostSearch.runSearch(graph, origin, destination);
			}
			
			// Print solution path.
			System.out.print("Path: [");
			for(int i = 0; i < result.size(); i++) {
				System.out.print(result.get(i).id);
				if(i != result.size() - 1) {
					System.out.print(", ");
				} else {
					System.out.println("]");
				}
			}
			
			// Print solution cost.
			System.out.println("Cost: " + result.get(result.size() - 1).cost);
			
		} catch(Exception e) {
			System.out.println("Search failed");
		}
	}
}
