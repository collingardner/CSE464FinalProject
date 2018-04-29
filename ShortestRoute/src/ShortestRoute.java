import data_structures.Pair;
import java.util.*;

public class ShortestRoute {
	static public boolean list_graph = false;
	 private static final int NO_PARENT = -1;
	 private static ArrayList<Pair<Integer, ArrayList<String>>> shortestsDist = new ArrayList<Pair<Integer, ArrayList<String>>>();
	 private static String getPath= "";
	 private static ArrayList<String> totalPaths = new ArrayList<String>();
	 // Function that implements Dijkstra's single source shortest path algorithm for a graph from Graph Class
	    private static void dijkstra(Graph G, int startVertex)
	    {
	        int nVertices = G.num_nodes;
	        // shortestDistances[i] will hold the
	        // shortest distance from src to i
	        int[] shortestDistances = new int[nVertices];
	 
	        boolean[] added = new boolean[nVertices];
	 
	        // Initialize all distances as 
	        // INFINITE and added[] as false
	        for (int vertexIndex = 0; vertexIndex < nVertices; 
	                                            vertexIndex++)
	        {
	            shortestDistances[vertexIndex] = Integer.MAX_VALUE;
	            added[vertexIndex] = false;
	        }
	        // Distance of source vertex from
	        // itself is always 0
	        shortestDistances[startVertex] = 0;
	        // Parent array to store shortest
	        // path tree
	        int[] parents = new int[nVertices];
	        parents[startVertex] = NO_PARENT;
	        // Find shortest path for all 
	        // vertices
	        for (int i = 1; i < nVertices; i++)
	        { 
	            // Pick the minimum distance vertex
	            // from the set of vertices not yet
	            // processed. nearestVertex is 
	            // always equal to startNode in 
	            // first iteration.
	            int nearestVertex = -1;
	            int shortestDistance = Integer.MAX_VALUE;
	            for (int vertexIndex = 0;
	                     vertexIndex < nVertices; 
	                     vertexIndex++)
	            {
	                if (!added[vertexIndex] &&
	                    shortestDistances[vertexIndex] < 
	                    shortestDistance) 
	                {
	                    nearestVertex = vertexIndex;
	                    shortestDistance = shortestDistances[vertexIndex];
	                }
	            }
	            added[nearestVertex] = true;
	            for (int vertexIndex = 0;
	                     vertexIndex < nVertices; 
	                     vertexIndex++) 
	            {
	            	int edgeDistance = 0;// alwayhs will be zero
	                Double edgeDistanceDouble = G.getWeight(nearestVertex, vertexIndex);//adjacencyMatrix[nearestVertex][vertexIndex];
	                if(edgeDistanceDouble != null) {
	                	edgeDistance = edgeDistanceDouble.intValue();
	                }
	                
	                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) 
	                {
	                    parents[vertexIndex] = nearestVertex;
	                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance;
	                }
	            }
	        }
	        printSolution(startVertex, shortestDistances, parents);
	    }
	 
	    private static void printSolution(int startVertex,
	                                      int[] distances,
	                                      int[] parents)
	    {
	        int nVertices = distances.length;
	        for (int vertexIndex = 0; 
	                 vertexIndex < nVertices; 
	                 vertexIndex++) 
	        {
	        	getPath = "";
	            if (vertexIndex != startVertex) 
	            {
	                printPath(vertexIndex, parents);
	                ArrayList<String> addList = new ArrayList<String>();
	                Collections.addAll(addList, getPath.split(","));
	                Pair pair = new Pair(distances[vertexIndex], addList);
	                shortestsDist.add(pair);  //adding new pair to shortest distances list
	            }
	        }
	    }
	 
	    // Function to add shortest path
	    // from source to currentVertex
	    // using parents array
	    private static void printPath(int currentVertex, int[] parents)
	    {
	        if (currentVertex == NO_PARENT)
	        {
	            return;
	        }
	        printPath(parents[currentVertex], parents);
	        getPath += currentVertex + ",";
	    }
	    //all the methods above are for the dikjstra method

	    private static String getRoute() {
	    	String ret = "";
	    	System.out.print("Enter route desired seperate by commas: ");
	    	Scanner sc = new Scanner(System.in);
	    	ret = sc.nextLine();
	    	ret = ret.replaceAll("[, ;]", "");
	    	return ret;
	    	
	    }
	    
	    private static String swap(String a, int i, int j) {
	    	char temp;
	    	char[] charArray = a.toCharArray();
	    	temp = charArray[i];
	    	charArray[i] = charArray[j];
	    	charArray[j] = temp;
	    	return String.valueOf(charArray);
	    }
	    
	    private static void generateCombinations(String str, int l, int r, String first, String last) {
	    	if (l == r) {
	    		if(str.substring(0, 1).equals(first) && str.substring(str.length() - 1).equals(last)){
		    		totalPaths.add(str);
	    		}
	    	} else {
	    	for (int i = l; i <= r; i++) {
	    		str = swap(str, l, i);
	    		generateCombinations(str, l+1, r, first, last);
                str = swap(str,l,i);
	    	}
	    	}
	    }
	    
		private static Graph newGraph(int n) {
			return GraphFactory.make_graph(n, list_graph);
		}
	    
	    
	   public static void main(String[] args) {
		   Graph G = newGraph(11);// 10 points but has to be one higher
		   //Make the graph from 4.3 in Final Algorithm paper points E unlabled in that paper
			G.setWeight(0, 4, 20.0);
			G.setWeight(0, 5, 11.0);
			G.setWeight(0, 6, 13.0);// End of A 
			G.setWeight(4, 5, 12.0);
			G.setWeight(4, 2, 9.0);// End of E
			G.setWeight(5, 2, 3.0);
			G.setWeight(5, 6, 4.0);
			G.setWeight(5, 8, 17.0);// End of F
			G.setWeight(6, 1, 6.0);// End of G
			G.setWeight(2, 7, 10.0);// End of C
			G.setWeight(8, 7, 7.0);
			G.setWeight(8, 9, 18.0);
			G.setWeight(8, 1, 16.0);
			G.setWeight(8, 3, 5.0); // End of I
			G.setWeight(7, 3, 8.0); // end of H
			G.setWeight(3, 9, 21.0);// end of D
			G.setWeight(1, 9, 2.0);
			//End of setting up the graph.
			
			for(int i = 0; i < G.num_nodes-1; i++){
				dijkstra(G, i);
			}//List of all the possible combinations of paths to take
			String route = getRoute(); //Gets the route destination
		
		 generateCombinations(route, 0, route.length()-1, route.substring(0, 1), route.substring(route.length() - 1));

	    	double outputMinWeight = Integer.MAX_VALUE; //this is the weight you will output.
	    	double minWeight = 0;
	    	ArrayList<String> outputShortestRoute = new ArrayList<String>();
	    	ArrayList<String> shortestRoute = new ArrayList<String>();
	    	
		    for(int i = 0; i < totalPaths.size(); i++) { //Loop through Paths
		    	int count = 0;
		    	int count2 = 1;
		    	String prev = "";// holder so it does count looping back to itself
		    	//while loop to loop through shortest distance
		    	String path = totalPaths.get(i);
		    	while(path.length() -  1 > count) {  // looking at 1234, count has to be less than something
		    	 //find path from 1 to 2, then 2 to 3, then 3 to 4
		    		
		    		String dest1 = path.substring(count, count + 1);
		    		String dest2 = path.substring(count + 1, count + 2);
		    		//Start of comparision
		    		for(int j = 0; j < shortestsDist.size(); j++) {
		    			//find path from 1 to 2 and get weight
		    			String firstDest  = shortestsDist.get(j).second.get(0);
		    			int listsize = shortestsDist.get(j).second.size() -1;
		    			String lastDest  = shortestsDist.get(j).second.get(listsize);
		    			//compare to strings above with 1 and 2 respectivl
		    			if(dest1.equals(firstDest) && dest2.equals(lastDest)) {
		    				//SAVE THIS Path to shortestRoute
		    				for(int k = 0; k < shortestsDist.get(j).second.size(); k++){
		    					 if(!prev.equals(shortestsDist.get(j).second.get(k))) {
		    						 shortestRoute.add(shortestsDist.get(j).second.get(k));
		    						 prev = shortestsDist.get(j).second.get(k);
		    					 }
		    				}
		    				int printWeight = shortestsDist.get(j).first;
		    				minWeight += printWeight;
		    				//System.out.println("found path with weight = " + printWeight);
		    			}
		    		} //End of Comparision
		    		count += 1;
		    	}
		    	//Now look for the minimumWeight by comparasion and save it
		    	//also save path that goes along with that minimum weight
		    	if(minWeight < outputMinWeight) {
		    		//System.out.println("minWeight = " + minWeight);
		    		//System.out.println(shortestRoute); //removed for testing
		    		outputMinWeight = minWeight;//update prevMinWeight
		    		minWeight = 0;
		    		
		    		outputShortestRoute = new ArrayList<String>(shortestRoute); //set to zero
		    		shortestRoute.clear();
		    	}  // end of if logic
		    } //End of Looping throught paths   
		    System.out.println("Min weight is " + outputMinWeight);
		    System.out.println("The shortest route is " + outputShortestRoute);
	   }//End of main method
} //end of class
