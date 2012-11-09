package edu.wpi.cs.cs4341.project2.backtrack;

import java.util.Iterator;
import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.DAItem;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.Project2;
import edu.wpi.cs.cs4341.project2.constraints.BinaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

public class BacktrackSearchForwardChecking extends BacktrackSearchHeuristic {
	public BacktrackSearchForwardChecking(List<Item> items, List<Bag> bags,
			List<Constraint> constraints) {
		super(items, bags, constraints);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Performs the Backtrack search
	 * @param startNode the node to start at
	 * @return true if a solution has been found, false otherwise
	 */
	@Override
	protected boolean BackTrack(Node startNode) {
		if (items.size() == 0) {
			if (verifyConstraintsComplete()) {
				return true;
			}
			else {
				return false;
			}
		}
		Item currItem = selectUnassignedItem();
		orderBags(); // sort the bags using some algorithm
		for (Bag bag : bags) {
			Node currNode = new Node(bag, currItem, startNode);
			startNode.addChild(currNode);
			currNode.apply();
			items.remove(currItem);
			if (verifyConstraints()) {
				// LOCAL SEARCH
				// for each constraint involving currItem
				for (BinaryConstraint constraint : Project2.getGraph().getOutgoingConstraints((DAItem) currItem)) {
					Item[] items = constraint.getItems();
					// for each item in constraint
					for (int i = 0; i < items.length; i++) {
						// if the item is not currItem
						if (!currItem.equals(items[i])) {
							// for each bag in the current constraint item
							Iterator<Bag> iBags = ((DAItem) items[i]).getDomain().iterator();
							Bag rBag;
							while (iBags.hasNext()) {
								rBag = iBags.next();
								items[i].setAssignedBag(null);
								items[i].setAssignedBag(rBag);
								// remove the bag from the current constraint item's domain if Satisfaction is broken
								if (constraint.satisfied() == Satisfaction.BROKEN) {
									iBags.remove();
								}
								items[i].setAssignedBag(null);
							}
						}
					}
				}
				// END LOCAL SEARCH
				if (BackTrack(currNode)) {
					return true;
				}
			}
			items.add(currNode.revert()); // constraints violated, revert the assignment
		}
		return false; // failure
	}
}
