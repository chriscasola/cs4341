package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Item;

/**
 * For testing BinaryConstraint methods.
 */
public class DummyBinaryConstraint extends BinaryConstraint {

	public DummyBinaryConstraint(Item item1, Item item2) {
		super(item1, item2);
	}

	@Override
	public Satisfaction satisfied() {
		// TODO Auto-generated method stub
		return null;
	}
}
