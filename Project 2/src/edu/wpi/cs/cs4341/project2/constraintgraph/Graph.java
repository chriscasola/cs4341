package edu.wpi.cs.cs4341.project2.constraintgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.DAItem;
import edu.wpi.cs.cs4341.project2.constraints.BinaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

/**
 * Represents a constraint graph
 */
public class Graph {

	/** The nodes in the graph */
	protected Node[] nodes;
	
	/** The constraints (or edges) in the graph */
	protected List<Constraint> constraints;
	
	/**
	 * Constructs a new graph
	 * @param items the items (or variables/nodes) in the graph
	 * @param bags the starting domain of each item/variable
	 * @param constraints the constraints of the problem (edges)
	 */
	public Graph(DAItem[] items, List<Bag> bags, Constraint[] constraints) {
		
		this.constraints = Arrays.asList(constraints);
		
		// Construct the list of nodes
		nodes = new Node[items.length];
		
		// Construct a node for each item
		for (int i = 0; i < items.length; i++) {
			nodes[i] = new Node(items[i]);
			nodes[i].updateNeighbors(constraints);
		}		
	}
	
	/**
	 * Applies the arc consistency algorithm to this graph
	 * @return true if the result is valid, otherwise false
	 */
	public boolean AC3() {
		
		// Make a safe copy of the constraint list that can be modified
		List<BinaryConstraint> mutConst = new ArrayList<BinaryConstraint>();
		for (Constraint constraint : constraints) {
			if (constraint instanceof BinaryConstraint) {
				mutConst.add((BinaryConstraint)constraint);
			}
		}
		
		// While the queue of constraints is nonempty, apply arc consistency
		while (mutConst.size() > 0) {
			BinaryConstraint currConst = mutConst.remove(0); // get a constraint off the queue
			DAItem leftItem = (DAItem)currConst.getItems()[0]; // get the left and right items involved in the constraint
			DAItem rightItem = (DAItem)currConst.getItems()[1];
			Iterator<Bag> leftBagIterator = leftItem.getDomain().iterator();
			while (leftBagIterator.hasNext()) { // iterate over the domain of the left item
				Bag bag = leftBagIterator.next();
				leftItem.setAssignedBag(bag); // assign the left item to the next value in its domain
				int numBroken = 0;
				for (Bag rBag : rightItem.getDomain()) { // assign the right item to each of it's domain values
					rightItem.setAssignedBag(null);
					rightItem.setAssignedBag(rBag);
					if (currConst.satisfied() == Satisfaction.BROKEN) { // count the number of constraints that break
						numBroken++;
					}
					rightItem.setAssignedBag(null);
				}
				
				// if there are no possible assignments for the right item, remove the associated
				// domain value from the left item
				if (numBroken == rightItem.getDomain().size()) {
					leftBagIterator.remove();
					if (leftItem.getDomain().size() <= 0) { // if the domain is empty, there is a problem, bail
						return false;
					}
					
					// if the domain changed, re-add to the queue all constraints involving the left item
					mutConst.addAll(getOutgoingConstraints(leftItem));
				}
				leftItem.setAssignedBag(null); // undo temporary assignment
			}
		}
		return true;
	}
	
	/**
	 * Returns a list of all of the binary constraints the given item is involved in
	 * @param item the item to evaluate
	 * @return a list of all of the binary constraints the given item is involved in
	 */
	public List<BinaryConstraint> getOutgoingConstraints(DAItem item) {
		List<BinaryConstraint> retVal = new ArrayList<BinaryConstraint>();
		for (Constraint constraint : constraints) {
			if (constraint instanceof BinaryConstraint) {
				if (((BinaryConstraint)constraint).getItems()[0] == item) {
					retVal.add((BinaryConstraint)constraint);
				}
			}
		}
		return retVal;
	}
}
