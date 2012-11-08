package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

/**
 * Represents a weight constraint (all bags must be at 90% capacity, but not over 100%)
 */
public class WeightConstraint implements Constraint {
	
	/** The bag this constraint applies to */
	protected Bag bag;
	
	/** The list of all items in this problem */
	protected Item[] items;
	
	/**
	 * Constructs a new WeightConstraint for the given bag
	 * @param bag the bag to constrain
	 * @param items a list of all the items in the problem
	 */
	public WeightConstraint(Bag bag, Item[] items) {
		if (bag == null) {
			throw new NullPointerException("The parameter bag must not be null.");
		}
		if (items == null) {
			throw new NullPointerException("The parameter items must not be null.");
		}
		for (int i = 0; i < items.length; i++) {
			if (items[i] == null) {
				throw new NullPointerException("The parameter items is null at index " + i + ".");
			}
		}
		
		this.bag = bag;
		this.items = items;
	}

	/**
	 * Returns the satisfaction state of this constraint
	 */
	@Override
	public Satisfaction satisfied() {
		int weight = 0;
		for (int i = 0; i < items.length; i++) {
			if (items[i].getAssignedBag() == bag) {
				weight += items[i].getWeight();
			}
		}
		
		float capacity = (weight * 1f) / bag.getWeightCapacity();
		
		if (capacity == 0f) { // The bag is empty
			return Satisfaction.NONE;
		}
		else if (capacity < .9f) { // The bag is less than 90% full
			return Satisfaction.PARTIAL;
		}
		else if (capacity <= 1f) { // The bag is between 90% and 100% full, inclusive
			return Satisfaction.COMPLETE;
		}
		else { // The bag is more than 100% full
			return Satisfaction.BROKEN;
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
}
