//TODO: figure out how to implement heuristics and expand nodes based on heuristic value. Implement node class//
import java.util.*;

public class BestFirstSearch {
    public static String BestFirstSearch(Graph g, int start, int goal) {
        int[] parent = new int[g.getNumberOfVertices()];
        boolean[] expanded = new boolean[g.getNumberOfVertices()];

        int[][] A = g.getAdjacencyMatrix();
        for (int i = 0; i<g.getNumberOfVertices(); i++)
        {
            parent[i] = 0;
        }
        parent[start] = -1;
        String result = null;

        expanded[start] = true;

        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        //TODO: add comparator
        queue.add(start);
        while (!queue.isEmpty())
        {
            int u = queue.poll();
            if (u == goal) {
                result = "Found: " + String.valueOf(u);
                break;
            }
            else
            {
                for(int v = 0; v < g.getNumberOfVertices(); v++)
                {
                    if (A[u][v] != 0 && expanded[v] == false)
                    {
                        queue.add(v);
                        parent[v] = u;
                    }
                    expanded[u] = true;
                }
            }
        }
        if (result!=null)
        {
            //for finding path taken and total cost//
            Stack<Integer> stack = new Stack<Integer>();
            String path = String.valueOf(goal);
            int cost = 0;

            int i = goal;

            while (i!=-1)
            {
                cost+=A[parent[i]][i];
                i = parent[i];
                path = String.valueOf(i) + " -> " + path;
            }
            result += "\nPath taken: " + path + "\n Total cost: " + cost;
        }
        return result;
    }
}
