package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

public class TestNotEqualBinaryConstraint {
	protected Bag[] bags;
	protected Item item1;
	protected Item item2;

	@Before
	public void setUp() {
		bags = new Bag[2];
		bags[0] = new Bag('a', 80);
		bags[1] = new Bag('b', 80);
		
		item1 = new Item('A', 10);
		item2 = new Item('B', 10);
	}

	@Test
	public void testSatisfaction() {
		NotEqualBinaryConstraint equalBinaryConstraint = new NotEqualBinaryConstraint(item1, item2);

		assertTrue(Satisfaction.NONE == equalBinaryConstraint.satisfied());

		item1.setAssignedBag(bags[0]);
		assertTrue(Satisfaction.PARTIAL == equalBinaryConstraint.satisfied());
		
		item1.setAssignedBag(null);
		item2.setAssignedBag(bags[0]);
		assertTrue(Satisfaction.PARTIAL == equalBinaryConstraint.satisfied());

		item1.setAssignedBag(bags[0]);
		item2.setAssignedBag(null);
		item2.setAssignedBag(bags[1]);
		assertTrue(Satisfaction.COMPLETE == equalBinaryConstraint.satisfied());
		
		item2.setAssignedBag(null);
		item2.setAssignedBag(bags[0]);
		assertTrue(Satisfaction.BROKEN == equalBinaryConstraint.satisfied());
	}
}
