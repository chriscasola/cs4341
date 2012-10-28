package edu.wpi.cs.connectn.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.connectn.board.Board;
import edu.wpi.cs.connectn.board.Minimax;

public class testBoard {
	
	Board b1;

	@Before
	public void setUp() throws Exception {
		b1 = new Board(5, 6, 3);
	}

	@Test
	public void testDropChecker() {
		b1.dropPiece(Minimax.MAX, 0);
		b1.dropPiece(Minimax.MAX, 0);
		b1.dropPiece(Minimax.MAX, 0);
		
		b1.dropPiece(Minimax.MIN, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MAX, 3);
		assertTrue(b1.toString().equals("------\n------\nX--X--\nX--X--\nX--O--\n"));
	}
	
	@Test
	public void testCheckForWin1() {
		b1.dropPiece(Minimax.MAX, 2);
		b1.dropPiece(Minimax.MAX, 2);
		
		assertEquals(-1, b1.checkForWin());
		
		b1.dropPiece(Minimax.MAX, 2);
		
		assertEquals(1, b1.checkForWin());

	}
	
	@Test
	public void testCheckForWin2() {
		b1.dropPiece(Minimax.MIN, 0);
		b1.dropPiece(Minimax.MAX, 1);
		b1.dropPiece(Minimax.MIN, 2);
		
		assertEquals(-1, b1.checkForWin());
		
		b1.dropPiece(Minimax.MIN, 0);
		b1.dropPiece(Minimax.MIN, 1);
		b1.dropPiece(Minimax.MIN, 2);
		
		assertEquals(2, b1.checkForWin());
	}
	
	@Test
	public void testCheckForWin3() {
		b1.dropPiece(Minimax.MIN, 0);
		b1.dropPiece(Minimax.MAX, 0);
		b1.dropPiece(Minimax.MIN, 0);
		
		assertEquals(-1, b1.checkForWin());
		
		b1.dropPiece(Minimax.MAX, 1);
		b1.dropPiece(Minimax.MIN, 1);
		
		assertEquals(-1, b1.checkForWin());
		
		b1.dropPiece(Minimax.MIN, 2);
		
		assertEquals(2, b1.checkForWin());
	}
	
	@Test
	public void testCheckRLDiagonal() {
		b1.dropPiece(Minimax.MAX, 0);
		b1.dropPiece(Minimax.MAX, 0);
		b1.dropPiece(Minimax.MAX, 0);
		
		b1.dropPiece(Minimax.MIN, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MIN, 3);
		
		b1.dropPiece(Minimax.MAX, 2);
		b1.dropPiece(Minimax.MAX, 2);
		b1.dropPiece(Minimax.MAX, 2);
		
		b1.dropPiece(Minimax.MAX, 4);
		b1.dropPiece(Minimax.MAX, 4);
		b1.dropPiece(Minimax.MAX, 4);
		b1.dropPiece(Minimax.MAX, 4);
		assertEquals(Minimax.EMPTY, b1.checkRLDiagonal(4, 4));
		
		b1.dropPiece(Minimax.MAX, 4);
		assertEquals(Minimax.MAX, b1.checkRLDiagonal(4, 4));
	}
	
	@Test
	public void testCheckLRDiagonal() {
		b1.dropPiece(Minimax.MAX, 0);
		b1.dropPiece(Minimax.MAX, 0);
		b1.dropPiece(Minimax.MAX, 0);
		
		b1.dropPiece(Minimax.MIN, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MIN, 3);
		
		assertEquals(Minimax.EMPTY, b1.checkLRDiagonal(2, 0));
		
		b1.dropPiece(Minimax.MAX, 1);
		b1.dropPiece(Minimax.MAX, 1);
		
		assertEquals(Minimax.EMPTY, b1.checkLRDiagonal(2, 0));
		
		b1.dropPiece(Minimax.MAX, 2);
		assertEquals(Minimax.MAX, b1.checkLRDiagonal(2, 0));
		
		b1.dropPiece(Minimax.MAX, 2);
		b1.dropPiece(Minimax.MAX, 2);
		b1.dropPiece(Minimax.MAX, 2);
		b1.dropPiece(Minimax.MAX, 2);
		
		b1.dropPiece(Minimax.MAX, 1);
		b1.dropPiece(Minimax.MAX, 1);
		assertEquals(Minimax.EMPTY, b1.checkLRDiagonal(4, 1));
		
		b1.dropPiece(Minimax.MAX, 1);
		assertEquals(Minimax.MAX, b1.checkLRDiagonal(4, 1));
		
		
		
	}
	
	@Test
	public void testCheckHorizontal() {
		b1.dropPiece(Minimax.MAX, 0);
		b1.dropPiece(Minimax.MAX, 0);
		b1.dropPiece(Minimax.MAX, 0);
		
		b1.dropPiece(Minimax.MIN, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MIN, 3);
		
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(0));
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(1));
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(2));
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(3));
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(4));
		
		b1.dropPiece(Minimax.MAX, 1);
		b1.dropPiece(Minimax.MIN, 2);
		b1.dropPiece(Minimax.MAX, 2);
		b1.dropPiece(Minimax.MAX, 4);
		
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(0));
		assertEquals(Minimax.EMPTY, b1.checkHorizontal(1));
		
		b1.dropPiece(Minimax.MAX, 4);
		assertEquals(Minimax.MAX, b1.checkHorizontal(1));
	}

	@Test
	public void testCheckVertical() {
		b1.dropPiece(Minimax.MAX, 0);
		b1.dropPiece(Minimax.MAX, 0);
		b1.dropPiece(Minimax.MAX, 0);
		
		b1.dropPiece(Minimax.MIN, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MAX, 3);
		b1.dropPiece(Minimax.MIN, 3);
		assertEquals(Minimax.MAX, b1.checkVertical(0));
		assertEquals(Minimax.EMPTY, b1.checkVertical(1));
		assertEquals(Minimax.EMPTY, b1.checkVertical(2));
		assertEquals(Minimax.MAX, b1.checkVertical(3));
		assertEquals(Minimax.EMPTY, b1.checkVertical(4));
		assertEquals(Minimax.EMPTY, b1.checkVertical(5));
	}
}
