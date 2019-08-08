package trees_and_graphs_4_1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import trees_and_graphs_4_1.Graph;
import trees_and_graphs_4_1.Vertex;

/**
 * This method is my solution to Chapter 4) Trees and Graphs
 * Question 4.1) ROUTE BETWEEN NODES.
 * 
 * @author Brook Negussie
 * @version August 8, 2019
 */
public class MySolution {
	
	
	/**
	 * States whether or not there is a route between two nodes when
	 * given a directed graph along with two nodes. 
	 * 
	 * This method is my solution to 
	 *     Chapter 4) Trees and Graphs
	 *         Question 4.1) ROUTE BETWEEN NODES: Given a directed graph, 
	 *         				design an algorithm to find out whether there
	 *         				is a route between two nodes.
	 * 
	 * 
	 * @param theG The given graph.
	 * @param theVertexA The given starting vertex.
	 * @param theVertexB The given destination vertex. 
	 * @return True if the path exists or else it returns false.
	 */
	public static boolean bfsPathFound(Graph theG, Vertex theVertexA, Vertex theVertexB) {
		
		boolean pathFound = false;
		
		if (theG == null || theVertexA == null || theVertexB == null) {
			throw new NullPointerException("Passed in at least one null value.");
		}
		
		
		Set<Vertex> actualVertexSet = theG.getActualVertices();
		
		// Making sure to reset visited status:
		for (Vertex v : actualVertexSet) {
			v.setVisited(false);
		}
		
		// Using the breadth-first search algorithm to solve this problem:
		Queue<Vertex> tempQueue = new LinkedList<Vertex>();
		tempQueue.add(theVertexA);
		
		Vertex currentVertex;
		
		while (!tempQueue.isEmpty() && !pathFound) {
			currentVertex = tempQueue.remove();
			
			LinkedList<Vertex> children = theG.getChildren(currentVertex);
			Iterator<Vertex> it = children.iterator();
			
			while (it.hasNext()) {
				Vertex childVertex = it.next();
				
				if (!childVertex.getVisitedStatus()) {
					if (childVertex.equals(theVertexB)) {
						
						pathFound = true;
					} else {
						tempQueue.add(childVertex);
					}
				}
			}
			
			currentVertex.setVisited(true);
		}
		
		return pathFound;
	}
}