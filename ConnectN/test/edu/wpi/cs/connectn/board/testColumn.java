package edu.wpi.cs.connectn.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.connectn.board.Minimax;
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
		assertEquals(0, col1.numPieces);
	}

	@Test
	public void testdropPiece() {
		try {
			col1.dropPiece(Minimax.MIN);
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}
		assertEquals(1, col1.numPieces);
		try {
			col1.dropPiece(Minimax.MAX);
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}
		try {
			col1.dropPiece(Minimax.EMPTY);
		}
		catch (RuntimeException e) {
			assertTrue("Column is already full.".equals(e.getMessage()));
		}
		assertEquals(2, col1.numPieces);
		assertEquals(Minimax.MIN, col1.slots[0]);
		assertEquals(Minimax.MAX, col1.slots[1]);
		assertEquals(Minimax.EMPTY, col1.slots[2]);
		
		for (int i = 0; i < 3; i++) {
			try {
				col1.dropPiece(Minimax.MAX);
			}
			catch (Exception e) {
				fail("Received exception when i = " + i + ". Exception message: " + e.getMessage());
			}
		}
		
		try {
			col1.dropPiece(Minimax.MIN);
		}
		catch (RuntimeException e) {
			assertTrue("Column is already full.".equals(e.getMessage()));
		}
	}
}
