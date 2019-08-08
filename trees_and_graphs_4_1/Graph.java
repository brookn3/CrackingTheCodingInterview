package trees_and_graphs_4_1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import trees_and_graphs_4_1.Vertex;

/**
 * A directed graph.
 * 
 * @author Brook Negussie
 * @version August 8, 2019
 */
public class Graph {
	
	private Map<Vertex, LinkedList<Vertex>> g;
	
	public Graph() {
		g = new HashMap<Vertex, LinkedList<Vertex>>();
	}
	
	public boolean addVertex(final Vertex theN) {
		boolean isAdded = false;
		
		if (theN != null) {
			if (g.containsKey(theN)) {
				System.out.println("ALERT: The vertex \"" + theN.getData() + 
								"\" was not added because " +
								"it already exists in the Graph object.\n");
			} else {
				g.put(theN, new LinkedList<Vertex>());
				isAdded = true;
			}
		}
		
		return isAdded;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @pre-condition Both of the vertices provided must already
	 * 					reside in side of the graph object.
	 * @param theSrc
	 * @param theDest
	 * @return
	 */
	public boolean addEdge(final Vertex theSrc, final Vertex theDest) {
		boolean isAdded = false;
		
		if (theSrc != null && theDest != null) {
			if (g.containsKey(theSrc) && g.containsKey(theDest)) {
				
				LinkedList<Vertex> temp = g.get(theSrc);
				// Adding to the front to be more time efficient:
				temp.addFirst(theDest);
				g.put(theSrc, temp);
				isAdded = true;
				
			} else if (!g.containsKey(theSrc)) {
				throw new IllegalArgumentException("The given source " +
							"Vertex is not contained in the Graph object.");
			} else {
				throw new IllegalArgumentException("The given destination " +
						"Vertex is not contained in the Graph object.");
			}
		} else {
			throw new NullPointerException("The given parameters " +
								"contain at least one null value.");
		}
		
		return isAdded;
	}
	
	/**
	 * Provides a copy of the vertices in this graph.
	 * 
	 * @return
	 */
	public Set<Vertex> getVertices() {
		
		/* Provides a safe deep copy of the list of vertices to
		 * prevent any anyone from tampering with the data:
		 */ 
		Set<Vertex> vertexSet = g.keySet();
		Set<Vertex> deepCopySet = new HashSet<Vertex>();
		
		for (Vertex currV : vertexSet) {
			Vertex newV = new Vertex(currV.getData());
			if (currV.getVisitedStatus()) {
				newV.setVisited(true);
			}
			deepCopySet.add(newV);
		}
		
		return deepCopySet;
	}
	
	protected Set<Vertex> getActualVertices() {
		
		// *** This method only exists for the purpose of Question 4.1:
		return g.keySet();
	}
	
	protected LinkedList<Vertex> getChildren(final Vertex theV) {
		
		// *** This method only exists for the purpose of Question 4.1:
		return g.get(theV);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		Set<Vertex> s = g.keySet();
		for (Vertex n : s) {
			sb.append(n.getData() + ": --> ");
			
			LinkedList<Vertex> currentChildren = g.get(n);
			for (Vertex childV : currentChildren) {
				sb.append(childV.getData() + "  ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}
}