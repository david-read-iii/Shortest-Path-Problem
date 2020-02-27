package node;

public class GBFSNode implements Comparable<GBFSNode>
{
    public int id;
    public int h; //heuristic//
    public int parent;
    public int cost;

    public GBFSNode(int id, int h, int parent, int cost)
    {
        this.id = id;
        this.h = h;
        this.parent = parent;
        this.cost = cost;
    }

    public int compareTo(GBFSNode o)
    {
        return (this.h - o.h);
    }
}
