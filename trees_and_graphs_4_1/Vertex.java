package trees_and_graphs_4_1;

/**
 * Defines the vertex object used to build up a Graph.
 * 
 * @author Brook Negussie
 * @version August 8, 2019
 */
public class Vertex {
	
	private int data;
	private boolean visited;
	
	public Vertex(final int theData) {
		data = theData;
		visited = false;
	}
	
	public int getData() {
		return data;
	}
	
	public boolean getVisitedStatus() {
		return visited;
	}
	
	public boolean setVisited(final boolean theStatus) {
		visited = theStatus;
		
		return true;
	}
	
	/**
	 * A simple equals method.
	 * 
	 * @param obj Object being compared with.
	 * @return true if the current vertex is equal to the vertex passed in.
	 */
	@Override
	public boolean equals(Object obj) {
		boolean equality = false;
		
		if (obj != null && obj instanceof Vertex) {
			Vertex otherVertex = (Vertex) obj;
			
			// Checking all attributes:
			if (this.data == otherVertex.data && 
							this.visited == otherVertex.visited) {
				
				equality = true;
			}
		}
		
		return equality;
	}
	
	@Override
	public int hashCode() {
		// Initially using a prime non-zero number:
		int hash = 7; 
		
		/* Building on top of initial hash based off of each field
		 * being used to calculate the equality of two instances:
		 */
		hash = 31 * hash + data;
		hash = 31 * hash + (visited ? 0 : 1);
		
		
		return hash;
	}
}