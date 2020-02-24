// ******************************************************************************************
// This class represents a graph with an adjacency matrix. Methods are available to get or
// set the adjacency matrix and get the number of vertices in the graph.
// ******************************************************************************************

package graph;

public class Graph {
	
	private int[][] adjacencyMatrix;
	private int numberOfVertices;
	
	public Graph(int[][] adjacencyMatrix) {
		this.setAdjacencyMatrix(adjacencyMatrix);
		numberOfVertices = adjacencyMatrix.length;
	}

	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
		numberOfVertices = adjacencyMatrix.length;
	}

	public int getNumberOfVertices() {
		return numberOfVertices;
	}

}
