package edu.wpi.cs.connectn.board;

/**
 * Represents a Column in the Connect-N grid
 * 
 * @author Chris
 *
 */
public class Column {
	
	/** The height of the column (a.k.a. max number of checkers it can contain) */
	protected final int height;
	
	/** The number of checkers currently in this column */
	protected int numCheckers;
	
	/** A list of slots in this column */
	protected Checker[] slots;
	
	/**
	 * Constructs a new Column with the specified height (available slots)
	 * 
	 * @param height the number of checkers that can fit in this column
	 */
	public Column(int height) {
		this.height = height;
		this.numCheckers = 0;
		this.slots = new Checker[height];
	
		// Set all the slots to empty
		for (int i = 0; i < height; i++) {
			slots[i] = Checker.EMPTY;
		}
	}
	
	/**
	 * Drops a new checker in this column. The new checker is placed in
	 * the next available slot.
	 * 
	 * @param piece the piece to place (red or black)
	 * @return true if successful, false otherwise
	 */
	public boolean dropChecker(Checker piece) {
		if ((numCheckers < height) && (piece != Checker.EMPTY)) {
			slots[numCheckers++] = piece;
			return true;
		}
		else {
			return false; // the column is full
		}
	}
	
	/**
	 * Returns the checker at the given position
	 * @param pos the position to retrieve a checker from
	 * @return the checker at the given position
	 */
	public Checker getChecker(int pos) {
		if (pos < height) {
			return slots[pos];
		}
		else {
			throw new RuntimeException("Column does not have that many slots");
		}
	}
}
