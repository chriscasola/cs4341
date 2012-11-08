package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

/**
 * Represents a mutually exclusive binary constraint. in which two given items cannot be simultaneously assigned to a given pair of bags.
 */
public class MutuallyExclusiveBinaryConstraint extends BinaryConstraint {
	protected Bag[] bags;
	
	/**
	 * Constructs a MutuallyExclusiveBinaryConstraint.
	 * 
	 * @param item1		One item involved with this constraint.
	 * @param item2		Another item involved with this constraint.
	 * @param bag1		The first of a pair of Bags which may only contain one of the pair of items.
	 * @param bag2		The second of a pair of Bags which may only contain one of the pair of items.
	 */
	public MutuallyExclusiveBinaryConstraint(Item item1, Item item2, Bag bag1, Bag bag2) {
		super(item1, item2);
		
		if (bag1 == null) {
			throw new NullPointerException("The parameter bag1 must not be null.");
		}
		if (bag2 == null) {
			throw new NullPointerException("The parameter bag2 must not be null.");
		}
		
		bags = new Bag[2];
		bags[0] = bag1;
		bags[1] = bag2;
	}

	@Override
	public Satisfaction satisfied() {
		for (int i = 0; i < bags.length; i++) {
			if (item1.getAssignedBag() != null && item1.getAssignedBag().equals(bags[i])) {
				if (item2.getAssignedBag() == null) {
					return Satisfaction.PARTIAL;
				}
				
				for (int j = 0; j < bags.length; j++) {
					if (item2.getAssignedBag().equals(bags[j])) {
						return Satisfaction.BROKEN;
					}
				}
				
				return Satisfaction.COMPLETE;
			}
			if (item2.getAssignedBag() != null && item2.getAssignedBag().equals(bags[i])) {
				if (item1.getAssignedBag() == null) {
					return Satisfaction.PARTIAL;
				}
				
				for (int j = 0; j < bags.length; j++) {
					if (item1.getAssignedBag().equals(bags[j])) {
						return Satisfaction.BROKEN;
					}
				}
				
				return Satisfaction.COMPLETE;
			}
		}
		
		return Satisfaction.NONE;
	}

}
