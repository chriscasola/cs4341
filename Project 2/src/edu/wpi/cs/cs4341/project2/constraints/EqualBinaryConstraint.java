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
	
	/**
	 * Returns a new EqualBinaryConstraint created from the given String.
	 * 
	 * @param equalBinaryConstraintString	The String to build a EqualBinaryConstraint from.
	 * @param items							An array of all Items in the Constraint Satisfaction Problem. 
	 * 										This array will be searched to find Items which have the same id 
	 * 										as those specified in the given equalBinaryConstraintString.
	 * 
	 * @return	A EqualBinaryConstraint created using the given String.
	 */
	public static EqualBinaryConstraint fromString(String equalBinaryConstraintString, Item[] items) {
		String[] parameters = equalBinaryConstraintString.split(" ");

		// Ensure there are only two parameters in the String
		if (parameters.length != 2) {
			throw new RuntimeException("The given String must only contain two parameters.");
		}
		// Ensure the first parameter is at least 1 character long
		else if (parameters[0].length() == 1) {
			throw new RuntimeException("The first parameter contained in the given String must be only one character long.");
		}
		// Ensure the second parameter is at least 1 character long
		else if (parameters[1].length() == 1) {
			throw new RuntimeException("The second parameter contained in the given String must be only one character long.");
		}
		else {
			Item item1 = null;
			Item item2 = null;
			char item1Id = parameters[0].charAt(0);
			char item2Id = parameters[1].charAt(0);
			
			for (int i = 0; i < items.length; i++) {
				if (items[i].getId() == item1Id) {
					item1 = items[i];
				}
				if (items[i].getId() == item2Id) {
					item2 = items[i];
				}
			}
			
			if (item1 == null) {
				throw new RuntimeException("The first Item (id: '" + item1Id + "') could not be found in the given array of Items.");
			}
			if (item2 == null) {
				throw new RuntimeException("The second Item (id: '" + item2Id + "') could not be found in the given array of Items.");
			}

			return new EqualBinaryConstraint(item1, item2);
		}
	}
}
