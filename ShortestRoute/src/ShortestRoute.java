import data_structures.Pair;
import java.util.*;

public class ShortestRoute {
	static public boolean list_graph = false;
	 private static final int NO_PARENT = -1;
	 private static ArrayList<Pair<Integer, ArrayList<String>>> shortestsDist = new ArrayList<Pair<Integer, ArrayList<String>>>();
	 private static String getPath= "";
	 // Function that implements Dijkstra's
	    // single source shortest path
	    // algorithm for a graph represented 
	    // using adjacency matrix
	    // representation
	    private static void dijkstra(Graph G,
	                                        int startVertex)
	    {
	        int nVertices = G.num_nodes;
	 
	        // shortestDistances[i] will hold the
	        // shortest distance from src to i
	        int[] shortestDistances = new int[nVertices];
	 
	        // added[i] will true if vertex i is
	        // included / in shortest path tree
	        // or shortest distance from src to 
	        // i is finalized
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
	 
	        // The starting vertex does not 
	        // have a parent
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
	 
	            // Mark the picked vertex as
	            // processed
	            added[nearestVertex] = true;
	 
	            // Update dist value of the
	            // adjacent vertices of the
	            // picked vertex.
	            for (int vertexIndex = 0;
	                     vertexIndex < nVertices; 
	                     vertexIndex++) 
	            {
	            	int edgeDistance = 0;// alwayhs will be zero
	                Double edgeDistanceDouble = G.getWeight(nearestVertex, vertexIndex);//adjacencyMatrix[nearestVertex][vertexIndex];
	                if(edgeDistanceDouble != null) {
	                	edgeDistance = edgeDistanceDouble.intValue();
	                }
	                
	                
	                if (edgeDistance > 0
	                    && ((shortestDistance + edgeDistance) < 
	                        shortestDistances[vertexIndex])) 
	                {
	                    parents[vertexIndex] = nearestVertex;
	                    shortestDistances[vertexIndex] = shortestDistance + 
	                                                       edgeDistance;
	                }
	            }
	        }
	 
	        printSolution(startVertex, shortestDistances, parents);
	    }
	 
	    // A utility function to print 
	    // the constructed distances
	    // array and shortest paths
	    private static void printSolution(int startVertex,
	                                      int[] distances,
	                                      int[] parents)
	    {
	        int nVertices = distances.length;
	        System.out.print("Vertex\t Distance\tPath");
	         
	        for (int vertexIndex = 0; 
	                 vertexIndex < nVertices; 
	                 vertexIndex++) 
	        {
	        	getPath = "";
	            if (vertexIndex != startVertex) 
	            {
	               System.out.print("\n" + startVertex + " -> ");
	                System.out.print(vertexIndex + " \t\t ");
	               System.out.print(distances[vertexIndex] + "\t\t");
	                printPath(vertexIndex, parents);
	                System.out.println(getPath);
	                ArrayList<String> addList = new ArrayList<String>();
	                Collections.addAll(addList, getPath.split(","));
	                Pair pair = new Pair(distances[vertexIndex], addList);
	                shortestsDist.add(pair);  //adding new pair to shortest distances list
	            }
	        }
	    }
	 
	    // Function to print shortest path
	    // from source to currentVertex
	    // using parents array
	    private static void printPath(int currentVertex,
	                                  int[] parents)
	    {
	         
	        // Base case : Source node has
	        // been processed
	        if (currentVertex == NO_PARENT)
	        {
	            return;
	        }
	        printPath(parents[currentVertex], parents);
	        System.out.print(currentVertex + " ");
	        getPath += currentVertex + ",";
	    }
	    //all the methods above are for the dikjstra method
	    
	
	    
	    
		private static Graph newGraph(int n) {
			return GraphFactory.make_graph(n, list_graph);
		}
	    
	    
	   public static void main(String[] args) {
		   Graph G = newGraph(11);// 10 points but has to be one higher
			//Make the graph from 4.3 in Final Algorithm paper points E unlabled in that paper
			//Also since it is Int, 1 = A, 2 = B, and so forth
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
		
			
			//For Checking Purposes
			for(int i = 0; i < shortestsDist.size(); i++) {
				System.out.println(shortestsDist.get(i).first + " Path = " + shortestsDist.get(i).second);
			}
			
			ArrayList<Integer> destinations = new ArrayList<Integer>();//This is just for example
			destinations.add(0);
			destinations.add(1);
			destinations.add(2);
			destinations.add(3);
		    //Generate All paths method store in TotalPaths
			// Todo Permutions
			
		    
		    //MinWeight = Infinity
			
		    ArrayList<String> totalPaths = new ArrayList<String>();
		    totalPaths.add("0123");
		    totalPaths.add("0213");
		    
		    for(int i = 0; i < totalPaths.size(); i++) { //Loop through Paths
		    	int count = 0;
		    	int count2 = 1;
		    	//while loop to loop through shortest distance
		    	String path = totalPaths.get(i);
		    	System.out.println("PASS");
		    	
		    	
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
		    				//SAVE THIS Path
		    				int printWeight = shortestsDist.get(j).first;
		    				System.out.println("found path with weight = " + printWeight);
		    			}
		    		} //End of Comparision
		    		count += 1;
		    	}
		    }
			

	   }
}
