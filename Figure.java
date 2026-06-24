/**
 * The Figure class represents a game piece on a game board.
 */
public class Figure {

	private final int team;
	private int relPos; // relative position

	/**
	 * Constructs a new Figure object with the specified team.
	 *
	 * @param team the team number of the figure
	 */
	public Figure(int team) {
		this.relPos = -1; // -1 equals starting position
		this.team = team;
	}

	/**
	 * Retrieves the team number of the figure.
	 *
	 * @return the team number
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * Retrieves the relative position of the figure on the game board.
	 *
	 * @return the relative position
	 */
	public int getRelPosition() {
		return relPos;
	}

	/**
	 * Sets the relative position of the figure on the game board.
	 *
	 * @param position the new relative position
	 */
	public void setRelPosition(int position) {
		this.relPos = position;
	}

	/**
	 * Checks if the figure has moved enough spaces to be in a house.
	 *
	 * @return true if the figure is in a house, false otherwise
	 */
	public boolean isHouse() {
		return relPos >= 40 && relPos < 44;
	}

	/**
	 * Checks if the figure is in the base (starting position).
	 *
	 * @return true if the figure is in the base, false otherwise
	 */
	public boolean isBase() {
		return relPos < 0;
	}

	/**
	 * Moves the figure back to the base (starting position).
	 */
	public void backToBase() {
		relPos = -1;
	}

	@Override
	public String toString() {
		return String.valueOf(team);
	}

}
