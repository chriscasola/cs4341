package edu.wpi.cs.connectn.tree;

import edu.wpi.cs.connectn.board.Minimax;

/**
 * Represents a Move by storing the column in which the Move was made and the owner of the Move.
 */
public class Move {
	private final int column;
	private final Minimax owner;
	
	/**
	 * Constructor.
	 * 
	 * @param column	An int representing the column in which a Move has been made.
	 * @param owner		A Minimax representing the owner of the Move.
	 */
	public Move(int column, Minimax owner) {
		this.column = column;
		this.owner = owner;
	}
	
	/**
	 * Getter for the Move's column number.
	 * 
	 * @return	An int, starting at 0, representing the column of the Move.
	 */
	public int getColumn() {
		return column;
	}
	
	/**
	 * Getter for the Move's owner.
	 * 
	 * @return	A Minimax stating the owner of the Move.
	 */
	public Minimax getOwner() {
		return owner;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}
		else if (!(other instanceof Move)) {
			return false;
		}
		else if ((this.column == ((Move)other).column) && (this.owner == ((Move)other).owner)) {
			return true;
		}
		else {
			return false;
		}
	}
}
