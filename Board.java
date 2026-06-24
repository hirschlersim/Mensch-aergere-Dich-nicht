public class Board {

	Figure[] board;

	/**
	 * Initializes a new instance of the Board class. Constructs an empty board with
	 * 40 positions.
	 */
	public Board() {
		board = new Figure[40];
		for (int i = 0; i < 40; i++) {
			board[i] = null;
		}
	}

	/**
	 * Moves a figure on the board based on the given offset.
	 *
	 * @param f         The figure to be moved.
	 * @param offset    The offset by which to move the figure.
	 * @param spaceFree The bit representation of the home row's space availability.
	 * @return True if the figure was successfully moved, false otherwise.
	 */
	public boolean moveFigure(Figure f, int offset, boolean[] spaceFree) {

		int totalRel = f.getRelPosition() + offset;
		int totalAbs = absolutePos(f) + offset;
		if (totalAbs >= 40)
			totalAbs -= 40; // when index 40 is exceeded, subtract 40
		// TODO: keep in mind: when subtracting the offset, you need to consider getting
		// below 0 and add 40
		// Dont forget to alter between "InBase" and just a negative value for totalAbs
		// maby Use: (Simon)
		// totalAbs = (totalAbs + offset) % 40;

		if (f.isBase() && offset == 6) { // move figure from base to start
			setFigureStart(f);
			return true;
		}
		if (f.isBase()) { // if figure is still in base => not movable
			return false;
		}

		if (f.isHouse()) { // if figure is in house => not movable
			return false;
		}
		
		if(totalRel >= 44) {
			return false;
		}

		if (40 <= totalRel && totalRel < 44) { // the figure could be place in the home row
			if (spaceFree[totalRel - 40]) {
				spaceFree[totalRel - 40] = false;
				board[absolutePos(f)] = null;
				f.setRelPosition(totalRel);
				// if figure is placable in home
				return true;
			}
			// if figure is not placeable in home
			return false;
		}
		if (board[totalAbs] == null) {
			board[totalAbs] = f;
			if (totalAbs - offset < 0)
				totalAbs += 40;
			board[totalAbs - offset] = null;
			f.setRelPosition(totalRel);
			return true;
		}
		if (board[totalAbs].getTeam() != f.getTeam()) {
			board[totalAbs].backToBase();
			board[totalAbs] = f;
			if (totalAbs - offset < 0)
				totalAbs += 40;
			board[totalAbs - offset] = null;
			f.setRelPosition(totalRel);
			return true;
		}

		return false;
	}

	/**
	 * Sets the figure to its starting position on the board.
	 *
	 * @param f The figure to be set to the starting position.
	 * @throws IndexOutOfBoundsException If the index calculated for the starting
	 *                                   position is out of bounds.
	 */
	private void setFigureStart(Figure f) throws IndexOutOfBoundsException {
		int index = (f.getTeam() - 1) * 10;
		if (board[index] == null) {
			board[index] = f;
			f.setRelPosition(0);
		} else if (board[index].getTeam() != f.getTeam()) {
			board[index].setRelPosition(-1);
			board[index] = f;
			f.setRelPosition(0);
		}
	}

	/**
	 * Gets the absolute position of a figure on the board.
	 *
	 * @param f The figure for which to get the absolute position.
	 * @return The absolute position of the figure on the board, or -1 if the figure
	 *         is not found on the board.
	 */
	private int absolutePos(Figure f) {
		for (int i = 0; i < 40; i++) {

//			if (board[i] == f)	//TODO
//				return i; // should still work
			if (!(board[i] == null)) { // we test the pointer and do not call a methode
				if (board[i] == f)
					return i;
			}
		}
		return -1;
	}

	/**
	 * Gets the evaluation score of a potential move for a given figure and offset.
	 *
	 * @param f      The figure for which to evaluate the move.
	 * @param offset The offset by which the figure would be moved.
	 * @return The evaluation score of the move: 0 if no opponent is hit, 1 if an
	 *         opponent is hit.
	 */
	public int getMoveEvaluation(Figure f, int offset) {

		int pos = absolutePos(f);

		if (f.isBase()) { // we would get out on the board
			int index = (f.getTeam() - 1) * 10;

			if (board[index] == null)
				return 0; // would not hit a opponent

			if (board[index].getTeam() == f.getTeam())
				return 0; // would not hit a opponent

			return 1; // we would hit a enemy
		}

		pos = (pos + offset) % 40; // pos that the figure would get, with the loop around for the board 38,39,0,1

		if (board[pos] == null)
			return 0; // the figure would not hit a other figure
		if (board[pos].getTeam() == f.getTeam())
			return 0; // the figure would hit another figure but it is on the same team

		return 1; // we would hit a enemy
	}

	/**
	 * Returns a string representation of the board.
	 *
	 * @return The string representation of the board.
	 */
	@Override
	public String toString() {
		int counter = 0;
		String result = "";
		for (int i = 0; i < board.length; i++) {
			if (counter % 10 == 0)
				result += ' ';
			if (board[i] == null && counter % 10 == 0) {
				result += 'o';
			} else if (board[i] == null) {
				result += '*';
			} else {
				result += board[i].toString();
			}
			counter++;
		}

		return result;
	}

}
