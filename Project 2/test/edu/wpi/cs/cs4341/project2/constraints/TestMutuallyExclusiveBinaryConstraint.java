package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;
import edu.wpi.cs.cs4341.project2.constraints.Constraint.Satisfaction;

public class TestMutuallyExclusiveBinaryConstraint {
	protected Bag[] bags;
	protected Item[] items;
	protected Item item1;
	protected Item item2;

	@Before
	public void setUp() {
		bags = new Bag[3];
		bags[0] = new Bag('a', 80);
		bags[1] = new Bag('b', 80);
		bags[2] = new Bag('c', 80);
		
		items = new Item[2];
		items[0] = new Item('A', 10);
		items[1] = new Item('B', 10);
		item1 = items[0];
		item2 = items[1];
	}
	
	@Test
	public void testConstructorExceptions() {
		try {
			MutuallyExclusiveBinaryConstraint constraint = new MutuallyExclusiveBinaryConstraint(item1, item2, null, bags[1]);
			fail("Exception not thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The parameter bag1 must not be null."));
		}
		
		try {
			MutuallyExclusiveBinaryConstraint constraint = new MutuallyExclusiveBinaryConstraint(item1, item2, bags[0], null);
			fail("Exception not thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The parameter bag2 must not be null."));
		}
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
	
	@Test
	public void testHasBag() {
		MutuallyExclusiveBinaryConstraint constraint = MutuallyExclusiveBinaryConstraint.fromString("A B a b", items, bags);
		
		assertTrue(constraint.hasBag(bags[0]));
		assertTrue(constraint.hasBag(bags[1]));
		assertFalse(constraint.hasBag(bags[2]));
	}
	
	@Test
	public void testFromString() {
		MutuallyExclusiveBinaryConstraint constraint = MutuallyExclusiveBinaryConstraint.fromString("A B a b", items, bags);
		assertTrue(items[0].equals(constraint.item1));
		assertTrue(items[1].equals(constraint.item2));
		assertTrue(bags[0].equals(constraint.bags[0]));
		assertTrue(bags[1].equals(constraint.bags[1]));
		
		// test for less than four parameters exception
		try {
			constraint = MutuallyExclusiveBinaryConstraint.fromString("A B a", items, bags);
			fail("Exception not thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The given String must contain at least four parameters."));
		}
		
		// test for parameter length exceptions
		try {
			constraint = MutuallyExclusiveBinaryConstraint.fromString("AA B a b", items, bags);
			fail("Exception not thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The first parameter contained in the given String must be exactly one character long."));
		}
		
		try {
			constraint = MutuallyExclusiveBinaryConstraint.fromString("A BB a b", items, bags);
			fail("Exception not thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The second parameter contained in the given String must be exactly one character long."));
		}
		
		try {
			constraint = MutuallyExclusiveBinaryConstraint.fromString("A B aa b", items, bags);
			fail("Exception not thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The third parameter contained in the given String must be exactly one character long."));
		}
		
		try {
			constraint = MutuallyExclusiveBinaryConstraint.fromString("A B a bb", items, bags);
			fail("Exception not thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The fourth parameter contained in the given String must be exactly one character long."));
		}
		
		// test for nonexistent item
		try {
			constraint = MutuallyExclusiveBinaryConstraint.fromString("Z B a b", items, bags);
			fail("Exception not thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("Unable to find an Item with id Z."));
		}
		
		try {
			constraint = MutuallyExclusiveBinaryConstraint.fromString("A Z a b", items, bags);
			fail("Exception not thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("Unable to find an Item with id Z."));
		}
		
		// test for nonexistent bag
		try {
			constraint = MutuallyExclusiveBinaryConstraint.fromString("A B z b", items, bags);
			fail("Exception not thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("Unable to find an Item with id z."));
		}
		
		try {
			constraint = MutuallyExclusiveBinaryConstraint.fromString("A B a z", items, bags);
			fail("Exception not thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("Unable to find an Item with id z."));
		}
	}
}
