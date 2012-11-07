package edu.wpi.cs.cs4341.project2.constraints;

import java.util.List;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

/**
 * Represents a weight constraint (all bags must be at 90% capacity, but not over 100%)
 */
public class WeightConstraint implements Constraint {
	
	/** The bag this constraint applies to */
	protected Bag bag;
	
	/** The list of all items in this problem */
	protected List<Item> items;
	
	/**
	 * Constructs a new WeightConstraint for the given bag
	 * @param bag the bag to constrain
	 * @param items a list of all the items in the problem
	 */
	public WeightConstraint(Bag bag, List<Item> items) {
		this.bag = bag;
		this.items = items;
	}

	/**
	 * Returns the satisfaction state of this constraint
	 */
	@Override
	public Satisfaction satisfied() {
		int weight = 0;
		for (Item item : items) {
			if (item.getAssignedBag() == bag) {
				weight += item.getWeight();
			}
		}
		
		float capacity = (weight * 1f) / bag.getWeightCapacity();
		
		if (capacity == 0) { // The bag is empty
			return Satisfaction.NONE;
		}
		else if (capacity < .9) { // The bag is less than 90% full
			return Satisfaction.PARTIAL;
		}
		else if (capacity <= 1) { // The bag is between 90% and 100% full, inclusive
			return Satisfaction.COMPLETE;
		}
		else { // The bag is more than 100% full
			return Satisfaction.BROKEN;
		}
	}

}
