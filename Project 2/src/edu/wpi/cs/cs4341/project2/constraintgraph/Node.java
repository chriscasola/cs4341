package edu.wpi.cs.cs4341.project2.constraintgraph;

import java.util.HashMap;

import edu.wpi.cs.cs4341.project2.DAItem;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.BinaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;

/**
 * Represents a node in a constraint graph
 */
public class Node {

	/** The item the node represents */
	protected DAItem item;
	
	/** The edges leaving this node */
	protected HashMap<Constraint, DAItem> neighbors;
	
	/**
	 * Constructs a new node
	 * @param item the item the node should represent
	 */
	public Node(DAItem item) {
		if (item == null) {
			throw new RuntimeException("item cannot be null!");
		}
		this.item = item;
		this.neighbors = new HashMap<Constraint, DAItem>();
	}
	
	/**
	 * Updates the list of edges leaving this node based on the list
	 * of all constraints in the problem
	 * @param constraints a list of all the constraints in this problem
	 */
	public void updateNeighbors(Constraint[] constraints) {
		Item[] constItems;
		for (int i = 0; i < constraints.length; i++) {
			if (constraints[i] instanceof BinaryConstraint) { // look at every binary constraint
				constItems = ((BinaryConstraint) constraints[i]).getItems(); // get the items involved
				if (!(constItems[0] instanceof DAItem && constItems[1] instanceof DAItem)) { // verify this is a DAItem (and therefore has a domain field)
					throw new RuntimeException("Items must be of type DAItem!");
				}
				if (constItems[0] == item) {
					neighbors.put(constraints[i], (DAItem)constItems[1]);
				}
				else if (constItems[1] == item) {
					neighbors.put(constraints[i], (DAItem)constItems[0]);
				}
			}
		}
	}
}
