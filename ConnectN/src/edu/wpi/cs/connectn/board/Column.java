package edu.wpi.cs.connectn.board;

/**
 * Represents a Column in the Connect-N grid
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
	 * @param height	The number of pieces that can fit in this Column.
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
	 * Creates a new Column that is a duplicate of the Column.
	 * 
	 * @param otherColumn	The Column to copy.
	 */
	protected Column(Column otherColumn) {
		this(otherColumn.height);
		this.numPieces = otherColumn.numPieces;
				
		for (int i = 0; i < height; i++) {
			this.slots[i] = otherColumn.slots[i];
		}
	}

	/**
	 * Drops a new piece in this column. The new piece is placed in
	 * the next available slot.
	 * 
	 * @param piece		The piece to place (MAX or MIN)
	 * @throws RuntimeException		if the column is already full
	 */
	public void dropPiece(Minimax piece) throws RuntimeException {
		if ((numPieces < height) && (piece != Minimax.EMPTY)) {
			slots[numPieces++] = piece;
		}
		else {
			throw new RuntimeException("Column is already full.");
		}
	}

	/**
	 * Returns the piece at the given position.
	 * 
	 * @param pos	The position to retrieve a piece from.
	 * 
	 * @return	A Minimax value representing the piece at the given position.
	 */
	public Minimax getPiece(int pos) {
		if (pos < height) {
			return slots[pos];
		}
		else {
			throw new IndexOutOfBoundsException("Column does not have that many slots");
		}
	}

	/**
	 * Removes the top most piece from the column.
	 * 
	 * TODO check if empty first
	 */
	protected void removePiece() {
		slots[--numPieces] = Minimax.EMPTY;
	}

	/**
	 * Determines whether or no the column is full.
	 * 
	 * @return	True if full, false otherwise.
	 */
	public boolean isFull() {
		if (numPieces >= height) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * @return the number of pieces in this column
	 */
	public int getNumPieces() {
		return numPieces;
	}
	
	/**
	 * Returns a duplicate of the column.
	 * 
	 * @return	A duplicate Column.
	 */
	public Column duplicate() {
		return new Column(this);
	}
}
