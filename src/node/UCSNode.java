package node;

public class UCSNode implements Comparable<UCSNode> {
    public int id;
    public int distance;
    public int parent;
    public boolean explored;

    public UCSNode(int id, int distance, int parent, boolean explored) {
        this.id = id;
        this.distance = distance;
        this.parent = parent;
        this.explored = explored;
    }

    public int compareTo(UCSNode o) {
        return (this.distance - o.distance);
    }
    
    public boolean equals(UCSNode o) {
    	Boolean result = false;
    	if(id == o.id && distance == o.distance && parent == o.parent && explored == o.explored)
    		result = true;
		return result;
    }
}