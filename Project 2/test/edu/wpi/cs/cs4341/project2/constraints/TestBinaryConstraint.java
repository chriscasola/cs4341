package edu.wpi.cs.cs4341.project2.constraints;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.cs4341.project2.Bag;
import edu.wpi.cs.cs4341.project2.Item;

public class TestBinaryConstraint {
	private Item item1;
	private Item item2;

	@Before
	public void setUp() {
		item1 = new Item('A', 10);
		item2 = new Item('B', 10);
	}

	@Test
	public void testConstructorForExceptions() {
		BinaryConstraint constraint;

		// test for null item1
		try {
			constraint = new DummyBinaryConstraint(null, item2);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The parameter item1 must not be null."));
		}

		// test for null item2
		try {
			constraint = new DummyBinaryConstraint(item1, null);
			fail("No exception thrown.");
		}
		catch (RuntimeException e) {
			assertTrue(e.getMessage().equals("The parameter item2 must not be null."));
		}
	}
	
	@Test
	public void testGetItems() {
		BinaryConstraint constraint = new DummyBinaryConstraint(item1, item2);
		
		Item[] items = constraint.getItems();
		
		assertTrue(item1.equals(items[0]));
		assertTrue(item2.equals(items[1]));
	}
	
	@Test
	public void testHasItem() {
		BinaryConstraint constraint = new DummyBinaryConstraint(item1, item2);

		assertTrue(constraint.hasItem(item1));
		assertTrue(constraint.hasItem(item2));
		assertFalse(constraint.hasItem(new Item('C', 10)));
	}
	
	@Test
	public void testHasBag() {
		BinaryConstraint constraint = new DummyBinaryConstraint(item1, item2);
		assertFalse(constraint.hasBag(new Bag('a', 80)));
	}
}