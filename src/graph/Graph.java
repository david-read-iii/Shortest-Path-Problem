package graph;

public class Graph {
	
	private int[][] adjacencyMatrix;
	private int numberOfNodes;
	
	/**
	 * Represents a graph with nodes and edges with a given adjacency matrix.
	 *  @adjacencyMatrix The adjacency matrix of the graph.
	 *  @numberOfNodes The number of nodes that are in this graph.*/
	public Graph(int[][] adjacencyMatrix) {
		this.setAdjacencyMatrix(adjacencyMatrix);
		numberOfNodes = adjacencyMatrix.length;
	}

	/**
	 *  @return Returns the adjacency matrix of the graph.*/
	public int[][] getAdjacencyMatrix() {
		return adjacencyMatrix;
	}

	/**
	 * Sets the adjacency matrix of this graph to a new given adjacency matrix.
	 *  @adjacencyMatrix The adjacency matrix of the graph.*/
	public void setAdjacencyMatrix(int[][] adjacencyMatrix) {
		this.adjacencyMatrix = adjacencyMatrix;
		numberOfNodes = adjacencyMatrix.length;
	}

	/**
	 *  @return Returns the number of nodes in the graph.*/
	public int getNumberOfNodes() {
		return numberOfNodes;
	}

}
