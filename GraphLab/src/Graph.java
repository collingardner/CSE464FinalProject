import java.util.ArrayList;

import data_structures.Pair;

/**
 * Undirected Graph class.
 * @author karroje
 *
 */

public abstract class Graph {
	int num_nodes;
	int num_edges;
	
	public Graph(int n) {
		num_nodes = n;
	}
	
	/**
	 * Return number of nodes in graph.
	 * @return
	 */
	int numNodes() {
		return num_nodes;
	}
	
	/**
	 * Return number of edges in graph.
	 * @return
	 */
	int numEdges() {
		return num_edges;
	}
	

	
	/**
	 * Return weight of edge between node i to node j, or null if it doesn't exist..
	 * @param i
	 * @param j
	 * @return double
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	abstract Double getWeight(int i, int j);

	
	/** 
	 * Set weight of edge between from node i to node j.
	 * @param i
	 * @param j
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	abstract void setWeight(int i, int j, Double weight);
	
	/**
	 * Return a list of all node adjacent to i.
	 * @param i
	 * @ return
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	abstract ArrayList<Pair<Integer,Double>> adjacentNodes(int i);
	
	/**
	 * Return the degree of node i (the number of edge going "out" of i).
	 * @return
	 * @exception IndexOutOfBoundsExcetion   Thrown if either node doesn't exist.
	 */
	abstract int degree(int i);

}
