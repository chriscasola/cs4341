package edu.wpi.cs.connectn.tree;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.wpi.cs.connectn.tree.Move;
import edu.wpi.cs.connectn.board.Minimax;

public class testMove {
	@Test
	public void testCreateMoveAndGetters() {
		Move move = new Move(1, Minimax.MAX);
		
		assertEquals(move.getColumn(), 1);
		assertEquals(move.getOwner(), Minimax.MAX);
	}
}
