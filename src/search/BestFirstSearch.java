
// Java code to illustrate add()
import java.util.*;

public class BestFirstSearch {
    public Node BestFirstSearch(Graph g, Node start, Node goal) {
        PriorityQueue<Node> queue = new PriorityQueue<Node>();
        //TODO: add comparator
        queue.add(start);
        while (!queue.isEmpty())
        {
            Node u = queue.poll();
            if (u == goal) {
                return u;
            }
            else
            {

            }
        }
        return null;
    }
}
