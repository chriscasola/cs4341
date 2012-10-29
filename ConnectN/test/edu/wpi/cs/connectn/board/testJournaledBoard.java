package edu.wpi.cs.connectn.board;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.connectn.tree.Move;

public class testJournaledBoard {

	private JournaledBoard board;

	@Before
	public void setUp() throws Exception {
		board = new JournaledBoard(5, 6, 3);
	}

	@Test
	public void testRevert() {
		try {
			board.doMove(new Move(1, Minimax.MAX));
			board.doMove(new Move(1, Minimax.MAX));
			board.doMove(new Move(1, Minimax.MAX));
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception message: " + e.getMessage());
		}

		assertTrue("------\n------\n-X----\n-X----\n-X----\n".equals(board.toString()));
		
		board.revert(2);

		assertTrue("------\n------\n------\n------\n-X----\n".equals(board.toString()));
		
		board.revert();
		
		assertTrue("------\n------\n------\n------\n------\n".equals(board.toString()));
	}
	
	@Test
	public void testRevertTooManyMoves() {
		try {
			board.doMove(new Move(1, Minimax.MAX));
			board.doMove(new Move(1, Minimax.MAX));
			board.doMove(new Move(1, Minimax.MAX));
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception message: " + e.getMessage());
		}

		assertTrue("------\n------\n-X----\n-X----\n-X----\n".equals(board.toString()));
		
		try {
			board.revert(4);
		}
		catch (Exception e) {
			assertTrue("There are not enough moves to revert.".equals(e.getMessage()));
		}
	}
}
