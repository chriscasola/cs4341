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
	
	/**
	 * Returns a new MutuallyExclusiveBinaryConstraint created from the given String.
	 * 
	 * @param mutuallyExclusiveBinaryConstraintString	The String to build a 
	 * 													MutuallyExclusiveBinaryConstraint from.
	 * @param items			An array of all Items in the Constraint Satisfaction Problem. This array will be 
	 * 						searched to find Items which have the same id as the one specified in the given 
	 * 						mutuallyExclusiveBinaryConstraintString.
	 * @param bags			An array of all Bags in the Constraint Satisfaction Problem. This array will be 
	 * 						searched to find Bags which have the same id as the one specified in the given 
	 * 						mutuallyExclusiveBinaryConstraintString.
	 * 
	 * @return	A MutuallyExclusiveBinaryConstraint created using the given String.
	 */
	public static MutuallyExclusiveBinaryConstraint fromString(String mutuallyExclusiveBinaryConstraintString, Item[] items, Bag[] bags) {
		String[] parameters = mutuallyExclusiveBinaryConstraintString.split(" ");

		// Ensure there are at least two parameters in the String
		if (parameters.length < 4) {
			throw new RuntimeException("The given String must contain at least two parameters.");
		}
		// Ensure the first parameter is 1 character long
		else if (parameters[0].length() != 1) {
			throw new RuntimeException("The first parameter contained in the given String must be exactly one character long.");
		}
		// Ensure the second parameter is 1 character long
		else if (parameters[1].length() != 1) {
			throw new RuntimeException("The second parameter contained in the given String must be exactly one character long.");
		}
		// Ensure the third parameter is 1 character long
		else if (parameters[2].length() != 1) {
			throw new RuntimeException("The third parameter contained in the given String must be exactly one character long.");
		}
		// Ensure the fourth parameter is 1 character long
		else if (parameters[3].length() != 1) {
			throw new RuntimeException("The fourth parameter contained in the given String must be exactly one character long.");
		}
		else {
			// Get item1 and item2
			char item1Id = parameters[0].charAt(0);
			char item2Id = parameters[1].charAt(0);
			Item item1 = null;
			Item item2 = null;
			
			for (int i = 0; i < items.length; i++) {
				if (item1Id == items[i].getId()) {
					item1 = items[i];
				}
				if (item2Id == items[i].getId()) {
					item2 = items[i];
				}
			}
			
			if (item1 == null) {
				throw new RuntimeException("Unable to find an Item with id " + item1Id + ".");
			}
			if (item2 == null) {
				throw new RuntimeException("Unable to find an Item with id " + item2Id + ".");
			}
			
			// get bag1 and bag2
			char bag1Id = parameters[2].charAt(0);
			char bag2Id = parameters[3].charAt(0);
			Bag bag1 = null;
			Bag bag2 = null;
			
			for (int i = 0; i < bags.length; i++) {
				if (bag1Id == bags[i].getId()) {
					bag1 = bags[i];
				}
				if (bag2Id == bags[i].getId()) {
					bag2 = bags[i];
				}
			}
			
			if (bag1 == null) {
				throw new RuntimeException("Unable to find an Item with id " + bag1Id + ".");
			}
			if (bag2 == null) {
				throw new RuntimeException("Unable to find an Item with id " + bag2Id + ".");
			}
			
			// Construct and return new MutuallyExclusiveBinaryConstraint
			return new MutuallyExclusiveBinaryConstraint(item1, item2, bag1, bag2);
		}
	}
}
