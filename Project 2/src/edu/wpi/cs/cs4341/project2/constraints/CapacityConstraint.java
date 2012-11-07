package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Item;

/**
 * Represents a capacity constraint. A capacity constraint places limits on how many items may be in each bag.
 */
public class CapacityConstraint implements Constraint {
	/** The minimum number of items that can be in a bag. */
	protected final int minimum;

	/** The maximum number of items that can be in a bag. */
	protected final int maximum;

	/**
	 * Constructs a CapacityConstraint.
	 * 
	 * @param minimum
	 * @param maximum
	 */
	public CapacityConstraint(int minimum, int maximum) {
		if (minimum > maximum) {
			throw new RuntimeException("The value of minimum may not be larger than the value of maximum.");
		}
		
		this.minimum = minimum;
		this.maximum = maximum;
	}

	@Override
	public Satisfaction satisfied() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Returns a new CapacityConstraint created from the given String.
	 * 
	 * @param capacityConstraintString	The String to build a CapacityConstraint from.
	 * 
	 * @return	An Item created using the given String.
	 */
	public static CapacityConstraint fromString(String capacityConstraintString) {
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

			return new CapacityConstraint(minimum, maximum);
		}
	}
}
