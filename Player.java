/**
 * The Player class represents a player in the game.
 */
public abstract class Player {

	protected boolean[] spaceFree; // bit representation of the home row [0] is the first position
	protected Board board;
	private static int teamcounter = 0; // the custom identifier every player has
	protected final int team;
	protected Dice dice; // used to get the random number

	public Figure[] figures;

	/**
	 * Constructs a new Player object with the specified board and dice.
	 *
	 * @param board the game board
	 * @param dice  the dice for generating random numbers
	 */
	public Player(Board board, Dice dice) {
		team = ++teamcounter;
		this.board = board;
		this.dice = dice;
		spaceFree = new boolean[] { true, true, true, true };
		figures = new Figure[4];
		for (int i = 0; i < 4; i++) {
			figures[i] = new Figure(team);
		}
	}

	/**
	 * Performs the next turn for the player.
	 */
	public void nextTurn() {
	}

	/**
	 * Checks if the player has only figures in the base (starting position).
	 *
	 * @return true if all figures are in the base, false otherwise
	 */
	public boolean hasOnlyBase() {
		for (int i = 0; i < 4; i++) {
			if (!figures[i].isBase())
				return false;
		}
		return true;
	}

	/**
	 * Checks if the player has an empty base (no figures in the starting position).
	 *
	 * @return true if the base is empty, false otherwise
	 */
	public boolean isBaseEmpty() {
		for (int i = 0; i < 4; i++) {
			if (figures[i].isBase())
				return false;
		}
		return true;
	}

	/**
	 * Checks if the player has won the game (all spaces are occupied in the home
	 * row).
	 *
	 * @return true if the player has won, false otherwise
	 */
	public boolean hasWon() {
		for (boolean b : spaceFree)
			if (b) {
				return false;
			}
		System.out.println("Spieler " + team + " hat gewonnen!!");
		return true;
	}

}
