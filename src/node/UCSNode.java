package node;

public class UCSNode implements Comparable<UCSNode> {
    public int id;
    public int cost;
    public int parent;
    public boolean explored;

	/**
	 * Represents a node in the uniform cost search algorithm.
	 *  @id The index of the node.
	 *  @cost The cost of the node.
	 *  @parent The parent of the node.
	 *  @explored Whether the node has been explored during the algorithm.*/
    public UCSNode(int id, int cost, int parent, boolean explored) {
        this.id = id;
        this.cost = cost;
        this.parent = parent;
        this.explored = explored;
    }

	/**
	 * Compares two nodes by their costs.
	 *  @o The second node that will be compared with this node.*/
    public int compareTo(UCSNode o) {
        return (this.cost - o.cost);
    }
}