package main;

import java.util.ArrayList;
import java.util.Scanner;

imprt graph.Graph;
import node.Node;
import search.BestFirstSearch;
import search.UniformCostSearch;

public class Main {

    /**
     * Creates an example graph object and asks the user to pick an start node, goal node and choice
     * of search algorithm. Then, the user's choice of search algorithm is executed and returns the
     * appropriate path to the goal node and cost to get from start to finish.*/
    public static void main(String[] args) {

        // Create graph object.
        int[][] A =
                {
                        {0, 4, 2, 3, 0, 0, 0, 0, 0},
                        {4, 0, 0, 0, 0, 7, 0, 9, 0},
                        {2, 0, 0, 0, 0, 2, 6, 0, 7},
                        {3, 0, 0, 0, 0, 0, 2, 0, 0},
                        {0, 0, 0, 0, 0, 6, 0, 5, 0},
                        {0, 7, 2, 0, 6, 0, 8, 0, 0},
                        {0, 0, 6, 2, 0, 8, 0, 0, 3},
                        {0, 9, 0, 0, 5, 0, 0, 0, 12},
                        {0, 0, 7, 0, 0, 0, 3, 12, 0}};
        Graph graph = new Graph(A);

        /*These are all of the heuristic sets depending on the goal node.
        A different set will be applied depending on the goal
        the numbers of the variables H0 - H8 correspond to their goal states
        Note that these heuristic values ARE specific to the graph declared above*/
        final int[] H0 = {0, 3, 1, 2, 10, 7, 6, 9, 8};
        final int[] H1 = {1, 0, 4, 5, 7, 4, 6, 8, 10};
        final int[] H2 = {1, 5, 0, 3, 6, 1, 2, 7, 10};
        final int[] H3 = {2, 4, 4, 0, 10, 5, 1, 10, 6};
        final int[] H4 = {10, 8, 6, 9, 0, 3, 5, 1, 7};
        final int[] H5 = {3, 4, 1, 5, 4, 0, 6, 8, 6};
        final int[] H6 = {3, 5, 4, 1, 8, 7, 0, 6, 2};
        final int[] H7 = {6, 4, 8, 10, 1, 4, 6, 0, 5};
        final int[] H8 = {5, 7, 6, 3, 10, 7, 1, 6, 0};

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
            ArrayList<GBFSNode> GBFSresult = null;
            ArrayList<Node> UCSresult = null;

            if(algorithm == 0) {
                int[] heuristic = new int[graph.getNumberOfNodes()];

                //Pass heuristics to the runSeach method depending on the destination city//
                switch (destination)
                {
                    case 0: heuristic = H0;
                        break;
                    case 1: heuristic = H1;
                        break;
                    case 2: heuristic = H2;
                        break;
                    case 3: heuristic = H3;
                        break;
                    case 4: heuristic = H4;
                        break;
                    case 5: heuristic = H5;
                        break;
                    case 6: heuristic = H6;
                        break;
                    case 7: heuristic = H7;
                        break;
                    case 8: heuristic = H8;
                        break;
                }
                GBFSresult = BestFirstSearch.runSearch(graph, origin, destination, heuristic);

                //print solution path//
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
