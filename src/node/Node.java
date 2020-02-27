package node;

public class Node implements Comparable<Node> {
    public int id;
    public int cost;
    public int parent;

	/**
	 * Represents a node in the uniform cost search algorithm.
	 *  @id The index of the node.
	 *  @cost The cost of the node.
	 *  @parent The parent of the node.*/
    public Node(int id, int cost, int parent) {
        this.id = id;
        this.cost = cost;
        this.parent = parent;
    }

	/**
	 * Compares two nodes by their costs.
	 *  @o The second node that will be compared with this node.*/
    public int compareTo(Node o) {
        return (this.cost - o.cost);
    }
}