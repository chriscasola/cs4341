package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

/**
 * Represents an exclusive unary constraint (the item cannot be in the given bags)
 */
public class ExclusiveUnaryConstraint extends UnaryConstraint {

	/**
	 * Constructs a new exclusive unary constraint
	 * @param item the item to constrain
	 * @param bags the bags that this item cannot be in
	 */
	public ExclusiveUnaryConstraint(Item item, Bag[] bags) {
		super(item, bags);
	}


	@Override
	public Satisfaction satisfied() {
		for (int i = 0; i < bags.length; i++) {
			if (item.getAssignedBag() == bags[i]) {
				return Satisfaction.BROKEN;
			}
		}
		return Satisfaction.COMPLETE;
	}

}
