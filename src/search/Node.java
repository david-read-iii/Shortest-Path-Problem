public class Node implements Comparable<Node> {
    public int id;
    public int key;

    public Node(int id, int key) {
        this.id = id;
        this.key = key;
    }

    public int compareTo(Node o)
    {
        return (this.key - o.key);
    }
}