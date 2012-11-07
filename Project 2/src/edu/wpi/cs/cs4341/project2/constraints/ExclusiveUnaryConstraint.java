package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

/**
 * Represents an exclusive unary constraint in which an Item may not be assigned to certain Bags.
 */
public class ExclusiveUnaryConstraint extends UnaryConstraint {

	/**
	 * Constructs a new ExclusiveUnaryConstraint
	 * 
	 * @param item	The Item to constrain.
	 * @param bags	The Bags that this item may not be in.
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
	
	/**
	 * Returns a new ExclusiveUnaryConstraint created from the given String.
	 * 
	 * @param exclusiveUnaryConstraintString	The String to build a ExclusiveUnaryConstraint from.
	 * @param items								An array of all Items in the Constraint Satisfaction 
	 * 											Problem. This array will be searched to find Items which 
	 * 											have the same id as those specified in the given 
	 * 											equalBinaryConstraintString.
	 * 
	 * @return	A ExclusiveUnaryConstraint created using the given String.
	 */
	public static ExclusiveUnaryConstraint fromString(String exclusiveUnaryConstraintString, Item[] items, Bag[] bags) {
		String[] parameters = exclusiveUnaryConstraintString.split(" ");

		// Ensure there are at least two parameters in the String
		if (parameters.length < 2) {
			throw new RuntimeException("The given String must contain at least two parameters.");
		}
		// Ensure the first parameter is 1 character long
		else if (parameters[0].length() != 1) {
			throw new RuntimeException("The first parameter contained in the given String must be exactly one character long.");
		}
		else {
			Bag[] constraintBags = new Bag[parameters.length-1];
			
			char currentParamBagId;
			for (int i = 1; i < parameters.length; i++) {
				if (parameters[i].length() != 1) {
					throw new RuntimeException("Parameter " + (i+1) + " contained in the given String must be only one character long.");
				}
				
				currentParamBagId = parameters[i].charAt(0);
				
				for (int j = 0; j < bags.length; j++) {
					if (bags[j].getId() == currentParamBagId) {
						constraintBags[i-1] = bags[j];
					}
				}
				
				if (constraintBags[i-1] == null) {
					throw new RuntimeException("No corresponding Bag found for parameter " + (i+1) + ".");
				}
			}
			
			char itemId = parameters[0].charAt(0);
			Item item = null;
			
			for (int i = 0; i < items.length; i++) {
				if (items[i].getId() == itemId) {
					item = items[i];
				}
			}
			
			if (item == null) {
				throw new RuntimeException("No corresponding Item found for first parameter.");
			}

			return new ExclusiveUnaryConstraint(item, constraintBags);
		}
	}
}
