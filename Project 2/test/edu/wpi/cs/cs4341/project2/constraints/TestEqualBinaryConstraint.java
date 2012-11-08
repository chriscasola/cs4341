package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

public class TestEqualBinaryConstraint {
	protected Bag[] bags;
	protected Item[] items;
	protected Item item1;
	protected Item item2;

	@Before
	public void setUp() {
		bags = new Bag[2];
		bags[0] = new Bag('a', 80);
		bags[1] = new Bag('b', 80);

		items = new Item[3];
		items[0] = new Item('A', 10);
		items[1] = new Item('B', 10);
		items[2] = new Item('C', 10);

		item1 = items[0];
		item2 = items[1];
	}

	@Test
	public void testSatisfaction() {
		EqualBinaryConstraint equalBinaryConstraint = new EqualBinaryConstraint(item1, item2);

		assertTrue(Satisfaction.NONE == equalBinaryConstraint.satisfied());

		item1.setAssignedBag(bags[0]);
		assertTrue(Satisfaction.PARTIAL == equalBinaryConstraint.satisfied());

		item1.setAssignedBag(null);
		item2.setAssignedBag(bags[0]);
		assertTrue(Satisfaction.PARTIAL == equalBinaryConstraint.satisfied());

		item1.setAssignedBag(bags[0]);
		assertTrue(Satisfaction.COMPLETE == equalBinaryConstraint.satisfied());

		item2.setAssignedBag(null);
		item2.setAssignedBag(bags[1]);
		assertTrue(Satisfaction.BROKEN == equalBinaryConstraint.satisfied());
	}

	@Test
	public void testFromString() {
		EqualBinaryConstraint constraint = EqualBinaryConstraint.fromString("A B", items);

		assertTrue(items[0].equals(constraint.item1));
		assertTrue(items[1].equals(constraint.item2));

		// test for incorrect parameter quantity Exceptions
		try {
			constraint = EqualBinaryConstraint.fromString("", items);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The given String must contain exactly two parameters."));
		}

		try {
			constraint = EqualBinaryConstraint.fromString("A", items);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The given String must contain exactly two parameters."));
		}

		try {
			constraint = EqualBinaryConstraint.fromString("A B C", items);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The given String must contain exactly two parameters."));
		}

		// test for empty first param
		try {
			constraint = EqualBinaryConstraint.fromString(" B", items);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The first parameter contained in the given String must be exactly one character long."));
		}

		// test for array of items with no items[0]
		try {
			constraint = EqualBinaryConstraint.fromString("A B", new Item[] {items[1]});
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The first Item (id: 'A') could not be found in the given array of Items."));
		}

		// test for array of items with no items[1]
		try {
			constraint = EqualBinaryConstraint.fromString("A B", new Item[] {items[0]});
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The second Item (id: 'B') could not be found in the given array of Items."));
		}
	}
}
