package edu.wpi.cs.connectn.board;

import java.util.Stack;

import edu.wpi.cs.connectn.tree.Move;

/**
 * A JournaledBoard keeps track of every move made and allows moves to be reverted.
 */
public class JournaledBoard extends Board {
	/** The move log. The most recent move is stored on top of the stack. */
	protected final Stack<Move> moveLog;
	
	/**
	 * Constructor.
	 * 
	 * @see edu.wpi.cs.connectn.board.Board#Board(int, int, int)
	 * 
	 * @param height	The height of the board.
	 * @param width		The width of the board.
	 * @param N			The number of pieces in a line required to win.
	 */
	public JournaledBoard(int height, int width, int N) {
		super(height, width, N);
		moveLog = new Stack<Move>();
	}
	
	/**
	 * A copy constructor
	 * @param otherBoard the board to copy
	 */
	@SuppressWarnings("unchecked")
	public JournaledBoard(JournaledBoard otherBoard) {
		super(otherBoard.height, otherBoard.width, otherBoard.N);
		
		for (int i = 0; i < otherBoard.width; i++) {
			this.columns[i] = otherBoard.columns[i].duplicate();
		}
		this.moveLog = (Stack<Move>)otherBoard.moveLog.clone();
	}
	
	/**
	 * @see edu.wpi.cs.connectn.board.Board#doMove(Move)
	 */
	@Override
	public void doMove(Move move) throws RuntimeException, IndexOutOfBoundsException {
		super.doMove(move);
		moveLog.push(move);	// add the move to the front of move log
	}
	
	/**
	 * Reverts 1 move.
	 * 
	 * @see JournaledBoard#revert(int)
	 */
	public void revert() {
		revert(1);
	}
	
	/**
	 * Reverts the specified number of moves made.
	 * 
	 * @param numMoves	The number of moves to revert.
	 */
	public void revert(int numMoves) {
		if (numMoves > moveLog.size()) {
			throw new RuntimeException("There are not enough moves to revert.");
		}
		
		Move currentMove;
		for (int i = 0; i < numMoves; i++) {
			currentMove =  moveLog.pop();
			this.columns[currentMove.getColumn()].removePiece();
		}
	}
	
	public JournaledBoard duplicate() {
		return new JournaledBoard(this);
	}
}
