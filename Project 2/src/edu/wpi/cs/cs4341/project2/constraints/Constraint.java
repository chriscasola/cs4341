package edu.wpi.cs.cs4341.project2.constraints;

/**
 * An interface for constraints.
 */
public interface Constraint {
	public enum Satisfaction {
		COMPLETE, PARTIAL, NONE, BROKEN
	}
	
	/**
	 * Returns true if the constraint is satisfied, false otherwise.
	 * 
	 * @return True if the constraint is satisfied, false otherwise.
	 */
	public Satisfaction satisfied();
}
