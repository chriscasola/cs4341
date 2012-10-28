package edu.wpi.cs.connectn.board;

import java.util.ArrayList;

import edu.wpi.cs.connectn.tree.Move;

/**
 * A JournaledBoard keeps track of every move made and allows moves to be reverted.
 */
public class JournaledBoard extends Board {
	private final ArrayList<Move> moves;
	
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
		moves = new ArrayList<Move>();
	}
	
	/**
	 * Performs a move on the board.
	 * 
	 * @param	move	A Move.
	 */
	@Override
	public void doMove(Move move) {
		super.doMove(move);
		moves.add(move);
	}
	
	public void revert(int numMoves) {
		
	}
}
