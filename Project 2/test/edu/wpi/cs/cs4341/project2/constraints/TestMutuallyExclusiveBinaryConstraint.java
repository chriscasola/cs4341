package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

public class TestMutuallyExclusiveBinaryConstraint {
	protected Bag[] bags;
	protected Item item1;
	protected Item item2;

	@Before
	public void setUp() {
		bags = new Bag[3];
		bags[0] = new Bag('a', 80);
		bags[1] = new Bag('b', 80);
		bags[2] = new Bag('c', 80);
		
		item1 = new Item('A', 10);
		item2 = new Item('B', 10);
	}

	@Test
	public void testSatisfaction() {
		MutuallyExclusiveBinaryConstraint mebConstraint = new MutuallyExclusiveBinaryConstraint(item1, item2, bags[0], bags[1]);

		assertTrue(Satisfaction.NONE == mebConstraint.satisfied());

		item1.setAssignedBag(bags[0]);
		assertTrue(Satisfaction.PARTIAL == mebConstraint.satisfied());
		
		item1.setAssignedBag(null);
		item2.setAssignedBag(bags[1]);
		assertTrue(Satisfaction.PARTIAL == mebConstraint.satisfied());
		
		item1.setAssignedBag(bags[0]);
		item2.setAssignedBag(null);
		item2.setAssignedBag(bags[2]);
		assertTrue(Satisfaction.COMPLETE == mebConstraint.satisfied());
		
		item1.setAssignedBag(null);
		item1.setAssignedBag(bags[2]);
		item2.setAssignedBag(null);
		item2.setAssignedBag(bags[1]);
		assertTrue(Satisfaction.COMPLETE == mebConstraint.satisfied());
		
		item1.setAssignedBag(null);
		item1.setAssignedBag(bags[0]);
		item2.setAssignedBag(null);
		item2.setAssignedBag(bags[1]);
		assertTrue(Satisfaction.BROKEN == mebConstraint.satisfied());
		
		item1.setAssignedBag(null);
		item1.setAssignedBag(bags[1]);
		item2.setAssignedBag(null);
		item2.setAssignedBag(bags[0]);
		assertTrue(Satisfaction.BROKEN == mebConstraint.satisfied());
	}
}
