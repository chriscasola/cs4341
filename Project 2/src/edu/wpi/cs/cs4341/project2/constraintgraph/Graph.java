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

public class Graph {

	protected Node[] nodes;
	
	protected List<Constraint> constraints;
	
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
	
	public boolean AC3() {
		List<BinaryConstraint> mutConst = new ArrayList<BinaryConstraint>();
		for (Constraint constraint : constraints) {
			if (constraint instanceof BinaryConstraint) {
				mutConst.add((BinaryConstraint)constraint);
			}
		}
		
		while (mutConst.size() > 0) {
			BinaryConstraint currConst = mutConst.remove(0);
			DAItem leftItem = (DAItem)currConst.getItems()[0];
			DAItem rightItem = (DAItem)currConst.getItems()[1];
			Iterator<Bag> leftBagIterator = leftItem.getDomain().iterator();
			while (leftBagIterator.hasNext()) {
				Bag bag = leftBagIterator.next();
				leftItem.setAssignedBag(bag);
				int numBroken = 0;
				for (Bag rBag : rightItem.getDomain()) {
					rightItem.setAssignedBag(null);
					rightItem.setAssignedBag(rBag);
					if (currConst.satisfied() == Satisfaction.BROKEN) {
						numBroken++;
					}
					rightItem.setAssignedBag(null);
				}
				if (numBroken == rightItem.getDomain().size()) {
					leftBagIterator.remove();
					if (leftItem.getDomain().size() <= 0) {
						return false;
					}
					mutConst.addAll(getOutgoingConstraints(leftItem));
				}
				leftItem.setAssignedBag(null);
			}
		}
		
		return true;
	}
	
	protected List<BinaryConstraint> getOutgoingConstraints(DAItem item) {
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
