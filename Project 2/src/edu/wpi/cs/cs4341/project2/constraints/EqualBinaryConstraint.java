package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Item;

/**
 * Represents an equal binary constraint. This constraint requires that two specific Items are not assigned to the same Bag.
 */
public class EqualBinaryConstraint extends BinaryConstraint {
	/**
	 * Constructs an EqualBinaryConstraint.
	 * 
	 * @param item1		One item involved with this constraint.
	 * @param item2		Another item involved with this constraint.
	 */
	public EqualBinaryConstraint(Item item1, Item item2) {
		super(item1, item2);
	}

	@Override
	public Satisfaction satisfied() {
		if (item1.getAssignedBag() == null && item2.getAssignedBag() == null) {
			return Satisfaction.NONE;
		}
		else if (item1.getAssignedBag() == null ^ item2.getAssignedBag() == null) {
			return Satisfaction.PARTIAL;
		}
		else if (item1.getAssignedBag().equals(item2.getAssignedBag())) {
			return Satisfaction.COMPLETE;
		}
		else {
			return Satisfaction.BROKEN;
		}
	}

}
