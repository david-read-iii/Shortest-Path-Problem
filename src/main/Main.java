package main;

import java.util.ArrayList;
import java.util.Scanner;

import graph.Graph;
import node.GBFSNode;
import node.UCSNode;
import search.GreedyBestFirstSearch;
import search.UniformCostSearch;

public class Main {

    /**
     * Creates an example graph object and asks the user to pick an start node, goal node and choice
     * of search algorithm. Then, the user's choice of search algorithm is executed and returns the
     * appropriate path to the goal node and cost to get from start to finish.*/
    public static void main(String[] args) {

        // Create graph object.
        int[][] A =
                {{0, 4, 2, 3, 0, 0, 0, 0, 0},
                {4, 0, 0, 0, 0, 7, 0, 9, 0},
                {2, 0, 0, 0, 0, 2, 6, 0, 7},
                {3, 0, 0, 0, 0, 0, 2, 0, 0},
                {0, 0, 0, 0, 0, 6, 0, 5, 0},
                {0, 7, 2, 0, 6, 0, 8, 0, 0},
                {0, 0, 6, 2, 0, 8, 0, 0, 3},
                {0, 9, 0, 0, 5, 0, 0, 0, 12},
                {0, 0, 7, 0, 0, 0, 3, 12, 0}};
        Graph graph = new Graph(A);

        // The matrix containing the heuristic sets for the above graph. Used in the greedy best first search algorithm.
        int[][] H =
                {{0, 3, 1, 2, 10, 7, 6, 9, 8},
                {1, 0, 4, 5, 7, 4, 6, 8, 10},
                {1, 5, 0, 3, 6, 1, 2, 7, 10},
                {2, 4, 4, 0, 10, 5, 1, 10, 6},
                {10, 8, 6, 9, 0, 3, 5, 1, 7},
                {3, 4, 1, 5, 4, 0, 6, 8, 6},
                {3, 5, 4, 1, 8, 7, 0, 6, 2},
                {6, 4, 8, 10, 1, 4, 6, 0, 5},
                {5, 7, 6, 3, 10, 7, 1, 6, 0}};

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

            ArrayList<GBFSNode> GBFSresult = null;
            ArrayList<UCSNode> UCSresult = null;

            // Use the greedy best first search algorithm to find the shortest path from origin to destination city.
            if(algorithm == 0) {

                GBFSresult = GreedyBestFirstSearch.runSearch(graph, origin, destination, H[destination]);

                // Print solution path.
                System.out.print("Path: [");
                for(int i = 0; i < GBFSresult.size(); i++) {
                    System.out.print(GBFSresult.get(i).id);
                    if(i != GBFSresult.size() - 1) {
                        System.out.print(", ");
                    } else {
                        System.out.println("]");
                    }
                }

                // Print solution cost.
                System.out.println("Cost: " + GBFSresult.get(GBFSresult.size() - 1).cost);
            }

            // Use the uniform cost search algorithm to find the shortest path from origin to destination city.
            else if(algorithm == 1) {

                UCSresult = UniformCostSearch.runSearch(graph, origin, destination);

                // Print solution path.
                System.out.print("Path: [");
                for(int i = 0; i < UCSresult.size(); i++) {
                    System.out.print(UCSresult.get(i).id);
                    if(i != UCSresult.size() - 1) {
                        System.out.print(", ");
                    } else {
                        System.out.println("]");
                    }
                }

                // Print solution cost.
                System.out.println("Cost: " + UCSresult.get(UCSresult.size() - 1).cost);
            }

        } catch(Exception e) {
            System.out.println("Search failed");
        }
    }
}
