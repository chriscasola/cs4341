package edu.wpi.cs.cs4341.project2.constraints;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

/**
 * DummyUnaryConstraint for testing.
 */
public class DummyUnaryConstraint extends UnaryConstraint {

	public DummyUnaryConstraint(Item item, Bag[] bags) {
		super(item, bags);
	}

	@Override
	public Satisfaction satisfied() {
		// TODO Auto-generated method stub
		return null;
	}

}
