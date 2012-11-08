package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

public class TestExclusiveUnaryConstraint {

	protected Bag[] bags;
	protected Bag bag1;
	protected Bag bag2;
	protected Bag bag3;
	protected Bag bag4;
	protected Item item1;
	protected ExclusiveUnaryConstraint euc1;

	@Before
	public void setUp() throws Exception {
		bags = new Bag[4];
		bags[0] = new Bag('a', 10);
		bags[1] = new Bag('b', 10);
		bags[2] = new Bag('c', 10);
		bags[3] = new Bag('d', 10);
		bag1 = bags[0];
		bag2 = bags[1];
		bag3 = bags[2];
		bag4 = bags[3];
		item1 = new Item('A', 3);
	}

	@Test
	public void test() {
		euc1 = new ExclusiveUnaryConstraint(item1, new Bag[]{bag3, bag4});
		assertTrue(euc1.satisfied() == Satisfaction.COMPLETE);

		item1.setAssignedBag(bag2);
		assertTrue(euc1.satisfied() == Satisfaction.COMPLETE);

		item1.setAssignedBag(null);
		item1.setAssignedBag(bag3);
		assertTrue(euc1.satisfied() == Satisfaction.BROKEN);
	}

	@Test
	public void testFromString() {
		Item[] items = new Item[2];
		items[0] = item1;
		items[1] = new Item('Z', 10);

		ExclusiveUnaryConstraint constraint = ExclusiveUnaryConstraint.fromString("A a b", items, bags);

		assertTrue(items[0].equals(constraint.item));
		assertTrue(bags[0].equals(constraint.bags[0]));
		assertTrue(bags[1].equals(constraint.bags[1]));

		// test for incorrect parameter quantity Exceptions
		try {
			constraint = ExclusiveUnaryConstraint.fromString("A", items, bags);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The given String must contain at least two parameters."));
		}

		// test for first parameter too long Exception
		try {
			constraint = ExclusiveUnaryConstraint.fromString("AA a", items, bags);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The first parameter contained in the given String must be exactly one character long."));
		}

		// test for post first parameter too long Exception
		try {
			constraint = ExclusiveUnaryConstraint.fromString("A aa", items, bags);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("Parameter 2 contained in the given String must be only one character long."));
		}

		// test for no corresponding Bag found Exception
		try {
			constraint = ExclusiveUnaryConstraint.fromString("A z", items, bags);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("No corresponding Bag found for parameter 2."));
		}

		// test for no corresponding Item found Exception
		try {
			constraint = ExclusiveUnaryConstraint.fromString("X a", items, bags);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("No corresponding Item found for first parameter."));
		}
	}
}
