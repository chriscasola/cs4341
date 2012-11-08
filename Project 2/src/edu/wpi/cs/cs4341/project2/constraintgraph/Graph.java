package edu.wpi.cs.cs4341.project2.constraintgraph;

import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.DAItem;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;

public class Graph {

	protected Node[] nodes;
	
	protected Constraint[] constraints;
	
	public Graph(DAItem[] items, List<Bag> bags, Constraint[] constraints) {
		
		this.constraints = constraints;
		
		// Construct the list of nodes
		nodes = new Node[items.length];
		
		// Construct a node for each item
		for (int i = 0; i < items.length; i++) {
			nodes[i] = new Node(items[i]);
			nodes[i].updateNeighbors(constraints);
		}		
	}
	
	
}
