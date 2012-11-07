package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

/**
 * Represents a inclusive unary constraint (an item can only be assigned to one of a specified set of bags)
 */
public class InclusiveUnaryConstraint extends UnaryConstraint {

	/**
	 * Constructs an InclusiveUnaryConstraint
	 * @param item the item to be constrained
	 * @param bags the bags that the item can be assigned to
	 */
	public InclusiveUnaryConstraint(Item item, Bag[] bags) {
		super(item, bags);
	}

	@Override
	public Satisfaction satisfied() {
		if (item.getAssignedBag() == null) {
			return Satisfaction.NONE;
		}
		for (int i = 0; i < bags.length; i++) {
			if (item.getAssignedBag() == bags[i]) {
				return Satisfaction.COMPLETE;
			}
		}
		return Satisfaction.BROKEN;
	}

}
