package edu.wpi.cs.connectn.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.connectn.board.Board;
import edu.wpi.cs.connectn.board.Minimax;
import edu.wpi.cs.connectn.tree.Move;

public class testBoard {

	Board b1;

	@Before
	public void setUp() throws Exception {
		b1 = new Board(5, 6, 3);
	}

	@Test
	public void testDropChecker() {
		try {
			b1.doMove(new Move(0, Minimax.MAX));
			b1.doMove(new Move(0, Minimax.MAX));
			b1.doMove(new Move(0, Minimax.MAX));

			b1.doMove(new Move(3, Minimax.MIN));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MAX));
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Exception message: " + e.getMessage());
		}
		assertTrue(b1.toString().equals("------\n------\nX--X--\nX--X--\nX--O--\n"));
	}

	@Test
	public void testCheckForWin1() {
		try {
			b1.doMove(new Move(2, Minimax.MAX));
			b1.doMove(new Move(2, Minimax.MAX));
			b1.doMove(new Move(2, Minimax.MAX));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}

		assertEquals(GameState.MAX, b1.checkForWin());

		try {
			b1.doMove(new Move(2, Minimax.MAX));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}

		assertEquals(GameState.MAX, b1.checkForWin());

	}

	@Test
	public void testCheckForWin2() {
		try {
			b1.doMove(new Move(0, Minimax.MIN));
			b1.doMove(new Move(1, Minimax.MAX));
			b1.doMove(new Move(2, Minimax.MIN));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}

		assertEquals(GameState.IN_PROGRESS, b1.checkForWin());

		try {
			b1.doMove(new Move(0, Minimax.MIN));
			b1.doMove(new Move(1, Minimax.MIN));
			b1.doMove(new Move(2, Minimax.MIN));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}

		assertEquals(GameState.MIN, b1.checkForWin());
	}

	@Test
	public void testCheckForWin3() {
		try {
			b1.doMove(new Move(0, Minimax.MIN));
			b1.doMove(new Move(0, Minimax.MAX));
			b1.doMove(new Move(0, Minimax.MIN));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}

		assertEquals(GameState.IN_PROGRESS, b1.checkForWin());

		try {
			b1.doMove(new Move(1, Minimax.MAX));
			b1.doMove(new Move(1, Minimax.MIN));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}

		assertEquals(GameState.IN_PROGRESS, b1.checkForWin());

		try {
			b1.doMove(new Move(2, Minimax.MIN));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}

		assertEquals(GameState.MIN, b1.checkForWin());
	}

	@Test
	public void testCheckRLDiagonal() {
		try {
			b1.doMove(new Move(0, Minimax.MAX));
			b1.doMove(new Move(0, Minimax.MAX));
			b1.doMove(new Move(0, Minimax.MAX));

			b1.doMove(new Move(3, Minimax.MIN));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MIN));

			b1.doMove(new Move(2, Minimax.MAX));
			b1.doMove(new Move(2, Minimax.MAX));
			b1.doMove(new Move(2, Minimax.MAX));

			b1.doMove(new Move(4, Minimax.MAX));
			b1.doMove(new Move(4, Minimax.MAX));
			b1.doMove(new Move(4, Minimax.MAX));
			b1.doMove(new Move(4, Minimax.MAX));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}
		assertEquals(Minimax.EMPTY, b1.checkRLDiagonal(4, 4));

		try {
			b1.doMove(new Move(4, Minimax.MAX));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}
		assertEquals(Minimax.MAX, b1.checkRLDiagonal(4, 4));
	}

	@Test
	public void testCheckLRDiagonal() {
		try {
			b1.doMove(new Move(0, Minimax.MAX));
			b1.doMove(new Move(0, Minimax.MAX));
			b1.doMove(new Move(0, Minimax.MAX));

			b1.doMove(new Move(3, Minimax.MIN));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MIN));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}

		assertEquals(Minimax.EMPTY, b1.checkLRDiagonal(2, 0));

		try {
			b1.doMove(new Move(1, Minimax.MAX));
			b1.doMove(new Move(1, Minimax.MAX));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}

		assertEquals(Minimax.EMPTY, b1.checkLRDiagonal(2, 0));

		try {
			b1.doMove(new Move(2, Minimax.MAX));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}
		assertEquals(Minimax.MAX, b1.checkLRDiagonal(2, 0));

		try {
			b1.doMove(new Move(2, Minimax.MAX));
			b1.doMove(new Move(2, Minimax.MAX));
			b1.doMove(new Move(2, Minimax.MAX));
			b1.doMove(new Move(2, Minimax.MAX));

			b1.doMove(new Move(1, Minimax.MAX));
			b1.doMove(new Move(1, Minimax.MAX));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}
		assertEquals(Minimax.EMPTY, b1.checkLRDiagonal(4, 1));

		try {
			b1.doMove(new Move(1, Minimax.MAX));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}
		assertEquals(Minimax.MAX, b1.checkLRDiagonal(4, 1));



	}

	@Test
	public void testCheckHorizontal() {
		try {
			b1.doMove(new Move(0, Minimax.MAX));
			b1.doMove(new Move(0, Minimax.MAX));
			b1.doMove(new Move(0, Minimax.MAX));

			b1.doMove(new Move(3, Minimax.MIN));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MIN));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}

		assertEquals(Minimax.EMPTY, b1.checkHorizontal(0));
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(1));
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(2));
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(3));
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(4));

		try {
			b1.doMove(new Move(1, Minimax.MAX));
			b1.doMove(new Move(2, Minimax.MIN));
			b1.doMove(new Move(2, Minimax.MAX));
			b1.doMove(new Move(4, Minimax.MAX));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}

		assertEquals(Minimax.EMPTY, b1.checkHorizontal(0));
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(1));

		try {
			b1.doMove(new Move(4, Minimax.MAX));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}
		assertEquals(Minimax.MAX, b1.checkHorizontal(1));
	}

	@Test
	public void testCheckVertical() {
		try {
			b1.doMove(new Move(0, Minimax.MAX));
			b1.doMove(new Move(0, Minimax.MAX));
			b1.doMove(new Move(0, Minimax.MAX));

			b1.doMove(new Move(3, Minimax.MIN));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MAX));
			b1.doMove(new Move(3, Minimax.MIN));
		}
		catch (Exception e) {
			fail("Exception message: " + e.getMessage());
		}
		assertEquals(Minimax.MAX, b1.checkVertical(0));
		assertEquals(Minimax.EMPTY, b1.checkVertical(1));
		assertEquals(Minimax.EMPTY, b1.checkVertical(2));
		assertEquals(Minimax.MAX, b1.checkVertical(3));
		assertEquals(Minimax.EMPTY, b1.checkVertical(4));
		assertEquals(Minimax.EMPTY, b1.checkVertical(5));
	}
}
