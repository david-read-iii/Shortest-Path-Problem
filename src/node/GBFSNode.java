package node;

public class GBFSNode implements Comparable<GBFSNode> {

    public int id;
    public int heuristic;
    public int parent;
    public int cost;

    /**
     * Represents a node in the greedy best first search algorithm.
     * @param id The index of the node.
     * @param heuristic The heuristic of the node.
     * @param parent The parent of the node.
     * @param cost The cost of the node.*/
    public GBFSNode(int id, int heuristic, int parent, int cost) {
        this.id = id;
        this.heuristic = heuristic;
        this.parent = parent;
        this.cost = cost;
    }

    /**
     * Compares two nodes by their heuristic.
     * @param o The second node that will be compared with this node.*/
    public int compareTo(GBFSNode o) {
        return (this.heuristic - o.heuristic);
    }
}