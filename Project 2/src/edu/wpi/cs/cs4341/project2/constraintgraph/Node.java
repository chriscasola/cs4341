package edu.wpi.cs.cs4341.project2.constraintgraph;

import java.util.HashMap;

import edu.wpi.cs.cs4341.project2.DAItem;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.BinaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;

public class Node {

	protected DAItem item;
	
	protected HashMap<Constraint, DAItem> neighbors;
	
	public Node(DAItem item) {
		if (item == null) {
			throw new RuntimeException("item cannot be null!");
		}
		this.item = item;
		this.neighbors = new HashMap<Constraint, DAItem>();
	}
	
	public void updateNeighbors(Constraint[] constraints) {
		Item[] constItems;
		for (int i = 0; i < constraints.length; i++) {
			if (constraints[i] instanceof BinaryConstraint) {
				constItems = ((BinaryConstraint) constraints[i]).getItems();
				if (!(constItems[0] instanceof DAItem && constItems[1] instanceof DAItem)) {
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
