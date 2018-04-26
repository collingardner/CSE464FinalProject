import java.util.ArrayList;
import java.util.Collections;

import data_structures.Pair;

/**
 * @author karroje
 *   Collin Gardner
 */ 



public class ListGraph extends Graph {
	
	ArrayList<ArrayList<Pair<Integer,Double>>> graph;
	public ListGraph(int n) {
		super(n);
		graph = new ArrayList<ArrayList<Pair<Integer,Double>>>(Collections.nCopies(n, null));
		for(int i = 0; i < n; i++){
			graph.set(i, new ArrayList<Pair<Integer,Double>>(Collections.nCopies(n, null)));
		}
	}

	/* (non-Javadoc)
	 * @see Graph#weight(int, int)
	 */
	@Override
	Double getWeight(int i, int j) {  //should be correct
		// TODO
		if(i > numNodes() || j > numNodes())
			throw new IndexOutOfBoundsException();
		if(graph.get(i).get(j) == null)
			return null;
		Double ret;	
		ret = graph.get(i).get(j).second;
		return ret;
	}



	/* (non-Javadoc)
	 * @see Graph#addEdge(int, int)
	 */
	@Override
	void setWeight(int i, int j, Double weight) { //
		
		ArrayList<Pair<Integer,Double>> list;
		ArrayList<Pair<Integer,Double>> list2;
		Pair<Integer,Double> pair = new Pair<Integer,Double>(j, weight);
		Pair<Integer,Double> pair2 = new Pair<Integer,Double>(i, weight);
     	if(i > numNodes() || j > numNodes() ){
     		throw new IndexOutOfBoundsException(); //this needs to be there to throw the exception
     	}
     	list = graph.get(i);
     	list2 = graph.get(j);
     	list.set(j, new Pair<Integer,Double>(j,weight));
     	list2.set(i, new Pair<Integer,Double>(i,weight));
     	graph.get(i).get(j).second = weight;
        graph.get(j).get(i).second = weight; 	
	}
		
	/* (non-javadoc)
	 * @see Graph#AdjacentNodes
	 */
	ArrayList<Pair<Integer,Double>> adjacentNodes(int i) {  //throw indexoutofboundsExcpetion if node doesnt exist... 
	ArrayList<Pair<Integer,Double>> ret = new ArrayList<Pair<Integer,Double>>();
	if(graph.get(i) == null){
		throw new IndexOutOfBoundsException();
	}
		for (int j = 0; j < numNodes(); j++) {
			if (graph.get(i).get(j) != null)
				ret.add(new Pair<Integer, Double>(j, graph.get(i).get(j).second));
		}
		return ret;
	}
	 
	/* (non-javadoc)
	 * @see Graph#degree
	 */
	int degree(int i) {
		int nodes = numNodes();
		int count = 0;
		if(nodes <= 1)
			throw new IndexOutOfBoundsException();
		for(int k = 0; k < graph.get(i).size(); k++){
			if(graph.get(i).get(k) != null)
				count++;
		}
		return count;
	}

}
