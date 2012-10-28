package edu.wpi.cs.connectn.board;

/**
 * Represents a Column in the Connect-N grid
 * 
 * @author Chris
 *
 */
public class Column {
	
	/** The height of the column (a.k.a. max number of pieces it can contain) */
	protected final int height;
	
	/** The number of pieces currently in this column */
	protected int numPieces;
	
	/** A list of slots in this column */
	protected Minimax[] slots;
	
	/**
	 * Constructs a new Column with the specified height (available slots)
	 * 
	 * @param height the number of pieces that can fit in this column
	 */
	public Column(int height) {
		this.height = height;
		this.numPieces = 0;
		this.slots = new Minimax[height];
	
		// Set all the slots to empty
		for (int i = 0; i < height; i++) {
			slots[i] = Minimax.EMPTY;
		}
	}
	
	/**
	 * Drops a new piece in this column. The new piece is placed in
	 * the next available slot.
	 * 
	 * @param piece the piece to place (MAX or MIN)
	 * @return true if successful, false otherwise
	 */
	public boolean dropPiece(Minimax piece) {
		if ((numPieces < height) && (piece != Minimax.EMPTY)) {
			slots[numPieces++] = piece;
			return true;
		}
		else {
			return false; // the column is full
		}
	}
	
	/**
	 * Returns the piece at the given position
	 * @param pos the position to retrieve a piece from
	 * @return the piece at the given position
	 */
	public Minimax getPiece(int pos) {
		if (pos < height) {
			return slots[pos];
		}
		else {
			throw new RuntimeException("Column does not have that many slots");
		}
	}
}
