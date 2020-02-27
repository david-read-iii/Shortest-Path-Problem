package search;

import java.util.*;

import graph.Graph;
import node.Node;

public class BestFirstSearch {

    /**
     * Runs Greedy Best First Search given an undirected graph, a start node, a goal node, and a set of heuristics.
     *  @graph A graph object initialized with an adjacency matrix.
     *  @start The index of the start node.
     *  @goal The index of the goal node.
     *  @h The set of heuristics defined in the main method.
     *  @return Returns null if the search fails. Returns an ordered ArrayList of nodes that
     *  are in path order.*/

    public static ArrayList<GBFSNode> runSearch(Graph g, int start, int goal, int[] h) {
        boolean[] expanded = new boolean[g.getNumberOfNodes()]; //Boolean array to keep track of expanded//
        GBFSNode[] nodes = new GBFSNode[g.getNumberOfNodes()]; //Node array to keep track of cost and parent of each node//
        ArrayList<GBFSNode> result = null; //Result array list set to initial value of null//

        int[][] A = g.getAdjacencyMatrix(); //adjacency matrix//

        for (int i = 0; i < g.getNumberOfNodes(); i++) { //initialize nodes with default values//
            nodes[i] = new GBFSNode(i, h[i], -1, Integer.MAX_VALUE);
            expanded[i] = false;
        }

        nodes[start] = new GBFSNode(start, h[start], -1, 0); //initialize the starting node//

        PriorityQueue<GBFSNode> queue = new PriorityQueue<>(); //priority queue, the node with the lowest heuristic is expanded//
        queue.add(nodes[start]);

        //repeat until the queue is empty//
        while (!queue.isEmpty()) {

            //poll the node with the lowest heuristic value//
            GBFSNode u = queue.poll();
            expanded[u.id] = true;

            //if u is the goal, generate an array of nodes with the optimal costs and parents. Clear the queue so the loop breaks.//
            if (u.id == goal) {
                result = generateSolutionArrayList(nodes, goal);
                queue.clear();
            }

            else { //if u is not the goal node, iterate through its neighbors and add unexplored nodes to the priority queue//
                for (int j = 0; j < g.getNumberOfNodes(); j++) {
                    if (A[u.id][j] != 0 && !expanded[j] && !nodeIsInQueue(j, queue))
                    {
                        nodes[j] = new GBFSNode(j, h[j], u.id, A[u.id][j] + u.cost);
                        queue.add(nodes[j]);
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
    private static boolean nodeIsInQueue(int index, PriorityQueue<GBFSNode> queue) {

        boolean result = false;

        Iterator<GBFSNode> iterator = queue.iterator();
        while(iterator.hasNext()) {
            GBFSNode temp = iterator.next();
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
    private static ArrayList<GBFSNode> generateSolutionArrayList(GBFSNode[] solutionState, int goal) {

        ArrayList<GBFSNode> result = new ArrayList<GBFSNode>();

        // Use the node's parent attribute to traceback through the parents. Start at the goal node.
        GBFSNode current = solutionState[goal];

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
