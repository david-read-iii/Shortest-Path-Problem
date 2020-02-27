// TODO: Remove this class once development on this project is over.

package main;

import search.UniformCostSearch;
import graph.Graph;
import node.UCSNode;

public class TestMainUCS {

	public static void main(String[] args) {
		
		// Test graph A: Undirected graph with no weights
		int[][] A = 
			{{0, 1, 0, 0, 1, 0, 0, 0},
			{1, 0, 0, 0, 0, 1, 0, 0},
			{0, 0, 0, 1, 0, 1, 1, 0},
			{0, 0, 1, 0, 0, 0, 1, 1},
			{1, 0, 0, 0, 0, 0, 0, 0},
			{0, 1, 1, 0, 0, 0, 1, 0},
			{0, 0, 1, 1, 0, 1, 0, 1},
			{0, 0, 0, 1, 0, 0, 1, 0}};
		
		// Test graph B: Simple undirected graph with weights
		int[][] B =
			{{0, 2, 4, 10, 0},
			{2, 0, 0, 0, 9},
			{4, 0, 0, 0, 5},
			{10, 0, 0, 0, 2},
			{0, 9, 5, 2, 0}};
		
		// Test graph C: Complex undirected graph with weights
		int[][] C =
			{{0,3,5,2,0,0,0},
			{3,0,0,0,8,0,0},
			{5,0,0,0,0,4,0},
			{2,0,0,0,0,6,0},
			{0,8,0,0,0,0,10},
			{0,0,4,6,0,0,11},
			{0,0,0,0,10,11,0}};
		
		Graph graph1 = new Graph(A);
		Graph graph2 = new Graph(B);
		Graph graph3 = new Graph(C);
		
		// Run UCS on three test graphs.
		UCSNode[] nodes1 = UniformCostSearch.runSearch(graph1, 1, 7);
		
		System.out.println("Test graph A:");
		if(nodes1 == null) {
			System.out.println("Search failed");
		} else {
			System.out.println("ID:\t" + "Cost:\t" + "Parent:\t" + "Explored?\t");
			for(int i = 0; i < nodes1.length; i++) {
				System.out.println(nodes1[i].id + "\t" + nodes1[i].cost + "\t" + nodes1[i].parent + "\t" + nodes1[i].explored);
			}
		}
		
		UCSNode[] nodes2 = UniformCostSearch.runSearch(graph2, 0, 4);
		
		System.out.println("Test graph B:");
		if(nodes2 == null) {
			System.out.println("Search failed");
		} else {
			System.out.println("ID:\t" + "Cost:\t" + "Parent:\t" + "Explored?\t");
			for(int i = 0; i < nodes2.length; i++) {
				System.out.println(nodes2[i].id + "\t" + nodes2[i].cost + "\t" + nodes2[i].parent + "\t" + nodes2[i].explored);
			}
		}
		
		UCSNode[] nodes3 = UniformCostSearch.runSearch(graph3, 0, 6);

		System.out.println("Test graph C:");
		if(nodes3 == null) {
			System.out.println("Search failed");
		} else {
			System.out.println("ID:\t" + "Cost:\t" + "Parent:\t" + "Explored?\t");
			for(int i = 0; i < nodes3.length; i++) {
				System.out.println(nodes3[i].id + "\t" + nodes3[i].cost + "\t" + nodes3[i].parent + "\t" + nodes3[i].explored);
			}
		}
	}
}
