package edu.wpi.cs.cs4341.project2.constraints;

/**
 * An interface for constraints.
 */
public interface Constraint {
	/**
	 * Returns true if the constraint is satisfied, false otherwise.
	 * 
	 * @return True if the constraint is satisfied, false otherwise.
	 */
	public boolean satisfied();
}
