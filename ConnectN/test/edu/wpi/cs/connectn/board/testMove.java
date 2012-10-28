package edu.wpi.cs.connectn.board;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class testMove {
	@Test
	public void testCreateMoveAndGetters() {
		Move move = new Move(1, Minimax.MAX);
		
		assertEquals(move.getColumn(), 1);
		assertEquals(move.getOwner(), Minimax.MAX);
	}
}
