package edu.wpi.cs.connectn.board;

/**
 * Represents a Connect-N game board
 * @author Chris
 *
 */
public class Board {
	
	/** N, the number of checkers in a row required to win */
	protected final int N;
	
	/** An array of the columns that make up this board */
	protected final Column[] columns;
	
	/** The height of the board */
	protected final int height;
	
	/** The width of the board */
	protected final int width;

	/**
	 * Constructs a new board with the given properties
	 * @param height the height of the board
	 * @param width the width of the board
	 * @param N the number of checkers in a row that are required to win
	 */
	public Board(int height, int width, int N) {

		// Set the height of each column
		this.height = height;

		// Set the width of the board
		this.width = width;

		// Set N (the number of checkers in a row required to win)
		this.N = N;

		// Instantiate columns
		columns = new Column[width];
		for (int i = 0; i < width; i++)
			columns[i] = new Column(height);
	}
	
	/**
	 * Checks if a player has won the game
	 * @return -1 if no player has won, 0 if it is a draw, 1 if black won, 2 if red won
	 */
	public int checkForWin() {
		
		// Check for a winner
		switch (checkAllRows()) {
		case BLACK:
			return 1;
		case RED:
			return 2;
		default:
			break;
		}
		
		switch (checkAllColumns()) {
		case BLACK:
			return 1;
		case RED:
			return 2;
		default:
			break;
		}
		
		switch (checkAllLRDiagonals()) {
		case BLACK:
			return 1;
		case RED:
			return 2;
		default:
			break;
		}
		
		switch (checkAllRLDiagonals()) {
		case BLACK:
			return 1;
		case RED:
			return 2;
		default:
			break;
		}
		
		// Check if the game board is full, in that case there is a draw
		int numCheckers = 0;
		for (int i = 0; i < width; i++)
			numCheckers += columns[i].numCheckers;
		if (numCheckers == width * height)
			return 0;
		
		return -1;
	}

	protected Checker checkAllRows() {
		Checker retVal = Checker.EMPTY;

		// Check all rows
		for (int i = 0; i < height; i++) {
			retVal = checkHorizontal(i);
			if (retVal != Checker.EMPTY)
				return retVal;
		}
		return retVal;
	}

	protected Checker checkAllColumns() {
		Checker retVal = Checker.EMPTY;

		// Check all columns
		for (int i = 0; i < width; i++) {
			retVal = checkVertical(i);
			if (retVal != Checker.EMPTY)
				return retVal;
		}
		return retVal;
	}

	protected Checker checkAllLRDiagonals() {
		Checker retVal = Checker.EMPTY;

		// Check top-left to bottom-right diagonals
		for (int i = 1; i < height; i++) {
			retVal = checkLRDiagonal(i, 0);
			if (retVal != Checker.EMPTY)
				return retVal;
		}
		for (int i = 1; i < width; i++) {
			retVal = checkLRDiagonal(height - 1, i);
			if (retVal != Checker.EMPTY)
				return retVal;
		}
		return retVal;
	}

	protected Checker checkAllRLDiagonals() {
		Checker retVal = Checker.EMPTY;

		// Check top-right to bottom-left diagonals
		for (int i = 1; i < width; i++) {
			retVal = checkRLDiagonal(height - 1, i);
			if (retVal != Checker.EMPTY)
				return retVal;
		}
		for (int i = 1; i < height; i++) {
			retVal = checkRLDiagonal(i, width - 1);
			if (retVal != Checker.EMPTY)
				return retVal;
		}
		return retVal;
	}

	protected Checker checkRLDiagonal(int row, int col) {
		int numInARow = 0;
		Checker lastColor = Checker.EMPTY;

		while ((row >= 0) && (col >= 0)) {
			Checker currChecker = columns[col].getChecker(row);

			if (currChecker == Checker.EMPTY) {
				lastColor = currChecker;
				numInARow = 0;
			}
			else if (currChecker == lastColor) {
				if (++numInARow >= N) {
					return lastColor;
				}
			}
			else {
				lastColor = currChecker;
				numInARow = 1;
			}
			row--;
			col--;
		}

		return Checker.EMPTY;
	}

	protected Checker checkLRDiagonal(int row, int col) {
		int numInARow = 0;
		Checker lastColor = Checker.EMPTY;

		while ((row >= 0) && (col < width)) {
			Checker currChecker = columns[col].getChecker(row);

			if (currChecker == Checker.EMPTY) {
				lastColor = currChecker;
				numInARow = 0;
			}
			else if (currChecker == lastColor) {
				if (++numInARow >= N) {
					return lastColor;
				}
			}
			else {
				lastColor = currChecker;
				numInARow = 1;
			}
			row--;
			col++;
		}
		return Checker.EMPTY;
	}

	protected Checker checkHorizontal(int row) {
		int numInARow = 0;
		Checker lastColor = Checker.EMPTY;

		for (int i = 0; i < width; i++) {
			Checker currChecker = columns[i].getChecker(row);

			if (currChecker == Checker.EMPTY) {
				lastColor = currChecker;
				numInARow = 0;
			}
			else if (currChecker == lastColor) {
				if (++numInARow >= N) {
					return lastColor;
				}
			}
			else {
				lastColor = currChecker;
				numInARow = 1;
			}
		}
		return Checker.EMPTY;
	}

	protected Checker checkVertical(int col) {
		int numInARow = 0;
		Checker lastColor = Checker.EMPTY;
		Column currColumn = columns[col];

		for (int i = 0; i < currColumn.numCheckers; i++) {
			Checker currChecker = currColumn.getChecker(i);
			if (currChecker == lastColor) {
				if (++numInARow >= N) {
					return lastColor;
				}
			}
			else {
				lastColor = currChecker;
				numInARow = 1;
			}
		}
		return Checker.EMPTY;
	}

	public boolean dropChecker(Checker piece, int colNum) {
		if (colNum < columns.length) {
			return columns[colNum].dropChecker(piece);
		}
		else {
			return false;
		}
	}

	public String toString() {
		String retVal = "";
		for (int i = height - 1; i >= 0; i--) {
			for (int j = 0; j < width; j++) {
				switch (columns[j].getChecker(i)) {
				case BLACK:
					retVal += "X";
					break;
				case EMPTY:
					retVal += "-";
					break;
				case RED:
					retVal += "O";
					break;
				}
			}
			retVal += "\n";
		}
		return retVal;
	}
}