import java.util.ArrayList;

import data_structures.Pair;


/**
 * @author karroje
 *
 */

public class MatrixGraph extends Graph {
	
	Double[][] graph;
	public MatrixGraph(int n) {
		super(n);
		graph = new Double[n][n];
	}

	/* (non-Javadoc)
	 * @see Graph#weight(int, int)
	 */
	@Override
	Double getWeight(int i, int j) {
		return graph[i][j];
	}

	/* (non-Javadoc)
	 * @see Graph#setWeight(int, int, java.lang.Double)
	 */
	@Override
	void setWeight(int i, int j, Double weight) {
		graph[i][j] = weight;// why isnt it weight = graph[i][j];?
		graph[j][i] = weight;
	}

	/* (non-Javadoc)
	 * @see Graph#adjacentNodes(int)
	 */
	@Override
	ArrayList<Pair<Integer, Double>> adjacentNodes(int i) {
		// TODO
		ArrayList<Pair<Integer, Double>> ret = new ArrayList<Pair<Integer, Double>>();//Maxsize, should it be 4, or 8;
		int nodes = numNodes();
		
		for(int j = 0; j < numNodes(); j++){
			if(graph[i][j] != null)
			ret.add(new Pair<Integer,Double>(j, graph[i][j]));
		}
		return ret;
	}
	
	

	/* (non-Javadoc)
	 * @see Graph#degree(int)
	 */
	@Override
	int degree(int i) {

		int nodes = numNodes();		
		int ret = 0;
		for(int j = 0; j < nodes; j++){
			if(graph[i][j] != null)
				ret++;
		}
		return ret;
	}

}
