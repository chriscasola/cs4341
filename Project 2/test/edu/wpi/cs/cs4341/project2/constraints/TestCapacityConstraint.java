package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

public class TestCapacityConstraint {
	protected Bag bag;
	protected Item[] items;

	@Before
	public void setUp() {
		bag = new Bag('a', 80);
		items = new Item[4];
		items[0] = new Item('A', 10);
		items[1] = new Item('B', 10);
		items[2] = new Item('C', 10);
		items[3] = new Item('D', 10);
	}

	@Test
	public void testConstructorForExceptions() {
		CapacityConstraint constraint;

		// test for minimum parameter greater than maximum parameter exception
		try {
			constraint = new CapacityConstraint(3, 1, bag, items);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The value of minimum may not be larger than the value of maximum."));
		}

		// test for bag parameter null exception
		try {
			constraint = new CapacityConstraint(1, 3, null, items);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The parameter bag must not be null."));
		}

		// test for items parameter null exception
		try {
			constraint = new CapacityConstraint(3, 3, bag, null);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The parameter items must not be null."));
		}
	}

	@Test
	public void testSatisfaction() {
		CapacityConstraint capacityConstraint = new CapacityConstraint(2, 3, bag, items);

		assertTrue(Satisfaction.NONE == capacityConstraint.satisfied());

		items[0].setAssignedBag(bag);
		assertTrue(Satisfaction.PARTIAL == capacityConstraint.satisfied());

		items[1].setAssignedBag(bag);
		assertTrue(Satisfaction.COMPLETE == capacityConstraint.satisfied());

		items[2].setAssignedBag(bag);
		assertTrue(Satisfaction.COMPLETE == capacityConstraint.satisfied());

		items[3].setAssignedBag(bag);
		assertTrue(Satisfaction.BROKEN == capacityConstraint.satisfied());
	}
	
	@Test
	public void testHasItem() {
		CapacityConstraint constraint = new CapacityConstraint(1, 3, bag, items);
		
		assertTrue(constraint.hasItem(items[0]));
		assertTrue(constraint.hasItem(items[1]));
		assertTrue(constraint.hasItem(items[2]));
		assertTrue(constraint.hasItem(items[3]));
		
		assertFalse(constraint.hasItem(new Item('Z', 10)));
	}
	
	@Test
	public void testHasBag() {
		CapacityConstraint constraint = new CapacityConstraint(1, 3, bag, items);
		
		assertTrue(constraint.hasBag(bag));
		
		assertFalse(constraint.hasBag(new Bag('z', 80)));
	}

	@Test
	public void testFromString() {
		CapacityConstraint constraint = CapacityConstraint.fromString("1 3", bag, items);

		assertEquals(1, constraint.minimum);
		assertEquals(3, constraint.maximum);

		// test for incorrect parameter quantity Exceptions
		try {
			constraint = CapacityConstraint.fromString("", bag, items);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The given String must contain exactly two parameters."));
		}

		try {
			constraint = CapacityConstraint.fromString("1", bag, items);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The given String must contain exactly two parameters."));
		}

		try {
			constraint = CapacityConstraint.fromString("1 2 3", bag, items);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The given String must contain exactly two parameters."));
		}

		// test for empty first param
		try {
			constraint = CapacityConstraint.fromString(" 1", bag, items);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The first parameter contained in the given String must be at least one character long."));
		}
	}
}
