package edu.wpi.cs.connectn.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.connectn.board.Checker;
import edu.wpi.cs.connectn.board.Column;

public class testColumn {
	
	private Column col1, col2, col3;

	@Before
	public void setUp() throws Exception {
		col1 = new Column(5);
		col2 = new Column(1);
		col3 = new Column(30);
	}

	@Test
	public void testConstructor() {
		assertEquals(5, col1.height);
		assertEquals(1, col2.height);
		assertEquals(30, col3.height);
		assertEquals(5, col1.slots.length);
		assertEquals(1, col2.slots.length);
		assertEquals(30, col3.slots.length);
		assertEquals(0, col1.numCheckers);
	}

	@Test
	public void testDropChecker() {
		assertTrue(col1.dropChecker(Checker.RED));
		assertEquals(1, col1.numCheckers);
		assertTrue(col1.dropChecker(Checker.BLACK));
		assertFalse(col1.dropChecker(Checker.EMPTY));
		assertEquals(2, col1.numCheckers);
		assertEquals(Checker.RED, col1.slots[0]);
		assertEquals(Checker.BLACK, col1.slots[1]);
		assertEquals(Checker.EMPTY, col1.slots[2]);
		
		for (int i = 0; i < 3; i++)
			assertTrue(col1.dropChecker(Checker.BLACK));
		
		assertFalse(col1.dropChecker(Checker.RED));
	}
}
