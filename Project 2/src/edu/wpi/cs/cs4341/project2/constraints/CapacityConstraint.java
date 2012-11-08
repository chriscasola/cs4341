package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

/**
 * Represents a capacity constraint. A capacity constraint places limits on how many items may be in each bag.
 */
public class CapacityConstraint implements Constraint {
	/** The minimum number of items that can be in a bag. */
	protected final int minimum;

	/** The maximum number of items that can be in a bag. */
	protected final int maximum;
	
	/** The Bag for this constraint. */
	protected final Bag bag;
	
	/** A list of all the Items. */
	protected final Item[] items;

	/**
	 * Constructs a CapacityConstraint.
	 * 
	 * @param minimum	The minimum amount of items required to be in a Bag.
	 * @param maximum	The maximum	amount of items that can be in a Bag.
	 * @param bag		The Bag that this CapacityConstraint is for.
	 * @param items		An array of all Items in the Constraint Satisfaction Problem.
	 */
	public CapacityConstraint(int minimum, int maximum, Bag bag, Item[] items) {
		if (minimum > maximum) {
			throw new RuntimeException("The value of minimum may not be larger than the value of maximum.");
		}
		if (bag == null) {
			throw new NullPointerException("The parameter bag must not be null.");
		}
		if (items == null) {
			throw new NullPointerException("The parameter items must not be null.");
		}
		
		this.minimum = minimum;
		this.maximum = maximum;
		this.bag = bag;
		this.items = items;
	}

	@Override
	public Satisfaction satisfied() {
		int numItemsInBag = 0;
		for (int i = 0; i < items.length; i++) {
			if (bag.equals(items[i].getAssignedBag())) {
				numItemsInBag++;
			}
		}
		
		if (numItemsInBag > 0 && numItemsInBag < minimum) {
			return Satisfaction.PARTIAL;
		}
		else if (numItemsInBag >= minimum && numItemsInBag <= maximum) {
			return Satisfaction.COMPLETE;
		}
		else if (numItemsInBag > maximum) {
			return Satisfaction.BROKEN;
		}
		else {
			return Satisfaction.NONE;
		}
	}
	
	@Override
	public boolean hasItem(Item item) {
		for (int i = 0; i < items.length; i++) {
			if (items[i].equals(item)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean hasBag(Bag bag) {
		if (this.bag.equals(bag)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns a new CapacityConstraint created from the given String.
	 * 
	 * @param capacityConstraintString	The String to build a CapacityConstraint from.
	 * @param bag						The Bag that this CapacityConstraint is for.
	 * @param items						An array of all Items in the Constraint Satisfaction Problem.
	 * 
	 * @return	A CapacityConstraint created using the given String.
	 */
	public static CapacityConstraint fromString(String capacityConstraintString, Bag bag, Item[] items) {
		String[] parameters = capacityConstraintString.split(" ");

		// Ensure there are only two parameters in the String
		if (parameters.length != 2) {
			throw new RuntimeException("The given String must only contain two parameters.");
		}
		// Ensure the first parameter is at least 1 character long
		else if (parameters[0].length() < 1) {
			throw new RuntimeException("The first parameter contained in the given String must be at least one character long.");
		}
		// Ensure the second parameter is at least 1 character long
		else if (parameters[1].length() < 1) {
			throw new RuntimeException("The second parameter contained in the given String must be at least one character long.");
		}
		else {
			int minimum = Integer.parseInt(parameters[0]);
			int maximum = Integer.parseInt(parameters[1]);

			return new CapacityConstraint(minimum, maximum, bag, items);
		}
	}
}
