package node;

public class Node implements Comparable<Node> {
    public int id;
    public int cost;
    public int parent;

	/**
	 * Represents a node in the uniform cost search algorithm.
	 * @param id The index of the node.
	 * @param cost The cost of the node.
	 * @param parent The parent of the node.*/
    public Node(int id, int cost, int parent) {
        this.id = id;
        this.cost = cost;
        this.parent = parent;
    }

	/**
	 * Compares two nodes by their costs.
	 * @param o The second node that will be compared with this node.*/
    public int compareTo(Node o) {
        return (this.cost - o.cost);
    }
}