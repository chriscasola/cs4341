package edu.wpi.cs.cs4341.project2.backtrack;

import java.util.Comparator;
import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.DAItem;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.Project2;
import edu.wpi.cs.cs4341.project2.constraintgraph.Graph;
import edu.wpi.cs.cs4341.project2.constraints.BinaryConstraint;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

/**
 * Comparator for bags that uses the least constraining value algorithm
 */
public class BagComparator implements Comparator<Bag> {

	/** The item (variable) to be assigned */
	protected Item currItem;

	/**
	 * Constructs a new comparator
	 * @param currItem the item to be assigned
	 */
	public BagComparator(Item currItem) {
		this.currItem = currItem;
	}

	/**
	 * Implements the least constraining value algorithm
	 * @param o1 the left bag
	 * @param o2 the right bag
	 * @returns -1 if the left bag is preferred, 0 if they are equal, and 1 if the right bag is preferred 
	 */
	@Override
	public int compare(Bag o1, Bag o2) {
		// retrieve the constraint graph
		Graph graph = Project2.getGraph();
		
		// Get the constraints that the current item is involved in (and that are binary)
		List<BinaryConstraint> constraints = graph.getOutgoingConstraints((DAItem)currItem);

		int numChoicesBag1 = 0;
		int numChoicesBag2 = 0;

		// Assign the item to the first bag
		currItem.setAssignedBag(null);
		currItem.setAssignedBag(o1);

		// Check each of the neighboring items to find the size of their
		// domains after the assignment
		for (BinaryConstraint currConst : constraints) {
			DAItem rightItem = (DAItem)currConst.getItems()[1]; // get the other item ("right" item) involved in this constraint
			if (rightItem.getAssignedBag() != null) { // ignore items that have already been assigned
				continue;
			}
			for (Bag rBag : rightItem.getDomain()) { // assign the right item to each of its bags and check the constraint
				rightItem.setAssignedBag(null);
				rightItem.setAssignedBag(rBag);
				if (currConst.satisfied() == Satisfaction.BROKEN) {
					// do nothing
				}
				else {
					numChoicesBag1++; // count the number of assignments that do not break constraints
				}
				rightItem.setAssignedBag(null); // undo the temporary assignment
			}
		}
		
		currItem.setAssignedBag(null);
		currItem.setAssignedBag(o2); // now we check the second bag, same process as above
		
		for (BinaryConstraint currConst : constraints) {
			DAItem rightItem = (DAItem)currConst.getItems()[1];
			if (rightItem.getAssignedBag() != null) {
				continue;
			}
			for (Bag rBag : rightItem.getDomain()) {
				rightItem.setAssignedBag(null);
				rightItem.setAssignedBag(rBag);
				if (currConst.satisfied() == Satisfaction.BROKEN) {
					// do nothing
				}
				else {
					numChoicesBag2++;
				}
				rightItem.setAssignedBag(null);
			}
		}
		
		currItem.setAssignedBag(null); // undo temporary bag assignment
		
		if (numChoicesBag1 > numChoicesBag2) { // bag1 is less constraining
			return -1;
		}
		else if (numChoicesBag1 < numChoicesBag2) { // bag2 is less constraining
			return 1;
		}
		else { // there is a tie
			return 0;
		}
	}

}
