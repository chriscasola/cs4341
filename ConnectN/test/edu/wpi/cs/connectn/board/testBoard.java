package edu.wpi.cs.connectn.board;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.cs.connectn.board.Board;
import edu.wpi.cs.connectn.board.Checker;

public class testBoard {
	
	Board b1;

	@Before
	public void setUp() throws Exception {
		b1 = new Board(5, 6, 3);
	}

	@Test
	public void testDropChecker() {
		b1.dropChecker(Checker.BLACK, 0);
		b1.dropChecker(Checker.BLACK, 0);
		b1.dropChecker(Checker.BLACK, 0);
		
		b1.dropChecker(Checker.RED, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.BLACK, 3);
		assertEquals("------\n------\nX--X--\nX--X--\nX--O--\n", b1.toString());
	}
	
	@Test
	public void testCheckForWin1() {
		b1.dropChecker(Checker.BLACK, 2);
		b1.dropChecker(Checker.BLACK, 2);
		
		assertEquals(-1, b1.checkForWin());
		
		b1.dropChecker(Checker.BLACK, 2);
		
		assertEquals(1, b1.checkForWin());

	}
	
	@Test
	public void testCheckForWin2() {
		b1.dropChecker(Checker.RED, 0);
		b1.dropChecker(Checker.BLACK, 1);
		b1.dropChecker(Checker.RED, 2);
		
		assertEquals(-1, b1.checkForWin());
		
		b1.dropChecker(Checker.RED, 0);
		b1.dropChecker(Checker.RED, 1);
		b1.dropChecker(Checker.RED, 2);
		
		assertEquals(2, b1.checkForWin());
	}
	
	@Test
	public void testCheckForWin3() {
		b1.dropChecker(Checker.RED, 0);
		b1.dropChecker(Checker.BLACK, 0);
		b1.dropChecker(Checker.RED, 0);
		
		assertEquals(-1, b1.checkForWin());
		
		b1.dropChecker(Checker.BLACK, 1);
		b1.dropChecker(Checker.RED, 1);
		
		assertEquals(-1, b1.checkForWin());
		
		b1.dropChecker(Checker.RED, 2);
		
		assertEquals(2, b1.checkForWin());
	}
	
	@Test
	public void testCheckRLDiagonal() {
		b1.dropChecker(Checker.BLACK, 0);
		b1.dropChecker(Checker.BLACK, 0);
		b1.dropChecker(Checker.BLACK, 0);
		
		b1.dropChecker(Checker.RED, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.RED, 3);
		
		b1.dropChecker(Checker.BLACK, 2);
		b1.dropChecker(Checker.BLACK, 2);
		b1.dropChecker(Checker.BLACK, 2);
		
		b1.dropChecker(Checker.BLACK, 4);
		b1.dropChecker(Checker.BLACK, 4);
		b1.dropChecker(Checker.BLACK, 4);
		b1.dropChecker(Checker.BLACK, 4);
		assertEquals(Checker.EMPTY, b1.checkRLDiagonal(4, 4));
		
		b1.dropChecker(Checker.BLACK, 4);
		assertEquals(Checker.BLACK, b1.checkRLDiagonal(4, 4));
	}
	
	@Test
	public void testCheckLRDiagonal() {
		b1.dropChecker(Checker.BLACK, 0);
		b1.dropChecker(Checker.BLACK, 0);
		b1.dropChecker(Checker.BLACK, 0);
		
		b1.dropChecker(Checker.RED, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.RED, 3);
		
		assertEquals(Checker.EMPTY, b1.checkLRDiagonal(2, 0));
		
		b1.dropChecker(Checker.BLACK, 1);
		b1.dropChecker(Checker.BLACK, 1);
		
		assertEquals(Checker.EMPTY, b1.checkLRDiagonal(2, 0));
		
		b1.dropChecker(Checker.BLACK, 2);
		assertEquals(Checker.BLACK, b1.checkLRDiagonal(2, 0));
		
		b1.dropChecker(Checker.BLACK, 2);
		b1.dropChecker(Checker.BLACK, 2);
		b1.dropChecker(Checker.BLACK, 2);
		b1.dropChecker(Checker.BLACK, 2);
		
		b1.dropChecker(Checker.BLACK, 1);
		b1.dropChecker(Checker.BLACK, 1);
		assertEquals(Checker.EMPTY, b1.checkLRDiagonal(4, 1));
		
		b1.dropChecker(Checker.BLACK, 1);
		assertEquals(Checker.BLACK, b1.checkLRDiagonal(4, 1));
		
		
		
	}
	
	@Test
	public void testCheckHorizontal() {
		b1.dropChecker(Checker.BLACK, 0);
		b1.dropChecker(Checker.BLACK, 0);
		b1.dropChecker(Checker.BLACK, 0);
		
		b1.dropChecker(Checker.RED, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.RED, 3);
		
		assertEquals(Checker.EMPTY, b1.checkHorizontal(0));
		assertEquals(Checker.EMPTY, b1.checkHorizontal(1));
		assertEquals(Checker.EMPTY, b1.checkHorizontal(2));
		assertEquals(Checker.EMPTY, b1.checkHorizontal(3));
		assertEquals(Checker.EMPTY, b1.checkHorizontal(4));
		
		b1.dropChecker(Checker.BLACK, 1);
		b1.dropChecker(Checker.RED, 2);
		b1.dropChecker(Checker.BLACK, 2);
		b1.dropChecker(Checker.BLACK, 4);
		
		assertEquals(Checker.EMPTY, b1.checkHorizontal(0));
		assertEquals(Checker.EMPTY, b1.checkHorizontal(1));
		
		b1.dropChecker(Checker.BLACK, 4);
		assertEquals(Checker.BLACK, b1.checkHorizontal(1));
	}

	@Test
	public void testCheckVertical() {
		b1.dropChecker(Checker.BLACK, 0);
		b1.dropChecker(Checker.BLACK, 0);
		b1.dropChecker(Checker.BLACK, 0);
		
		b1.dropChecker(Checker.RED, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.BLACK, 3);
		b1.dropChecker(Checker.RED, 3);
		assertEquals(Checker.BLACK, b1.checkVertical(0));
		assertEquals(Checker.EMPTY, b1.checkVertical(1));
		assertEquals(Checker.EMPTY, b1.checkVertical(2));
		assertEquals(Checker.BLACK, b1.checkVertical(3));
		assertEquals(Checker.EMPTY, b1.checkVertical(4));
		assertEquals(Checker.EMPTY, b1.checkVertical(5));
	}
}
