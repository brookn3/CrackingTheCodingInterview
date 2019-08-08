package trees_and_graphs_4_1;

import trees_and_graphs_4_1.Graph;
import trees_and_graphs_4_1.MySolution;
import trees_and_graphs_4_1.Vertex;

/**
 * Runs the solution code.
 * 
 * @author Brook Negussie
 * @version August 8, 2019
 */
public class Main {

	
	public static void main(String[] args) {
		Graph g = new Graph();
		
		Vertex a = new Vertex(0);
		Vertex b = new Vertex(1);
		Vertex c = new Vertex(2);
		Vertex d = new Vertex(3);
		
		g.addVertex(a);
		g.addVertex(b);
		g.addVertex(c);
		g.addVertex(d);
		
		g.addEdge(a, b);
		g.addEdge(b, c);
		g.addEdge(c, a);
		g.addEdge(d, c);
		
		
		System.out.println(g.toString());
		System.out.println();
		
		boolean result = MySolution.bfsPathFound(g, a, c);
				
		System.out.println("Path between the two vertices exists: " + result);
	}
}