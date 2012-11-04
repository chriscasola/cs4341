package edu.wpi.cs.cs4341.project2.constraints;
//TODO
public class CapacityConstraint extends Constraint {
	protected final int minimum;
	protected final int maximum;
	
	// TODO
	public CapacityConstraint(int minimum, int maximum) {
		this.minimum = minimum;
		this.maximum = maximum;
	}
}
